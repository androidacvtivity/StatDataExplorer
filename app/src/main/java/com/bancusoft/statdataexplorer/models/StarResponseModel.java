package com.bancusoft.statdataexplorer.models;

import java.util.List;

public class StarResponseModel {
    private int code;
    private String message;
    private List<StarModel> result;

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public List<StarModel> getResult() { return result; }
}
