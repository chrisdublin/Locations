package org.unical.locations.model;

import java.io.Serializable;
import java.util.List;

public class Countries implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<CountryDataObject> countries;
	
	public Countries() {
	}
	
	public Countries(List<CountryDataObject> countries) {
		this.countries = countries;
	}

	public List<CountryDataObject> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryDataObject> countries) {
		this.countries = countries;
	}
	
	

}
