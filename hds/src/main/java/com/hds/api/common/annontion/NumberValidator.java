package com.hds.api.common.annontion;
 
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hds.api.common.constant.RequestValidation;

/**
* @ClassName:com.hds.api.common.validate.NumberValidator
* @Description: 数字字符串约束    注解接口定义
* @author zengli
* @date 创建时间：2016年4月17日 下午4:29:05
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD}) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented
@Constraint(validatedBy = {NumberValidatorImpl.class}) 
public @interface NumberValidator {
 
    boolean isNumber () default true;
     
    //约束注解验证时的输出消息
    String message() default RequestValidation.ValidateMessage.LIMIT_ISNUMBER_ERRMSG;   
    
    //约束注解验证所属的组别
    Class<?>[] groups() default { }; 
     
   // 约束注解的有效负载
    Class<? extends Payload>[] payload() default { }; 
     
}