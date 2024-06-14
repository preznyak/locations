package microservices.training.locations.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import microservices.training.locations.model.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = CoordinateValidator.class)
public @interface Coordinate {

    String message() default "Invalid coordinate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Type type();
}
