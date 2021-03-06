package ru.watchlist.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueFieldValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(UniqueFieldContainer.class)
public @interface UniqueField {

    public String message() default "UniqueField.error";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default{};

    public UniqueFields fieldName();

}
