package kr.or.ddit.employee.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.fancytree.FancyTreeNode;

public class EmployeeServiceTest {

	EmployeeService service =new EmployeeServiceImpl();
	
	@Test
	public void testRetrieveEmployeeList() {
		List<FancyTreeNode<EmployeeVO>> nodeList = service.retrieveEmployeeList(100);
		nodeList.stream().forEach(System.out::println);
	}

}
