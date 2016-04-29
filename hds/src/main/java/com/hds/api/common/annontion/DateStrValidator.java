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
* @ClassName:com.hds.api.common.validate.DateStrValidator
* @Description: 时间字符串约束 注解接口
* @author zengli
* @date 创建时间：2016年4月17日 下午4:27:53
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD}) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented
@Constraint(validatedBy = {DateStrValidatorImpl.class}) 
public @interface DateStrValidator {
 
    boolean isDateStr () default true;
     
    //约束注解验证时的输出消息
    String message() default RequestValidation.ValidateMessage.PRARM_ISDATESTR_ERRMSG;   
    
    //约束注解验证所属的组别
    Class<?>[] groups() default { }; 
     
   // 约束注解的有效负载
    Class<? extends Payload>[] payload() default { }; 
     
}