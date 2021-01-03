package com.nlimits.authserver.application.user.domain.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static com.nlimits.authserver.application.user.domain.annotation.EmailString.EmailStringAnnotationConfig.defaultMessage;

@Documented
@Constraint(validatedBy = EmailStringValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailString {
    String message() default defaultMessage;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class EmailStringAnnotationConfig {
        public static final String defaultMessage = "Invalid email address";
    }
}
