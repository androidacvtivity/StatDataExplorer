package com.bancusoft.statdataexplorer.network;

import com.bancusoft.statdataexplorer.models.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @GET("index_view.php")
    Call<ResponseModel> getViewData();

    @FormUrlEncoded
    @POST("index_view.php")
    Call<ResponseModel> searchCompanies(
            @Field("actionvw") String action,
            @Field("queryvw") String query,
            @Field("limitvw") int limit,
            @Field("startvw") int start
    );
}
