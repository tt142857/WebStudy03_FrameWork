package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.contraints.TelNumber;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//DTO, marker interface : Serializable - marker annotation
/**
 * 회원 관리를 위한 Domain Layer 한 사람의 회원에 대한 모든 정보를 가진 객체 기본정보 + 구매기록(상품들)
 * 
 * Mybatis를 이용한 조인 방법 1. 테이블 간의 관계성을 메인 테이블을 중심으로 파악 ex) 한 명의 회원과 그 사람의 구매 기록 조회
 * MEMBER(1) PROD(N) -> 1:N PROD(1) BUYER(1) -> 1:1 2. 각 테이블의 스키마를 반영한 VO 생성
 * MEMBER(MemberVO), PROD(ProdVO), BUYER(BuyerVO) 3. 테이블 간의 관계성을 VO에 반영 1:1 ->
 * ProdVO has a BuyerVO 1:N -> MemberVO has many ProdVO 4. 조인 쿼리 작성 ->
 * resultType 대신 resultMap을 사용해 바인딩 1:1 -> has a -> association으로 바인딩 1:N -> has
 * many -> collection으로 바인딩 -> id로 중복여부 판단 설정
 */
@Data
@EqualsAndHashCode(of = { "memId", "memRegno1", "memRegno2" })
@ToString(exclude = { "memPass", "memRegno1", "memRegno2", "buyList" })
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberVO implements Serializable {

	private int rnum;

	@NotBlank(message="아이디는 필수 입력 데이터", groups= {Default.class, DeleteGroup.class})
	private String memId;
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	@Size(min=4, max=12, message="비밀번호는 4글자에서 12글자 사이", groups= {Default.class, DeleteGroup.class} )
	private String memPass;
	@NotBlank
	private String memName;
	@NotBlank(groups=InsertGroup.class)
	@Size(min=6, max=6, groups=InsertGroup.class)
	private String memRegno1;
	@NotBlank(groups=InsertGroup.class)
	@Size(min=7, max=7, groups=InsertGroup.class)
	private String memRegno2;
	@NotBlank
	private String memBir;
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	
	//@Pattern(regexp="\\d{2,3}-\\d{3,4}-\\d{4}")
	@TelNumber // value라는 속성명은 생략할 수 있음
	private String memHometel;
	@Pattern(regexp="\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memComtel;
	@NotBlank
	//@Pattern(regexp="\\d{3}-\\d{3,4}-\\d{4}")
	@TelNumber("\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memHp;
	
	@NotBlank
	@Email(message="이메일 계정 확인")
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;

	// 구매기록, 중복허용 X
	private Set<ProdVO> buyList; // has many
}
