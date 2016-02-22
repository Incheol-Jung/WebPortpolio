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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jij.WebPortpolio.model.JsonResponse;
import org.jij.WebPortpolio.model.Test;
import org.jij.WebPortpolio.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/TestCreate/{testName}/{testAge}/{testActive}")
	public @ResponseBody JsonResponse createTest(
			@PathVariable("testName") String testName,
			@PathVariable("testAge") int testAge,
			@PathVariable("testActive") String testActive
			) {	
		JsonResponse response = new JsonResponse();
		response.setSuccess(true);	
		System.out.println("[sjc]>>>>>>[" + testName+"]");
		System.out.println("[sjc]>>>>>>[" + testAge+"]");
		System.out.println("[sjc]>>>>>>[" + testActive+"]");
		return response;
	}
	
	@RequestMapping(value = "/TestRead")
	public @ResponseBody JsonResponse createReadAll() {	
		logger.debug("/createReadAll classed.");
		
		JsonResponse response = new JsonResponse();
		response.setSuccess(true);		
		List<Test> testList = new ArrayList<Test>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try{			
			testList = testService.getTestListAll();			
		}catch(Exception e){
			System.out.println("[sjc]>>>>>>[" + e.toString()+"]");
		}
		response.setData(testList);
		return response;
	}
	
	
	@RequestMapping(value = "/TestRead/{testId}")
	public @ResponseBody JsonResponse createReadDetail(
			@PathVariable("testId") int testId
			) {	
		logger.debug("/createReadDetail classed.");
		
		JsonResponse response = new JsonResponse();
		response.setSuccess(true);		
		List<Test> testList = new ArrayList<Test>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try{			
			paramMap.put("testId", testId);
			testList = testService.getTestListDetail(paramMap);			
		}catch(Exception e){
			System.out.println("[sjc]>>>>>>[" + e.toString()+"]");
		}
		response.setData(testList);
		return response;
	}
	
	@RequestMapping(value = "/TestUpdate")
	public void createUpdate() {	
		
	}
	
	@RequestMapping(value = "/TestDelete")
	public void createDelete() {	
		
	}
}
