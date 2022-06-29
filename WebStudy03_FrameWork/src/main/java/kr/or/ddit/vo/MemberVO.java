package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//DTO, marker interface : Serializable - marker annotation
/**
 * 회원 관리를 위한 Domain Layer
 * 한 사람의 회원에 대한 모든 정보를 가진 객체
 * 기본정보 + 구매기록(상품들)
 * 
 * Mybatis를 이용한 조인 방법
 * 1. 테이블 간의 관계성을 메인 테이블을 중심으로 파악
 *   ex) 한 명의 회원과 그 사람의 구매 기록 조회
 *     MEMBER(1) PROD(N) -> 1:N
 *     PROD(1) BUYER(1) -> 1:1
 * 2. 각 테이블의 스키마를 반영한 VO 생성
 *   MEMBER(MemberVO), PROD(ProdVO), BUYER(BuyerVO)
 * 3. 테이블 간의 관계성을 VO에 반영
 *   1:1 -> ProdVO has a BuyerVO 
 *   1:N -> MemberVO has many ProdVO
 * 4. 조인 쿼리 작성 -> resultType 대신 resultMap을 사용해 바인딩
 *   1:1 -> has a -> association으로 바인딩
 *   1:N -> has many -> collection으로 바인딩 -> id로 중복여부 판단 설정        
 */
@Data
@EqualsAndHashCode(of= {"memId", "memRegno1", "memRegno2"})
@ToString(exclude= {"memPass", "memRegno1", "memRegno2", "buyList"})
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
public class MemberVO implements Serializable {

	private String memId;
	private transient String memPass;
	private String memName;
	private transient String memRegno1;
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;

	//구매기록, 중복허용 X
	private Set<ProdVO> buyList; // has many
}
