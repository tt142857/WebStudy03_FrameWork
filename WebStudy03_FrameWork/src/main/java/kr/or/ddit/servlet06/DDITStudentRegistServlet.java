package kr.or.ddit.servlet06;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.DDITStudentVO;

@WebServlet("/07/dditProcess.do")
public class DDITStudentRegistServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		1. 요청 분석- controller
//			1) 요청 검증
//				- 파라미터 확보
//				- 검증
//				- 통과
//					2) 요청 컨텐츠(model) 생성
//						-> 등록 완료 메시지를 가지고(/07/resultView.jsp 로 이동.)
//				- 불통
//					3) 입력 UI 로 복귀 (메시지, 기존 클라이언트 입력 값. )
//		2. 응답 생성- view
		req.setCharacterEncoding("UTF-8");
		DDITStudentVO vo = new DDITStudentVO();
		req.setAttribute("student", vo);
/*		vo.setName(req.getParameter("name"));
		vo.setHp(req.getParameter("hp"));*/
		
		// 1. reflection 을 몰라도 파리미터를 Vo 로 바인딩하려면???
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(vo, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// reflection 기술은 불확실성을 기반으로 하기때문에 여러 예외에 대한 처리가 필요함.
			// 예외의 두가지 종류(checked/unchecked)중 
			// 예외에 대한 제어권이 servlet container 로 전달된 후 500 에러로 처리됨.
			throw new RuntimeException(e);
		}
	/*	for(String parameterName : parameterMap.keySet()) {
			Class<? extends DDITStudentVO> type = vo.getClass();
			try {
				Field field = type.getDeclaredField(parameterName);
				field.setAccessible(true);
				if(field.getType().equals(int.class)) {
					field.set(vo, Integer.parseInt(req.getParameter(parameterName)));
				}else if(field.getType().equals(String[].class)) {
					field.set(vo, req.getParameterValues(parameterName));
				}else {
					field.set(vo, req.getParameter(parameterName));
				}
				System.out.println(field);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}*/
		
		boolean valid = validata(vo);
		String view = null;
		String message = null;
		if(valid) {
			message = "등록 완료";
			req.getSession().setAttribute("student", vo);
			req.getSession().setAttribute("message", message);
			view = "redirect:/07/resultView.jsp";
		}else {
			message = "등록 실패, 검증 실패";
			view = "/07/registForm.jsp";
		}
		
		req.setAttribute("message", message);
		if(view.startsWith("redirect:")) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + view);
		}else {
			req.getRequestDispatcher(view).forward(req, resp);
		}
		
	}
	
	boolean validata(DDITStudentVO vo){
		boolean valid = true;
		//2. 문자열 데이터의 empty 여부를 쉽게 확인하려면???
		if(StringUtils.isBlank(vo.getName())) {
			valid = false;
		}
		if(StringUtils.isBlank(vo.getHp())) {
			valid = false;
		}
		return valid;
	}
}

























