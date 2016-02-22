/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestApiSrv
 * 2. 패키지명		: org.jij.WebPortpolio.model.main
 * 3. 파일명		: City.java
 * 4. 작성일		: 2015. 12. 22.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name="City")
public class City implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 2384215445229668340L;
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public City(int cityId) {
		super();
		this.cityId = cityId;
	}



	@Id
	@GeneratedValue
	@Column(name="city_id")
	private int cityId;	
	public int getCityId() {
		return cityId;
	}
	
	@Column(name="city_name")
	private String cityName;	
	public String getCityName() {
		return cityName;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "city")
	private Set<Country> country = new HashSet<Country>(0);	;	
	public Set<Country> getCountry() {
		return country;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
