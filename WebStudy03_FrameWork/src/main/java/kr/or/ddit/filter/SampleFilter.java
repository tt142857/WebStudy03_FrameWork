package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleFilter implements Filter {
	// private static final Logger log = LoggerFactory.getLogger(SampleFilter.class);
	// lombok으로 @Slf4j을 쓰면 위 변수를 자동생성해 줌
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 필터 초기화", this.getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		log.info("{} 요청 필터링 함", uri);
		chain.doFilter(request, response); // 현재 필터에서 다음 순서의 필터로 제어권이 전달이 됨
		log.info("{} 응답 필터링", response.getContentType());
	}

	@Override
	public void destroy() {
		log.info("{} 필터 소멸", this.getClass().getSimpleName());
		
	}

}
