package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 블라인드 처리 대상이 되는 사용자가 접촉한 경우,
 * 해당 요청에 대해 직접 처리하지 않고 접근 불가 메시지 페이지로 연결함
 * 
 */
// 안쓰는게 좋음
//@WebFilter("/*")
@Slf4j
public class BlindFilter implements Filter {

	private Map<String, String> blindTarget;
	
	public void init(FilterConfig fConfig) throws ServletException {
		blindTarget = new LinkedHashMap<>();
		// IP, reason
		blindTarget.put("192.168.36.41", "나니까");
		blindTarget.put("192.168.36.1", "그냥");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// 1. IP 확보
		String ip = request.getRemoteAddr();
		String uri = req.getRequestURI();
		boolean pass = uri.endsWith("/errors/blindError.jsp");
		
		// 2. 블라인드 대상자 여부 결정
		boolean blind = !pass && blindTarget.containsKey(ip);
		if(blind) {
			// 3. 대상자 : blindError 페이지에 연결
			String reason = blindTarget.get(ip);
			
			Map<String, String> target = new HashMap<>();
			target.put("IP", ip);
			target.put("reason", reason);
			
			req.getSession().setAttribute("target", target);
			String goPage = "/errors/blindError.jsp";
			
			((HttpServletResponse) response).sendRedirect(req.getContextPath() + goPage);
		} else {
			//    비대상자 : 통과
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		
	}
}


















