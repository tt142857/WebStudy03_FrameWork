package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 상품에 관한 정보를 가진 Domain Layer
 */
@Data
@EqualsAndHashCode(of = { "prodId" })
@ToString(exclude = { "prodDetail" })
public class ProdVO implements Serializable {

	private int rnum;

	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String prodId;
	@NotBlank(message="상품명은 필수 입력 데이터", groups= {Default.class, InsertGroup.class})
	private String prodName;
	@NotBlank(groups=InsertGroup.class)
	private String prodLgu;
	@NotBlank(groups=InsertGroup.class)
	private String prodBuyer;
	private Integer prodCost;
	
	@Min(0)
	private Integer prodPrice;
	private Integer prodSale;
	@NotBlank
	private String prodOutline;
	private String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;

	private BuyerVO buyer;

	private String lprodNm;

	private Set<MemberVO> memberSet;
}
