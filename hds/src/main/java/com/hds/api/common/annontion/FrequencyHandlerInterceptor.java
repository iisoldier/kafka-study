/** 
 * @ClassName:com.hds.api.common.frequency
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月19日 上午10:01:24
 */
package com.hds.api.common.annontion;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hds.api.common.constant.R;
import com.hds.api.common.constant.RequestValidation;
import com.hds.api.common.util.DateUtils;
import com.hds.api.sys.vo.ResponseResult;

/**
 * @ClassName:com.hds.api.common.frequency.FrequencyHandlerInterceptor
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月19日 上午10:01:24
 */
public class FrequencyHandlerInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = Logger
			.getLogger(FrequencyHandlerInterceptor.class);

	private static final int MAX_BASE_STATION_SIZE = 200000;
	//创建线程安全map
	private static Map<String, FrequencyStruct> BASE_STATION = 
			Collections.synchronizedMap(new HashMap<String, FrequencyStruct>(MAX_BASE_STATION_SIZE));

	private static final float SCALE = 0.75F;
	private static final int MAX_CLEANUP_COUNT = 3;
	private static final int CLEANUP_INTERVAL = 1000;
	private Object syncRoot = new Object();
	private int cleanupCount = 0;

	/**
	 * @method preHandle
	 * @Description: 
	 *               preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的
	 *               Interceptor拦截器是链式的，可以同时存在
	 *               多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一
	 *               个的执行，而且所有的Interceptor中的preHandle方法都会在
	 *               Controller方法调用之前调用。SpringMVC的
	 *               这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
	 *               回值为false，当preHandle 的返回值为false的时候整个请求就结束了。
	 * @author zengli
	 * @date 2016年4月17日 下午3:41:23
	 * @parameter HttpServletRequest request 查询请求对象
	 * @parameter HttpServletResponse response 绑定的校验结果
	 * @parameter Object handler 请求ip
	 * @return boolean
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//获取方法级别的注解参数
		Frequency methodFrequency = ((HandlerMethod) handler)
				.getMethodAnnotation(Frequency.class);
		    //获取类级别的注解参数
		Frequency classFrequency = ((HandlerMethod) handler).getBean()
				.getClass().getAnnotation(Frequency.class);
		
		
		//设置拦截器处理状态
		boolean going = true;
		
		//如果类级别的注解不为空，则进入拦截器处理方法
		//拦截器处理方法
		if (classFrequency != null) {
			logger.info("类级别注释的请求已经被拦截器捕捉");
			going = handleFrequency(request, response, classFrequency);
		}
		
		//如果方法级别的注解不为空，则进入拦截器控制方法
		if (going && methodFrequency != null) {
			logger.info("方法级别注释的请求已经被拦截器捕捉");
			going = handleFrequency(request, response, methodFrequency);
		}
		
		return going;
	}

	
	/**
	* @method  handleFrequency
	* @Description:  注解处理类
	* @author  zengli
	* @date 2016年4月19日 下午6:06:21
	* @parameter  
	* @return  boolean
	 */
	private boolean handleFrequency(HttpServletRequest request,
			HttpServletResponse response, Frequency frequency) {

		//设置拦截器处理结果状态
		boolean going = true;
		
		//如果方法或者类，使用本注解，则直接设置拦截器处理状态处理结果为true
		if (frequency == null) {
			return going;
		}

		//若有方法使用该注解，则获取注解中提交的参数
		String name = frequency.name();
		//String message  = frequency.message();
		int limit = frequency.limit();
		int time = frequency.time();


		//如果注解的时间参数time=0或者limit=0(代表0秒内x请求，或者0请求x时间)
		if (time == 0 || limit == 0) {
			//going = false;
			//response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			
			going = true;
			ResponseResult rr = new ResponseResult();
			rr.setResult_code(RequestValidation.ResultCode.FREQUENCY_PARAM_ZERO);
			rr.setField(RequestValidation.Field.FREQUENCY_PARAM_ZERO);
			rr.setType(R.SystemConstant.RESTYPE_FREQUENCY);
			rr.setMessage(RequestValidation.ValidateMessage.FREQUENCY_PARAMZERO_ERRMSG);			
			request.setAttribute(R.SystemConstant.RESTYPE_FREQUENCY, rr);
			return going;
		}

		//获得当前时间(单位秒)
		long currentTimeMilles = System.currentTimeMillis() / 1000;
		//获得远程请求访问IP
		String ip = getRemoteIp(request);
		//生成计数Key(接口名称+访问IP)
		String key = ip + "_" + name;
		//从计数MAP当中获得@Frequency注解构造对象
		FrequencyStruct frequencyStruct = BASE_STATION.get(key);
		
		//如果构造结构一开始为空，则初始化相关参数
		if (frequencyStruct == null) {
			frequencyStruct = new FrequencyStruct();
			frequencyStruct.uniqueKey = name;
			frequencyStruct.start = frequencyStruct.end = currentTimeMilles;
			frequencyStruct.limit = limit;
			frequencyStruct.time = time;
			frequencyStruct.accessPoints.add(currentTimeMilles);
			
			//多线程锁，保证各个线程可以添加计数对象到  接口访问计数的map当中
    		//synchronized (syncRoot) {
			//	BASE_STATION.put(key, frequencyStruct);
			//}
			
			//若采用线程安全map则可以直接添加相关参数
			BASE_STATION.put(key, frequencyStruct);
			
			//如果当前map存储的数量大于     map存储的最大值*上限阀值
			//清理map中的数据
			if (BASE_STATION.size() > MAX_BASE_STATION_SIZE * SCALE) {
				cleanup(currentTimeMilles);
			}
		} 
		else {
			//如果已经存在值
			frequencyStruct.end = currentTimeMilles;
			frequencyStruct.accessPoints.add(currentTimeMilles);
		}

		// 校验时间断是否超过
		if (frequencyStruct.end - frequencyStruct.start >= time) {
			logger.info("@frequency校验时间段已经超时，校验结构即将重置。"+frequencyStruct.toString());
			frequencyStruct.reset(currentTimeMilles);	
		} 
		//时间校验成功
		else {
			//获得当前接口在单位时间内的请求数量
			int count = frequencyStruct.accessPoints.size();
			//当前接口在单位时间内的请求数量 >限制数量
			if (count > limit) {
				logger.info("@frequency接口请求过于频繁，超出限制，信息为["
			             +key+R.SystemConstant.LOG_COL_DELIMITER
						+count+R.SystemConstant.LOG_COL_DELIMITER
						+limit+R.SystemConstant.LOG_COL_DELIMITER
						+DateUtils.getDayStrBySec(frequencyStruct.start)+R.SystemConstant.LOG_COL_DELIMITER
						+DateUtils.getDayStrBySec(frequencyStruct.end)+"]");
				logger.info(frequencyStruct.toString());
				//going = false;
				//允许通过，以便将数据存入request
				going = true;
							
				ResponseResult rr = new ResponseResult();
				rr.setResult_code(RequestValidation.ResultCode.FREQUENCY_TIMES_OVERLIMITS);
				rr.setField(RequestValidation.Field.FREQUENCY_TIMES_OVERLIMITS);
				rr.setType(R.SystemConstant.RESTYPE_FREQUENCY);
				rr.setMessage(RequestValidation.ValidateMessage.FREQUENCY_OVERLIMITS_ERRMSG
			             +key+R.SystemConstant.LOG_COL_DELIMITER
						+count+R.SystemConstant.LOG_COL_DELIMITER
						+limit+R.SystemConstant.LOG_COL_DELIMITER
						+DateUtils.getDayStrBySec(frequencyStruct.start)+R.SystemConstant.LOG_COL_DELIMITER
						+DateUtils.getDayStrBySec(frequencyStruct.end));
				
				request.setAttribute(R.SystemConstant.RESTYPE_FREQUENCY, rr);
				
				//response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return going;
			}
		}
		
		//设置成功状态的返回结果
		ResponseResult rr = new ResponseResult();
		rr.setResult_code(RequestValidation.ResultCode.FREQUENCY_IS_VALID);
		rr.setField(RequestValidation.Field.FREQUENCY_IS_VALID);
		rr.setType(R.SystemConstant.RESTYPE_FREQUENCY);
		rr.setMessage(RequestValidation.ValidateMessage.FREQUENCY_VALID_SUCCESSMSG);
		request.setAttribute(R.SystemConstant.RESTYPE_FREQUENCY, rr);
		
		return going;
	}

	/**
	 * 
	* @method  cleanup
	* @Description:
	* @author  zengli
	* @date 2016年4月19日 下午7:23:29
	* @parameter  
	* @return  void
	 */
	private void cleanup(long currentTimeMilles) {

		synchronized (syncRoot) {
			Iterator<String> it = BASE_STATION.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				FrequencyStruct struct = BASE_STATION.get(key);
				if ((currentTimeMilles - struct.end) > struct.time) {
					it.remove();
				}
			}

			if ((MAX_BASE_STATION_SIZE - BASE_STATION.size()) > CLEANUP_INTERVAL) {
				cleanupCount = 0;
			} else {
				cleanupCount++;
			}

			if (cleanupCount > MAX_CLEANUP_COUNT) {
				randomCleanup(MAX_CLEANUP_COUNT);
			}
		}
	}

/**
* @method  randomCleanup
* @Description:
* @author  zengli
* @date 2016年4月19日 下午7:34:32
* @parameter  
* @return  void
 */
	private void randomCleanup(int count) {
		// 防止调用错误
		if (BASE_STATION.size() < MAX_BASE_STATION_SIZE * SCALE) {
			return;
		}

		Iterator<String> it = BASE_STATION.keySet().iterator();
		Random random = new Random();
		int tempCount = 0;

		while (it.hasNext()) {
			if (random.nextBoolean()) {
				it.remove();
				tempCount++;
				if (tempCount >= count) {
					break;
				}
			}
		}
	}

	
  /**
  * @method  getRemoteIp
  * @Description:
  * @author  zengli
  * @date 2016年4月19日 下午7:35:12
  * @parameter  
  * @return  String
   */
	private String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;

	}
}
