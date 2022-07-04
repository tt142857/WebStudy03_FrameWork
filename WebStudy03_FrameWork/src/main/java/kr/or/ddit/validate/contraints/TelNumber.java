package kr.or.ddit.validate.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 어노테이션 : 사람과 시스템에 동시에 정보를 전달하기 위한 주석의 한 형태
 *   커스텀 어노테이션의 필수 정책
 *     1. @Target : 어노테이션의 사용 위치 설정
 *     2. @Retention : 어노테이션의 사용(생존) 범위 설정, (SOURCE, CLASS, RUNTIME)
 * 어노테이션의 3가지 사용형태
 *   1. Marker annotation  ex) @TelNumber - 필수 속성이 없을 때
 *   2. Single value annotation  ex) @TelNumber("text") - value라는 이름을 가진 속성에 한해 사용되는 형태
 *   3. Multi value annotation  ex) @TelNumber(value="text", message="text) value 이외의 모든 속성을 이름이 반드시 필요함
 *   
 */
@Target(ElementType.FIELD) // 메타 어노테이션,
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TelNumberValidator.class)
public @interface TelNumber {
	String value() default "\\d{2,3}-\\d{3,4}-\\d{4}";
	
	String message() default "{kr.or.ddit.validate.contraints.TelNumber.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
