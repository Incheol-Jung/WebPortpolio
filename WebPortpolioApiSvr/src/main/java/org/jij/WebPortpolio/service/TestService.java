/**
 * 
 * <pre>
 * 1. 프로젝트명	: WebPortpolioApiSvr
 * 2. 패키지명		: org.jij.WebPortpolio.service
 * 3. 파일명		: TestService.java
 * 4. 작성일		: 2016. 2. 17.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.jij.WebPortpolio.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
	private static Logger logger = Logger.getLogger(TestService.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Test> getTestList(){
		logger.debug("getTestList");	
		Query qry = sessionFactory.getCurrentSession().getNamedQuery("test.getTestList");
				
		List<Test> list = qry.list();
		
		return list;
	}
}
