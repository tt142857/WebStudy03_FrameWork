package kr.or.ddit.security;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtils {
	public static boolean matche(String plain, String savedData) {
		String encoded = encryptSha512(plain);
		return savedData.equals(encoded);
	}

	public static String encryptSha512(String plain) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] encrypted = md.digest(plain.getBytes()); // md에 설정된 SHA-512 방식으로 plain(평문)을 암호화함
			System.out.println(encrypted.length * 8);
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			System.out.printf("평문 : %s, 암호문 : %s\n", plain, encoded);

			return encoded;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
