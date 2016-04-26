package com.hds.api.common.annontion;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
* @ClassName:com.hds.api.common.validate.NumberValidatorImpl
* @Description: 数字字符串约束    注解接口实现
* @author zengli
* @date 创建时间：2016年4月17日 下午4:29:42
 */
public class NumberValidatorImpl implements ConstraintValidator<NumberValidator, String>  {

	private boolean isNumber;
	
	/** 
	    * <p>Title: 对验证器进行实例化</p>  
	    * @param constraintAnnotation  
	    */
	@Override
	public void initialize(NumberValidator constraintAnnotation) {
		// TODO Auto-generated method stub
		isNumber = constraintAnnotation.isNumber();
	}

	  /**  
	    * <p>Description: 校验的方法</p> 
	    * @param value  需要验证的实例
	    * @param context 约束执行的上下文环境
	    * @return  
	    */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value.equals(null)||value.length()<0){
			return  false;
		}else{
			try{
				if(isNumber){
                    new BigDecimal(value);
    				return true;
                }else{
                    Long.parseLong(value);
                    return false;
                }
			}
			catch (NumberFormatException e) {
                return false;
            }
		}
	}

}
