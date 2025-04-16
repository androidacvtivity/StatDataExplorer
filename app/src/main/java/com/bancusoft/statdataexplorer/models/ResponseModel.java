package com.bancusoft.statdataexplorer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {
    private int code;
    private String message;
    @SerializedName("resultvw")
    private List<CompanyModel> result;

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public List<CompanyModel> getResult() { return result; }
}
