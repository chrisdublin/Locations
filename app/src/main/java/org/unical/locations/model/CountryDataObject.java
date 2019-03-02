package org.unical.locations.model;

import com.google.gson.annotations.SerializedName;

public class CountryDataObject {

    @SerializedName("name")
    private String name;

    public CountryDataObject() {
    }

    public CountryDataObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
