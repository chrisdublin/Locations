package org.unical.locations.model;
// Generated 27-Jan-2019 23:44:14 by Hibernate Tools 5.3.6.Final

import java.util.HashSet;
import java.util.Set;

public class City implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int cityid;
	private String cityname;
	private String descrition;
	private Set<Address> addresses = new HashSet<Address>(0);

	public City() {
	}

	public City(int cityid, String cityname, String descrition) {
		this.cityid = cityid;
		this.cityname = cityname;
		this.descrition = descrition;
	}

	public City(int cityid, String cityname, String descrition, Set<Address> addresses) {
		this.cityid = cityid;
		this.cityname = cityname;
		this.descrition = descrition;
		this.addresses = addresses;
	}

	public int getCityid() {
		return this.cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDescrition() {
		return this.descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
