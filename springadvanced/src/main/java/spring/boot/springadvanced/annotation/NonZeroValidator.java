package spring.boot.springadvanced.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NonZeroValidator implements ConstraintValidator<NonZero, Double> {


  @Override
  public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
    return aDouble != 0;
  }


}
