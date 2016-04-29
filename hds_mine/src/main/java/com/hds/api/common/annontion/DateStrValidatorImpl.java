package com.hds.api.common.annontion;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hds.api.common.constant.R;

/**
 * @ClassName:com.hds.api.common.validate.DateStrValidatorImpl
 * @Description: 时间字符串约束 注解接口实现
 * @author zengli
 * @date 创建时间：2016年4月17日 下午4:28:30
 */
public class DateStrValidatorImpl implements
		ConstraintValidator<DateStrValidator, String> {

	@SuppressWarnings("unused")
	private boolean isDateStr;

	/**
	 * <p>
	 * Title: 对验证器进行实例化
	 * </p>
	 * 
	 * @param constraintAnnotation
	 */
	@Override
	public void initialize(DateStrValidator constraintAnnotation) {
		// TODO Auto-generated method stub
		isDateStr = constraintAnnotation.isDateStr();
	}

	/**
	 * <p>
	 * Description: 校验的方法
	 * </p>
	 * 
	 * @param value
	 *            需要验证的实例
	 * @param context
	 *            约束执行的上下文环境
	 * @return
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat(R.DateConstant.DATE_SHORT);
		try {
			if (value.equals(sdf.format(sdf.parse(value)))) {
			//	System.out.println(sdf.parse(value));
			//	System.out.println(sdf.format(sdf.parse(value)));
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}
