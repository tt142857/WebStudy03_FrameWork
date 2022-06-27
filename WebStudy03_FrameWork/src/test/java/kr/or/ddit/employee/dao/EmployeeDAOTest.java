package kr.or.ddit.employee.dao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.vo.EmployeeVO;

import org.junit.Test;

public class EmployeeDAOTest {

	EmployeeDAO dao = new EmployeeDAOImpl();
	
	@Test
	public void test() {
		List<EmployeeVO> empList = dao.selectEmployeeList(100);
		assertNotNull(empList);
		assertNotEquals(0, empList.size());
		System.out.println(empList);
	}

}
