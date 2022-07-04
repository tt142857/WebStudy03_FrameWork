package kr.or.ddit.validate.contraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 문자열 타입의 프로퍼티 하나의 값에 대한 검증
 *
 */
public class TelNumberValidator implements ConstraintValidator<TelNumber, String> {
	
	private TelNumber annotation;
	
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = value == null || value.isEmpty();
		if(!valid) {
			// 전화번호 입력(value) 형식(정규식)에 대한 검증
			String regexp = annotation.value();
			valid = value.matches(regexp);
		}
		return valid;
	}
}
