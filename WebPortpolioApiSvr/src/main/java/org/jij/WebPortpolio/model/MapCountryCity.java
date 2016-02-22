/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestApiSrv
 * 2. 패키지명		: org.jij.WebPortpolio.model.main
 * 3. 파일명		: MapCountryCity.java
 * 4. 작성일		: 2015. 12. 22.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.Hibernate;

@Entity
@Table(name="map_country_city")
public class MapCountryCity implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 4006660076320176555L;

	public MapCountryCity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapCountryCity(int mapId) {
		super();
		this.mapId = mapId;
	}
	
	public MapCountryCity(int mapId, int countryId) {
		super();
		this.mapId = mapId;
		this.countryId = countryId;
	}

	@Id
	@GeneratedValue
	@Column(name="map_id")
	private int mapId;
	public int getMapId() {
		return mapId;
	}
	
	@Column(name="city_id")
	private int cityId;
	public int getCityId() {
		return cityId;
	}
	
	@Column(name="country_id")
	private int countryId;
	public int getCountryId() {
		return countryId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id", nullable=false, insertable=false, updatable=false)
	private Country country;	
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="city_id", nullable=false, insertable=false, updatable=false)
	private City city;	
	
	
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
