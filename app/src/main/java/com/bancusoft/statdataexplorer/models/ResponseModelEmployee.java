package com.bancusoft.statdataexplorer.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseModelEmployee {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private List<EmployeeModel> result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<EmployeeModel> getResult() {
        return result;
    }
}
