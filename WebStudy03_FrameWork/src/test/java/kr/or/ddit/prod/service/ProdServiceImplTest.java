package kr.or.ddit.prod.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SimpleSearchCondition;

public class ProdServiceImplTest {

	ProdService service = new ProdServiceImpl();
	
	@Test
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
	}

}
