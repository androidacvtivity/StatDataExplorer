package com.bancusoft.statdataexplorer.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StarModel implements Serializable {
    @SerializedName("star")
    private String star;

    public String getStar() { return star; }

    public void setStar(String star) { this.star = star; }
}
