package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {

	ProdDAO dao = new ProdDAOImpl();

	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
		System.out.println(prod);
	}
	
	@Test
	public void testSelectProdList() {
		List<ProdVO> prodList = dao.selectProdList(pagingVO);
		assertNotNull(prodList);
		System.out.println(prodList.get(1).getProdInsdate());
	}
}
