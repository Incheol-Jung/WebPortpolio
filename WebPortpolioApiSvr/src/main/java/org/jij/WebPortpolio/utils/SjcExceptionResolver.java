/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestApiSrv
 * 2. 패키지명		: org.jij.WebPortpolio.utils
 * 3. 파일명		: SjcExceptionResolver.java
 * 4. 작성일		: 2015. 11. 23.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.utils;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class SjcExceptionResolver extends SimpleMappingExceptionResolver{
	
	private static Logger logger = LoggerFactory.getLogger(SjcExceptionResolver.class);
	
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) {

		logger.error(ex.getMessage(), ex);

		return super.doResolveException(request, response, handler, ex);
	}

}
