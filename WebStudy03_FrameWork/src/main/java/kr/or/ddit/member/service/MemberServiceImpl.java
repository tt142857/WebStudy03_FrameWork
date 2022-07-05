package kr.or.ddit.member.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements MemberService {
	MemberDAO memberDao = new MemberDAOImpl();
	AuthenticateService authService = new AuthenticateServiceImpl();
	
	private final String CIPHER_NAME = "AES/CBC/PKCS5Padding";
	private final String KEY_NAME = "AES";
	private final int KEY_SIZE = 256;
	private final String ALGORITHM_NAME = "MD5";
	
	Cipher cipher;
	KeyGenerator keyGen;
	SecretKey key;
	MessageDigest md;
	IvParameterSpec ivSpec;
	
	{
		try {
			cipher = Cipher.getInstance(CIPHER_NAME);
			keyGen = KeyGenerator.getInstance(KEY_NAME);
			keyGen.init(KEY_SIZE);
			key = keyGen.generateKey();
			md = MessageDigest.getInstance(ALGORITHM_NAME);
			ivSpec = new IvParameterSpec(md.digest("password".getBytes()));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void encryptMember(MemberVO member) {
		String pass = member.getMemPass();
		String regno1 = member.getMemRegno1();
		String regno2 = member.getMemRegno2();
		
		String encodedPass = encryptOne(pass);
		String encodedRegno1 = encryptOne(regno1);
		String encodedRegno2 = encryptOne(regno2);
		
		member.setMemPass(encodedPass);
		member.setMemRegno1(encodedRegno1);
		member.setMemRegno2(encodedRegno2);
	}
	
	public void decryptMember(MemberVO member) {
		byte[] pass = member.getMemPass().getBytes();
		byte[] regno1 = member.getMemRegno1().getBytes();
		byte[] regno2 = member.getMemRegno2().getBytes();
		
		String decodedPass = decryptOne(pass);
		String decodedRegno1 = decryptOne(regno1);
		String decodedRegno2 = decryptOne(regno2);
		
		member.setMemPass(decodedPass);
		member.setMemRegno1(decodedRegno1);
		member.setMemRegno2(decodedRegno2);
	}
	
	public String encryptOne(String plain) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			throw new RuntimeException(e);
		}
		
		String encoded = null;
		try {
			byte[] input = plain.getBytes();
			byte[] encrypted = cipher.doFinal(input);
			encoded = Base64.getEncoder().encodeToString(encrypted);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
		return encoded;
	}

	public String decryptOne(byte[] encoded) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			throw new RuntimeException(e);
		}
		
		String decoded = null;
		try {
			byte[] input = Base64.getDecoder().decode(encoded);
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			byte[] decrypted = cipher.doFinal(input);
			decoded = new String(decrypted);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
		return decoded;
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if (memberDao.selectMemberForAuth(member) == null) {
			encryptMember(member);
			
			int rowcnt = memberDao.insertMember(member);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
		} else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) {
		pagingVO.setTotalRecord(memberDao.selectTotalRecord(pagingVO));
		List<MemberVO> memberList = memberDao.selectMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		return memberList;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = memberDao.selectMember(memId);
		if (member == null) {
			throw new PKNotFoundException(String.format("%s 아이디를 가진 회원이 없음", memId));
		}
		else {
			decryptMember(member);
		}
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		MemberVO inputData = new MemberVO();
		inputData.setMemId(member.getMemId());
		inputData.setMemPass(member.getMemPass());
		ServiceResult result = authService.authenticate(inputData);

		switch (result) {
		case NOTEXIST:
			throw new PKNotFoundException(String.format("%s에 해당하는 회원이 없음", member.getMemId()));
		case INVALIDPASSWORD:

			break;
		default:
			int rowcnt = memberDao.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
			break;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = authService.authenticate(member);
		
		switch (result) {
		case NOTEXIST:
			throw new PKNotFoundException(String.format("%s에 해당하는 회원이 없음", member.getMemId()));
		case INVALIDPASSWORD:

			break;
		default:
			int rowcnt = memberDao.deleteMember(member.getMemId());
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
			break;
		}
		return result;
	}
}
