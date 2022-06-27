package kr.or.ddit.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/jsonView.do")
public class JsonMarshallingViewServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=UTF-8");
//		1. 마샬링의 타겟. scope
		Enumeration<String> attrNames = req.getAttributeNames();
		Map<String, Object> target = new HashMap<>();
		while (attrNames.hasMoreElements()) {
			String attName = (String) attrNames.nextElement(); // key
			Object value = req.getAttribute(attName); // value
			target.put(attName, value);
		}
//		2. 마샬링
//		3. 직렬화
		ObjectMapper mapper = new ObjectMapper();
//		try~with~resource 문법 구조(1.7)
		try(
			// closable 객체 생성 코드 -> 자동 close 됨.
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, target);
		}
	}
}












