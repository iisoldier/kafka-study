package com.hds.api.common.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @ClassName:com.hds.api.common.util.VlidationUtil
 * @Description: 请求参数封装对象 注解校验工具类
 * @author zengli
 * @date 创建时间：2016年4月17日 下午4:25:46
 */
public class VlidationUtil {

	private static Validator validator;

	static {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		validator = vf.getValidator();
	}

	/**
	 * @method validate
	 * @Description: 校验方法
	 * @author zengli
	 * @date 2016年4月17日 下午4:26:27
	 * @parameter t 将要校验的对象
	 * @return void
	 * @throws ValidationException
	 *             校验失败异常，非程序异常，不走HDSException
	 */
	public static <T> void validate(T t) throws ValidationException {
		Set<ConstraintViolation<T>> set = validator.validate(t);
		if (set.size() > 0) {
			StringBuilder validateError = new StringBuilder();
			for (ConstraintViolation<T> val : set) {
				validateError.append(val.getMessageTemplate() + " ;");
			}
			throw new ValidationException(validateError.toString());
		}
	}

}