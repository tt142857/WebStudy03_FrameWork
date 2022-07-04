package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	ProdDAO prodDAO = new ProdDAOImpl();

	@Override
	public List<ProdVO> retrieveProdList(PagingVO pagingVO) {
		pagingVO.setTotalRecord(prodDAO.selectTotalRecord(pagingVO));
		List<ProdVO> prodList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDataList(prodList);
		return prodList;
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = prodDAO.selectProd(prodId);
		if (prod == null) {
			throw new PKNotFoundException(String.format("%s 상품이 없음", prodId));
		}
		return prod;
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		int rowcnt = prodDAO.insertProd(prod);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

}
