package kr.or.ddit.validate;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class ValidateUtils {
	static Validator validator;
	
	static {
		validator = Validation.byDefaultProvider()
		        .configure()
		        .messageInterpolator(
		                new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("kr.or.ddit.msgs.errorMessage"))
		        )
		        .buildValidatorFactory()
		        .getValidator();
	}
	
	public static <T> boolean validate(T target, Map<String, String> errors, Class...groups) {
		Set<ConstraintViolation<T>> violations = validator.validate(target, groups);
		boolean valid = violations == null || violations.size() == 0;
		
		for(ConstraintViolation<T> singleViolation : violations) {
			String property = singleViolation.getPropertyPath().toString();
			String message = singleViolation.getMessage();
			errors.put(property, message);
		}
		return valid;
	}
}
