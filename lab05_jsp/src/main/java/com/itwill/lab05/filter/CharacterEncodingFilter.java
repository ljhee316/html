package com.itwill.lab05.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;

public class CharacterEncodingFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;
	private String encoding;
	private static final Logger log = LoggerFactory.getLogger(CharacterEncodingFilter.class);

	
	//WAS기 filter객체를 생성한후 filter초기화작업을 하기위해서 호출하는 메서드
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//web.xml에서 <filter>의 <param-name>값을 아규먼트로 전달하면, <filter>으 <param-value> 값을 리턴해줌.
		encoding= filterConfig.getInitParameter("encoding");  // xml 파일의 <param-name>
		log.debug("init: encoding={}", encoding);
	}
	
	
	//필터체인->서블릿  으로 진행하기 위해서 WAS가 호출하는 메서드
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//요청 객체의 문자열 인코딩 타입을 UTF-8로 설정
		request.setCharacterEncoding(encoding);
		//다음 필터체인 진행 -> 서블릿 메서드(doGet, doPost) 호출
		chain.doFilter(request, response);
	}
	
}
