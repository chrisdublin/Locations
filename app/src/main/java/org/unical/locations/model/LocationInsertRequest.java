package org.unical.locations.model;

import java.io.Serializable;

public class LocationInsertRequest implements Serializable{

	private String countryname;
	private String countryDescrition = " ";
	private String cityname;
	private String cityDescrition = " ";
	private String buildingname = " ";
	private String streetname;
	private String review;
	private int zipcode;
	private boolean savearea;
	private String addressDescrition = " ";
	private String reveiwTitle;
	private int userId;

	public LocationInsertRequest(String countryname, String cityname, String streetname, String review, int zipcode,
								 boolean savearea, String reveiwTitle, int userId) {
		super();
		this.countryname = countryname;
		this.cityname = cityname;
		this.streetname = streetname;
		this.review = review;
		this.zipcode = zipcode;
		this.savearea = savearea;
		this.reveiwTitle = reveiwTitle;
		this.userId = userId;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountryDescrition() {
		return countryDescrition;
	}

	public void setCountryDescrition(String countryDescrition) {
		this.countryDescrition = countryDescrition;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCityDescrition() {
		return cityDescrition;
	}

	public void setCityDescrition(String cityDescrition) {
		this.cityDescrition = cityDescrition;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public boolean isSavearea() {
		return savearea;
	}

	public void setSavearea(boolean savearea) {
		this.savearea = savearea;
	}

	public String getAddressDescrition() {
		return addressDescrition;
	}

	public void setAddressDescrition(String addressDescrition) {
		this.addressDescrition = addressDescrition;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getReveiwTitle() {
		return reveiwTitle;
	}

	public void setReveiwTitle(String reveiwTitle) {
		this.reveiwTitle = reveiwTitle;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
