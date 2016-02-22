/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestApiSrv
 * 2. 패키지명		: org.jij.WebPortpolio.service
 * 3. 파일명		: CountryService.java
 * 4. 작성일		: 2015. 12. 22.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.service;

import java.util.List;

import javax.persistence.Entity;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.jij.WebPortpolio.model.City;
import org.jij.WebPortpolio.model.Country;
import org.jij.WebPortpolio.model.MapCountryCity;

@Service
public class CountryService {
	private static Logger logger = Logger.getLogger(CountryService.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Country> getCountryList(){
		logger.debug("getCountryList");		
		
		Query qry = sessionFactory.getCurrentSession()
				.getNamedQuery("country.countryList");
		
		List<Country> c = qry.list(); 
		
		for(Country x : c){
			Hibernate.initialize(x.getCity());			
		}		
		return c;
	}
		
	@SuppressWarnings("unchecked")
	@Transactional
	public List<City> getCityList(){
		logger.debug("getCityList");		
		
		Query qry = sessionFactory.getCurrentSession()
				.getNamedQuery("country.cityList");		
		
		List<City> c = qry.list(); 
		
		for(City x : c){
			Hibernate.initialize(x.getCountry());			
		}		
		return qry.list();
	}
}
