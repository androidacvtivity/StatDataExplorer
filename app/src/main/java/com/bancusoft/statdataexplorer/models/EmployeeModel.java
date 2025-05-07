package com.bancusoft.statdataexplorer.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class EmployeeModel implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("galaxy")
    private String galaxy;

    @SerializedName("star")
    private String star;

    @SerializedName("serviciu")
    private String serviciu;

    @SerializedName("sectia")
    private String sectia;

    @SerializedName("depart")
    private String depart;

    @SerializedName("phone")
    private String phone;

    @SerializedName("phoneinternal")
    private String phoneinternal;

    @SerializedName("email")
    private String email;

    @SerializedName("personalinfo")
    private String personalinfo;

    @SerializedName("formname")
    private String formname;

    @SerializedName("phonemobil")
    private String phonemobil;

    @SerializedName("floor")
    private String floor;

    @SerializedName("office")
    private String office;

    @SerializedName("notice")
    private String notice;

    // Getteri
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getGalaxy() { return galaxy; }
    public String getStar() { return star; }
    public String getServiciu() { return serviciu; }
    public String getSectia() { return sectia; }
    public String getDepart() { return depart; }
    public String getPhone() { return phone; }
    public String getPhoneinternal() { return phoneinternal; }
    public String getEmail() { return email; }
    public String getPersonalinfo() { return personalinfo; }
    public String getFormname() { return formname; }
    public String getPhonemobil() { return phonemobil; }
    public String getFloor() { return floor; }
    public String getOffice() { return office; }
    public String getNotice() { return notice; }

    @Override
    public String toString() {
        return name + " - " + serviciu;
    }
}
