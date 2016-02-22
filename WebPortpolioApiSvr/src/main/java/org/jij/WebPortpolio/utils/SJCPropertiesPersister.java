/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestApiSrv
 * 2. 패키지명		: org.jij.WebPortpolio.utils
 * 3. 파일명		: SJCPropertiesPersister.java
 * 4. 작성일		: 2015. 11. 24.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DefaultPropertiesPersister;
import org.xml.sax.XMLReader;

public class SJCPropertiesPersister extends DefaultPropertiesPersister{
	  private static Logger logger = LoggerFactory.getLogger(SJCPropertiesPersister.class);

	  //private static DataSourcePropertyEncryptor encryptor = new DataSourcePropertyEncryptor();

	  public void loadFromXml(Properties properties, InputStream inputstream)
	    throws IOException
	  {
	    logger.debug("SJCPropertiesPersister loadFromXml InputStream");	    
	    try {
	      SAXReader saxReader = new SAXReader();
	      setAllowJavaEncoding(saxReader.getXMLReader());
	      Element element = saxReader.read(inputstream).getRootElement();

	      Map propMap = new HashMap();

	      properties.putAll(getMapDataFromElement(propMap, "", element));

	      logger.debug("properties :" + properties.toString());
	    } catch (Exception e) {
	      throw new IOException(e.getMessage(), e);
	    }
	  }

	  private void setAllowJavaEncoding(XMLReader xmlReader) {		  
	    try {
	      xmlReader.setFeature("http://apache.org/xml/features/allow-java-encodings", true);
	    } catch (Exception e) {
	      if (logger.isInfoEnabled())
	        logger.info("failed to set feature( allow-java-encodings) caused of " + e, e);
	    }
	    catch (AbstractMethodError error) {
	      if (logger.isInfoEnabled())
	        logger.info("feature( allow-java-encodings) is not supported. use xerces 2.8 or above, caused of " + 
	          error, error);
	    }
	  }

	  private Map<String, String> getMapDataFromElement(Map<String, String> map, String pStr, Element element)
	  {
	    Attribute attribute = null;
	    int i = 0; for (int size = element.nodeCount(); i < size; ++i) {
	      Node node = element.node(i);

	      if (node.getNodeType() == 1) {
	        if (StringUtils.isNotEmpty(node.getText().trim())) {
	          attribute = ((Element)node).attribute("encrypt");
	          if ((attribute != null) && (attribute.getData().equals("true")))
	            try {
	              //map.put(pStr + node.getName(), encryptor.decrypt(node.getText()));
	            	map.put(pStr + node.getName(), node.getText());
	            } catch (Exception e) {
	              logger.error("Property " + node.getName() + " decrypt error", e);
	              map.put(pStr + node.getName(), node.getText());
	            }
	          else {
	            map.put(pStr + node.getName(), node.getText());
	          }
	        }
	        getMapDataFromElement(map, pStr + node.getName() + ".", (Element)node);
	      }
	    }

	    return map;
	  }
}
