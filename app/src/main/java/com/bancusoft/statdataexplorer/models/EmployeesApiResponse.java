package com.bancusoft.statdataexplorer.models;

import java.util.List;

public class EmployeesApiResponse {
    private int code;
    private String message;
    private List<EmployeeModel> result;

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public List<EmployeeModel> getResult() { return result; }
}
