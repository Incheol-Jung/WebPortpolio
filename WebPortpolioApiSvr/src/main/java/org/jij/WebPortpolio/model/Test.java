/**
 * 
 * <pre>
 * 1. 프로젝트명	: WebPortpolioApiSvr
 * 2. 패키지명		: org.jij.WebPortpolio.model
 * 3. 파일명		: Test.java
 * 4. 작성일		: 2016. 2. 17.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name="test")
public class Test implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = -7322174122772845021L;
	
	public Test(){}
	
	public Test(int testId){
		this.testId = testId;
	}	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue
	@Column(name="test_id")
	private int testId;
	public int getTestId() {
		return testId;
	}

	@Column(name="test_name")
	private String testName;
	public String getTestName() {
		return testName;
	}

	@Column(name="test_age")
	private int testAge;
	public int getTestAge() {
		return testAge;
	}

	@Column(name="test_active")
	private boolean testActive;
	public boolean isTestActive() {
		return testActive;
	}

	@Column(name="createdon")
	private Date 		createdOn;
	public Date getCreatedOn() {
		return createdOn;
	}

	@Column(name="createdby")
	private String 		createdBy;
	public String getCreatedBy() {
		return createdBy;
	}

	@Column(name="modifiedon")
	private Date 		modifiedOn;
	public Date getModifiedOn() {
		return modifiedOn;
	}

	@Column(name="modifiedby")
	private String		modifiedBy;
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
	
	
}
