package microservices.training.locations.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import microservices.training.locations.model.Type;

public class CoordinateValidator implements ConstraintValidator<Coordinate, Double> {

    private Type type;

    @Override
    public void initialize(Coordinate constraintAnnotation) {
        type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (Type.LAT.equals(type)) {
            return latitudeIsValid(value);
        } else {
            return longitudeIsValid(value);
        }
    }

    private boolean longitudeIsValid(Double value) {
        return value >= -180L && value <=180L;
    }

    private boolean latitudeIsValid(Double value) {
        return value >= -90L && value <=90L;
    }
}
