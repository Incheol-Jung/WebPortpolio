package org.jij.WebPortpolio.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;

public class HibernateAwareObjectMapper extends ObjectMapper {
 
	private static final long serialVersionUID = -5061324934452594321L;

	public HibernateAwareObjectMapper() {
		Hibernate4Module hm = new Hibernate4Module();
		hm.disable(Feature.USE_TRANSIENT_ANNOTATION);

        registerModule(hm);
    }
    
}
