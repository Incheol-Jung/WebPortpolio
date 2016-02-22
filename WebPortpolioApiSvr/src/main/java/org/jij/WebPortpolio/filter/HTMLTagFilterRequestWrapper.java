/**
 * 
 * <pre>
 * 1. 프로젝트명	: Kanola
 * 2. 패키지명		: com.kanola.common.filter
 * 3. 파일명		: HTMLTagFilterRequestWrapper.java
 * 4. 작성일		: 2015. 2. 4. 오전 11:25:41
 * 5. 작성자		: 김성완
 * 6. 설명		: ServletRequest 재 정의(getParameterValues / getParameter)
 * </pre>
 */
package org.jij.WebPortpolio.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {
	
	public HTMLTagFilterRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	 
	/**
	 * 재 정의(재 정의한 내역이 없이 바로 부모 호출)
	 * 필요 시 재 정의
	 */
	public String[] getParameterValues(String parameter) {
		return super.getParameterValues(parameter);
	}
	
	/**
	 * 재 정의(재 정의한 내역이 없이 바로 부모 호출)
	 * 필요 시 재 정의
	 */
	public String getParameter(String parameter) {
		return super.getParameter(parameter);
	}
}
