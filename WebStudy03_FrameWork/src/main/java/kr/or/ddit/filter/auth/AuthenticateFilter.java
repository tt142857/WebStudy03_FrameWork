package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 보안 : Authentication(인증, 신원확인) + Authorization(인가, 권한확인)
 * 
 * 보호자원을 접근하려는 사용자가 인증된 사용자인지 확인
 * 
 */
public class AuthenticateFilter implements Filter {

	private Map<String, String[]> securedResources; // 보호자원이 여러개일 수 있으므로 배열로 지정함
	private ServletContext application;
	
	public static final String SECUREDRESOURCENAME = "securedResources";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 보호자원 정보 로딩
		securedResources = new LinkedHashMap<>();
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.SecuredResources");
		Enumeration<String> keys = bundle.getKeys();

		//? 솔트 얻다씀
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String role = bundle.getString(key);
			String splitStr[] = role.split(",");
			securedResources.put(key, splitStr);
		}
		
		application = filterConfig.getServletContext();
		application.setAttribute(SECUREDRESOURCENAME, securedResources);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. 사용자의 요청 자원이 보호자원인지 여부 확인
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI().replace(req.getContextPath(), "");
		uri = uri.split(";")[0];
		boolean secureFlag = securedResources.containsKey(uri);
		boolean pass = false;
		if(secureFlag) {
			Principal principal = req.getUserPrincipal(); // 흠..

			// 2-1. 보호자원
				// 3. 사용자의 인증 여부 확인
			if(principal != null) {
				// 4-1. 인증 : 통과
				pass = true;
			}
			// 4-2. 미인증 : 인증을 하고 다시 들어올 수 있는 구조로 돌려보냄(로그인폼)
		} else {
			// 2-2. 비보호자원(통과, 그다음 필터로 넘김)
			pass = true;
		}
		if(pass) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
		}
	}

	@Override
	public void destroy() {
		
	}
	
}
