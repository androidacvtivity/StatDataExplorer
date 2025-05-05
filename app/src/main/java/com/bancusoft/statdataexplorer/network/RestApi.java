package com.bancusoft.statdataexplorer.network;

import com.bancusoft.statdataexplorer.models.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @GET("index_view.php")
    Call<ResponseModel> retrievevw();  // folosit doar la prima încărcare

    @FormUrlEncoded
    @POST("index_view.php")
    Call<ResponseModel> searchvw(
            @Field("actionvw") String actionvw,
            @Field("queryvw") String queryvw,
            @Field("startvw") String startvw,
            @Field("limitvw") String limitvw
    );
}
