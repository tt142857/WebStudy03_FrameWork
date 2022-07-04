package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 상품에 관한 정보를 가진 Domain Layer
 */
@Data
@EqualsAndHashCode(of= {"prodId"})
@ToString(exclude= {"prodDetail"})
public class ProdVO implements Serializable {
	
	private int rnum;
	
	private String prodId;
	private String prodName;
	private String prodLgu;
	private String prodBuyer;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
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
