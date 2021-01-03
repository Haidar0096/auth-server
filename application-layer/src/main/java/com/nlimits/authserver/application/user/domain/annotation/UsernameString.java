package com.nlimits.authserver.application.user.domain.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

import static com.nlimits.authserver.application.user.domain.annotation.UsernameString.UsernameStringAnnotationConfig.defaultMessage;
import static com.nlimits.authserver.application.user.domain.annotation.UsernameString.UsernameStringAnnotationConfig.max;
import static com.nlimits.authserver.application.user.domain.annotation.UsernameString.UsernameStringAnnotationConfig.min;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = min, max = max, message = defaultMessage)
@Constraint(validatedBy = {})
public @interface UsernameString {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UsernameStringAnnotationConfig {
        public static final int min = 1;
        public static final int max = 100;
        public static final String defaultMessage = "Username must be between " + min + " and " + max + " characters";
    }
}
