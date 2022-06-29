package kr.or.ddit.reflection;

/**
 * Reflection이란,
 *	 : 객체로부터 객체의 구성 요소, 특징, 속성, 형태들을 찾아가는 과정 - java.lang.reflect
 *
 *
 */
public class ReflectionDesc {
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		Class type = obj.getClass();
		type.getFileds();
	}
}
