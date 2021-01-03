package com.nlimits.authserver.application.user.domain.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

import static com.nlimits.authserver.application.user.domain.annotation.NumericId.NumericIdAnnotationConfig.defaultMessage;
import static com.nlimits.authserver.application.user.domain.annotation.NumericId.NumericIdAnnotationConfig.min;


@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Min(value = min, message = defaultMessage)
public @interface NumericId {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class NumericIdAnnotationConfig {
        public static final int min = 0;
        public static final String defaultMessage = "Id must be greater than or equal to " + min;
    }
}
