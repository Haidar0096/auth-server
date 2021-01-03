package com.nlimits.authserver.application.user.domain.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

import static com.nlimits.authserver.application.user.domain.annotation.PasswordString.PasswordStringAnnotationConfig.defaultMessage;
import static com.nlimits.authserver.application.user.domain.annotation.PasswordString.PasswordStringAnnotationConfig.max;
import static com.nlimits.authserver.application.user.domain.annotation.PasswordString.PasswordStringAnnotationConfig.min;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = min, max = max, message = defaultMessage)
@Constraint(validatedBy = {})
public @interface PasswordString {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class PasswordStringAnnotationConfig {
        public static final int min = 6;
        public static final int max = 100;
        public static final String defaultMessage = "Password must be between " + min + " and " + max + " characters";
    }
}
