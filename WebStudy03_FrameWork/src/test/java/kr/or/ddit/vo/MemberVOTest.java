package kr.or.ddit.vo;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;
import org.junit.Test;

public class MemberVOTest {

	static Validator validator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		validator = Validation.byDefaultProvider()
		        .configure()
		        .messageInterpolator(
		                new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("kr.or.ddit.msgs.errorMessage"))
		        )
		        .buildValidatorFactory()
		        .getValidator();
	}

	@Test
	public void testBundle() {
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.msgs.message", Locale.ENGLISH);
		System.out.println(bundle.getString("hi"));
	}
	
	@Test
	public void test() {
		MemberVO target = new MemberVO();
//		target.setMemId("a001");
//		target.setMemPass("ab");
//		target.setMemMail("abcd");
//		target.setMemRegno1("134");
		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target, Default.class);
		for(ConstraintViolation<MemberVO> singleViolation : violations) {
			String property = singleViolation.getPropertyPath().toString();
			String message = singleViolation.getMessage();
			System.out.printf("%s - %s \n", property, message);
		}
	}

}
