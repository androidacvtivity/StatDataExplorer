package com.bancusoft.statdataexplorer.activities;

import com.bancusoft.statdataexplorer.models.SimpleValueModel;

import java.util.List;

public class SimpleListResponse {
    private int code;
    private String message;
    private List<SimpleValueModel> result;

    public List<SimpleValueModel> getResult() {
        return result;
    }

    public void setResult(List<SimpleValueModel> result) {
        this.result = result;
    }
}
