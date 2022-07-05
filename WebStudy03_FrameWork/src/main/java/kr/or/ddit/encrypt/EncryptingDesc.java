package kr.or.ddit.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Encrypting(암호화) : 데이터 보호를 목적으로 허가받지 않은 사용자가 읽을 수 없는 
 * 					   표현방식으로 데이터 표현을 바꾸는 과정
 *   단방향 암호화(hash 함수) : 비밀번호 암호화에 사용 ex) SHA-512
 *   hash ?? 입력데이터에 대해 일정 길이로 만들어지는 코드값 
 *   
 *   양방향 암호화 : 전송 데이터 보호에 사용, 전자 서명에 사용 (Java Crypto Library - Cipher)
 *   	- 대칭키 방식(비밀키 방식) : 하나의 비밀키로 암호화와 복호화를 수행
 *   	- 비대칭키 방식(공개키 방식) : 한쌍의 키(공개키/개인키)로 암복호화 수행 ex) RSA
 * Encoding(부호화)/Decoding : 데이터를 전송하거나 저장하기 위해 사용할 매체가 인지할 수 있는 
 * 							  표현방식으로 데이터의 표현을 바꾸는 과정
 *   ex) URLEncdoing(Percent Encoding), Base64
 */
public class EncryptingDesc {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		String plain = "java";
		
		//encryptSha512(plain);
		encrypteAESExample(plain);
		//encryptRSAExample(plain);
	}
	
	/**
	 * SHA-512 알고리즘으로 단방향 암호화 후 Base64로 인코딩함
	 * @param plain
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptSha512(String plain) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512"); 
		byte[] encrypted = md.digest(plain.getBytes()); // md에 설정된 SHA-512 방식으로 plain(평문)을 암호화함
		System.out.println(encrypted.length * 8);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.printf("평문 : %s, 암호문 : %s\n", plain, encoded);
	
		return encoded;
	}
	
	public static void encrypteAESExample(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256); // 키 길이 128 = 16byte
		SecretKey key = keyGen.generateKey();
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] iv = md.digest("password".getBytes()); // 어떤 값이 들어가든 128비트로 공간이 생성됨 hash니까.
		IvParameterSpec ivSpec = new IvParameterSpec(iv); // Initialization vector : 초기화
		
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.printf("암호문 : %s\n", encoded);
		
		byte[] decoded = Base64.getDecoder().decode(encoded); // =encrypted
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		byte[] decrypted = cipher.doFinal(decoded); // =input
		System.out.printf("복호화된 평문 : %s\n", new String(decrypted));
	}
	
	public static void encryptRSAExample(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA"); // RSA는 안전하지만 속도가 느리다
		keyPairGen.initialize(2048);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.printf("암호문 : %s\n", encoded);
		
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decoded = Base64.getDecoder().decode(encoded);
		byte[] decrypted = cipher.doFinal(decoded);
		System.out.println("복원된 평문 : " + new String(decrypted));
	}
}
