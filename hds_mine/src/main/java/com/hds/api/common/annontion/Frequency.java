/** 
* @ClassName:com.hds.api.common.validate
* @Description:
* @author zengli
* @date 创建时间：2016年4月19日 上午9:52:54
*/
package com.hds.api.common.annontion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;
import com.hds.api.common.constant.RequestValidation;

/** 
 * @ClassName:com.hds.api.common.validate.FrequencyValidate
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月19日 上午9:52:54
 */
@Target({ElementType.TYPE,ElementType.METHOD}) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented     
@Component    
public @interface Frequency {

	//String message() default RequestValidation.ValidateMessage.REQUEST_FREQUENCY_ERRMSG;
	String name() default  RequestValidation.Constant.DEFAULT_API_NAME;
	int time()  default RequestValidation.Constant.DEFAULT_FREQUENCY_LIMIT;
	int limit()  default RequestValidation.Constant.DEFAULT_FREQUENCY_TIME;
	
}