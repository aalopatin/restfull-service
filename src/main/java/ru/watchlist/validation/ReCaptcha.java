package ru.watchlist.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ReCaptchaValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ReCaptcha {

    public String message() default "ReCaptcha.error";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default{};

}



