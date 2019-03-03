package org.unical.locations.model;

import com.google.gson.annotations.SerializedName;

public class DataObject {

    @SerializedName("name")
    private String name;
    private int id;

    public DataObject() {
    }

    public DataObject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
