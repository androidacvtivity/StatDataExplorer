package com.bancusoft.statdataexplorer.network;

import com.bancusoft.statdataexplorer.models.ResponseModelvw;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("index_view.php")
    Call<ResponseModelvw> retrievevw();
}
