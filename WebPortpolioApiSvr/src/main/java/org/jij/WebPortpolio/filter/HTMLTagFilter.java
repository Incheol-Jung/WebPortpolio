/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestMobileWeb
 * 2. 패키지명		: 
 * 3. 파일명		: 
 * 4. 작성일		: 
 * 5. 작성자		: 
 * 6. 설명			: Html Filter
 * </pre>
 */

package org.jij.WebPortpolio.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTMLTagFilter implements Filter {
	
	private FilterConfig filterConfig;
	
	/**
	 * 초기화
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
	}
	
	/**
	 * 삭제
	 */
	@Override
	public void destroy() {
		
	}
	 
	/**
	 * 클라이언트 요청 및 응답을 변경할 수 있는 함수
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		System.out.println("[sjc]>>>>>>[" + "1111111]");
		if (isAjaxRequest(httpRequest)) {	// ajax
			chain.doFilter(new HTMLTagFilterRequestWrapper(httpRequest), response);
		} else {
			httpResponse.setContentType("text/html; charset=UTF-8");
			httpResponse.setHeader("X-UA-Compatible", "IE=Edge");	// IE 최신 버전 사용
			//httpResponse.setHeader("X-Frame-Options", "DENY");		// 교차 프레임 방어 : DENY - frame 표시할 수 없음 / SAMEORIGIN - 동일한 orgin에 해당 / ALLOW-FROM - 지정된 orgin에 해당
			httpResponse.setHeader("Cache-Control", "no-cache");
			httpResponse.setHeader("Cache-Control", "no-cache");
			httpResponse.setHeader("Pragma", "no-cache");
			
			chain.doFilter(new HTMLTagFilterRequestWrapper(httpRequest), response);
		}
	}
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
	
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
	
	/**
	 * Ajax 통신인지 확인
	 * ContextType으로 판별(application/json)
	 */
	private boolean isAjaxRequest(HttpServletRequest request) throws IOException {
		boolean returnBool = false;
		
		String requestContextType = (request.getContentType() != null ? request.getContentType() : "");
		if (requestContextType.startsWith("application/json")) {
			returnBool = true;
		}
		
		return returnBool;
	}
}
