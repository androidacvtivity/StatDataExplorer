package com.bancusoft.statdataexplorer.network;

import com.bancusoft.statdataexplorer.models.EmployeesApiResponse;
import com.bancusoft.statdataexplorer.models.ResponseModel;
import com.bancusoft.statdataexplorer.models.ResponseModelEmployee;
import com.bancusoft.statdataexplorer.models.StarResponseModel;
import com.bancusoft.statdataexplorer.models.StructBns;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {


    @GET("index_view.php")
    Call<ResponseModel> retrievevw();  // folosit doar la prima încărcare

    @GET("index.php?action=GET_STRUCT_BNS")
    Call<List<StructBns>> getStructBns();

    @GET("index.php?action=GET_EMPLOYEES_BY_STRUCT")
    Call<EmployeesApiResponse> getEmployeesByStruct(
            @Query("type") String type,
            @Query("name") String name
    );

    @FormUrlEncoded
    @POST("index_view.php")
    Call<ResponseModel> searchvw(
            @Field("actionvw") String actionvw,
            @Field("queryvw") String queryvw,
            @Field("startvw") String startvw,
            @Field("limitvw") String limitvw
    );


    @GET("index.php")
    Call<ResponseModelEmployee> retrieveEmployees();

    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseModelEmployee> searchEmployees(
            @Field("action") String action,
            @Field("query") String query,
            @Field("start") String start,
            @Field("limit") String limit
    );


    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseModelEmployee> getEmployeesByStar(
            @Field("action") String action,
            @Field("star") String star
    );


    @FormUrlEncoded
    @POST("index.php")
    Call<StarResponseModel> getStars(
            @Field("action") String action
    );

}
