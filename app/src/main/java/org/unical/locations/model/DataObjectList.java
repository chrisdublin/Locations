package org.unical.locations.model;

import java.io.Serializable;
import java.util.List;

public class DataObjectList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<DataObject> elements;
	
	public DataObjectList() {
	}
	
	public DataObjectList(List<DataObject> elements) {
		this.elements = elements;
	}

	public List<DataObject> getElements() {
		return elements;
	}

	public void setElements(List<DataObject> countries) {
		this.elements = countries;
	}
	
	

}
