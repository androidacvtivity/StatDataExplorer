package com.bancusoft.statdataexplorer.network;

public class ApiUtils {

    public static RestApi getApiService() {
        return RetrofitClient.getClient().create(RestApi.class);
    }
}
