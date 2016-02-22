/**
 * 
 * <pre>
 * 1. 프로젝트명	: WebPortpolioApiSvr
 * 2. 패키지명		: org.jij.WebPortpolio.api
 * 3. 파일명		: TestController.java
 * 4. 작성일		: 2016. 2. 17.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jij.WebPortpolio.model.Country;
import org.jij.WebPortpolio.model.JsonResponse;
import org.jij.WebPortpolio.model.Test;
import org.jij.WebPortpolio.service.CountryService;
import org.jij.WebPortpolio.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	private static Logger logger = Logger.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;

	public TestController(){
		logger.debug("Create TestController");		
	}
	
	@RequestMapping(value = "/Test", method = RequestMethod.GET)//@PathVariable
	public @ResponseBody JsonResponse getCountryList() {	
		logger.debug("/TestController classed.");
		
		JsonResponse response = new JsonResponse();
		response.setSuccess(true);		
		List<Test> testList = new ArrayList<Test>();
		try{			
			testList = testService.getTestList();			
		}catch(Exception e){
			System.out.println("[sjc]>>>>>>[" + e.toString()+"]");
		}
		response.setData(testList);
		return response;
	} 
}
