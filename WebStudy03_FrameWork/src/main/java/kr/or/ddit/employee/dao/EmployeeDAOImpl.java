package kr.or.ddit.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImpl implements EmployeeDAO {
	private SqlSessionFactory SqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<EmployeeVO> selectEmployeeList(int managerId) {
                                                                                                 
		List<EmployeeVO> empList = new ArrayList<>();
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			EmployeeDAO mapperProxy = sqlSession.getMapper(EmployeeDAO.class);
			return mapperProxy.selectEmployeeList(managerId);
		}
	}

}
