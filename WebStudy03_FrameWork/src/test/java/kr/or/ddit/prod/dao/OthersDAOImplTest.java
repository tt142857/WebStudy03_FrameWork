package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImplTest {
	OthersDAO dao = new OthersDAOImpl();
	
	@Test
	public void testSelectLprodList() {
		List<Map<String, Object>> list = dao.selectLprodList();
		assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void testSelectBuyerList() {
		List<BuyerVO> list = dao.selectBuyerList();
		assertNotNull(list);
		System.out.println(list);
	}
}
