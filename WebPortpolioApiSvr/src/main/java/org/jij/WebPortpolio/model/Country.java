/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestApiSrv
 * 2. 패키지명		: org.jij.WebPortpolio.model.main
 * 3. 파일명		: Country.java
 * 4. 작성일		: 2015. 12. 22.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Country")
public class Country implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 8225507227331805833L;

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Country(int countryId) {
		super();
		this.countryId = countryId;
	}

	


	@Id
	@GeneratedValue
	@Column(name="country_id")
	private int countryId;	
	public int getCountryId() {
		return countryId;
	}
	
	@Column(name="country_name")
	private String countryName;	
	public String getCountryName() {
		return countryName;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "map_country_city", joinColumns = { 
			@JoinColumn(name = "country_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "city_id", 
					nullable = false, updatable = false) })
	private Set<City> city = new HashSet<City>(0);	
	public Set<City> getCity() {
		return city;
	}	
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
