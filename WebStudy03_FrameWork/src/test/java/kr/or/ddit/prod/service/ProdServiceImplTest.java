package kr.or.ddit.prod.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SimpleSearchCondition;

public class ProdServiceImplTest {

	ProdService service = new ProdServiceImpl();
	
	/*@Test
	public void testRetrieveProdList() {
//		String searchType = "name";
//		String searchWord = "로숀";
		
		String searchType = "LPROD_NM";
		String searchWord = "남성";
		SimpleSearchCondition searchVO = new SimpleSearchCondition(searchType, searchWord);
		
		int currentPage = 1;
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(searchVO);
		System.out.println(searchVO);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		assertNotNull(prodList);
		System.out.println(prodList);
	}*/

	@Test
	public void testCreateProd() {
		ProdVO prod = new ProdVO();
		prod.setProdName("모니터 삼성전자35인치칼라");
		prod.setProdLgu("P101");
		prod.setProdBuyer("P10101");
		prod.setProdCost(2100000);
		prod.setProdPrice(2900000);
		prod.setProdSale(2300000);
		prod.setProdOutline("평면모니터의");
		prod.setProdDetail("우리기술의");
		prod.setProdTotalstock(0);
		prod.setProdProperstock(33);
		prod.setProdSize("35인치");
		prod.setProdDelivery("파손 주의");
		prod.setProdUnit("EA");
		prod.setProdQtyin(0);
		prod.setProdQtysale(0);
		prod.setProdMileage(1400);
		
		ServiceResult result = service.createProd(prod);
		assertNotNull(result);
		System.out.println(result);
	}
}
