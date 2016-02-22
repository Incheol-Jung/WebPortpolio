/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestApiSrv
 * 2. 패키지명		: org.jij.WebPortpolio.api
 * 3. 파일명		: CountryController.java
 * 4. 작성일		: 2015. 12. 22.
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.jij.WebPortpolio.model.City;
import org.jij.WebPortpolio.model.Country;
import org.jij.WebPortpolio.model.JsonResponse;
import org.jij.WebPortpolio.model.MapCountryCity;

import org.jij.WebPortpolio.service.CountryService;

@Controller
public class CountryController {
	private static Logger logger = Logger.getLogger(CountryController.class);
	
	@Autowired
	private CountryService countryService;
	
	public CountryController(){
		logger.debug("Create CountryController");		
	}
	
	/**
	 * 
	 * @메소드명	getMain
	 * @작성자		sjc
	 * @작성일		2015. 12. 22.
	 * @param   	
	 * @return		
	 * @설명 		
	 */
	@RequestMapping(value = "/Country", method = RequestMethod.GET)//@PathVariable
	public @ResponseBody JsonResponse getCountryList() {	
		logger.debug("/CountryController classed.");
		
		JsonResponse response = new JsonResponse();
		response.setSuccess(true);		
		List<Country> countryList = new ArrayList<Country>();
		try{			
			countryList = countryService.getCountryList();			
		}catch(Exception e){
			System.out.println("[sjc]>>>>>>[" + e.toString()+"]");
		}
		response.setData(countryList);
		return response;
	}	
	
	@RequestMapping(value = "/City", method = RequestMethod.GET)//@PathVariable
	public @ResponseBody JsonResponse getCityList() {	
		logger.debug("/CountryController classed.");
		
		JsonResponse response = new JsonResponse();
		response.setSuccess(true);		
		List<City> cityList = new ArrayList<City>();
		try{		
			cityList = countryService.getCityList();						
		}catch(Exception e){
			System.out.println("[sjc]>>>>>>[" + e.toString()+"]");
		}
		response.setData(cityList);
		return response;
	}
}
