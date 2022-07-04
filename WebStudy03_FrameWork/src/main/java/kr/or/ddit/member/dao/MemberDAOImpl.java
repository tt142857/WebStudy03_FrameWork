package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements MemberDAO {
	private SqlSessionFactory SqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		// 회원의 정보 조회 : id, password, name, hp, address
		// 해당 조건으로 검색시 존재하지 않으면 null 반환
		// 쿼리 객체 : Statement 객체 사용.
		try (	
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){			
			//return sqlSession.selectOne("selectMemberForAuth", inputData);
			// Mapper Proxy - ""를 직접쓰는 하드코딩을 하지 않게 도와줌
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMemberForAuth(inputData);
			// Proxy가 만들어지려면 Interface가 꼭 필요
			// 단점: 너무 많은 Proxy를 만들어야 함
		}
	}
	
	@Override
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectTotalRecord(pagingVO);
		}
	}
	
	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			//return sqlSession.selectList("selectMemberList");
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMemberList(pagingVO);
		}
	}
	
	@Override
	public int insertMember(MemberVO member) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			//return sqlSession.insert("inserMember", member);
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapperProxy.insertMember(member);
			//sqlSession.commit(); - 안쓰면 커밋이 안됨
			//sqlSession.rollback();
			return rowcnt;
		}
	}
	
	@Override
	public MemberVO selectMember(String memId) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			return sqlSession.selectOne("selectMember", memId);
		} 
	}
	
	@Override
	public int updateMember(MemberVO member) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapperProxy.updateMember(member);
			sqlSession.commit();
			return rowcnt;
		} 
	}
	
	@Override
	public int deleteMember(String memId) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapperProxy.deleteMember(memId);
			sqlSession.commit();
			return rowcnt;
		} 
	}
}




















