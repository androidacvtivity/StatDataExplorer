package com.bancusoft.statdataexplorer.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.models.EmployeeModel;
import com.bancusoft.statdataexplorer.models.ResponseModelEmployee;

import com.bancusoft.statdataexplorer.network.ApiUtils;
import com.bancusoft.statdataexplorer.network.RestApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesListActivity extends BaseEmployeesActivity {

    private List<EmployeeModel> allEmployees;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);

        // initialize recycler view and search functionality
        setupEmployeeList(new ArrayList<>());

        String star = getIntent().getStringExtra("star");
        if (star != null && !star.isEmpty()) {
            loadEmployeesByStar(star);
        } else {
            loadEmployeeData();
        }
    }

    private void loadEmployeeData() {



        RestApi api = ApiUtils.getApiService(); // elegant
        api.retrieveEmployees().enqueue(new Callback<ResponseModelEmployee>() {
            @Override
            public void onResponse(Call<ResponseModelEmployee> call, Response<ResponseModelEmployee> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allEmployees = response.body().getResult();
                    if (allEmployees == null) allEmployees = new ArrayList<>();
                    Collections.sort(allEmployees, Comparator.comparing(EmployeeModel::getName, String.CASE_INSENSITIVE_ORDER));
                    updateEmployeeData(allEmployees);
                } else {
                    Toast.makeText(EmployeesListActivity.this, "Eroare la răspunsul serverului", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelEmployee> call, Throwable t) {
                Toast.makeText(EmployeesListActivity.this, "Eșec la conectare: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void loadEmployeesByStar(String star) {

        RestApi api = ApiUtils.getApiService(); // elegant
        api.getEmployeesByStar("GET_EMPLOYEES_BY_STAR", star)
                .enqueue(new Callback<ResponseModelEmployee>() {
                    @Override
                    public void onResponse(Call<ResponseModelEmployee> call, Response<ResponseModelEmployee> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<EmployeeModel> list = response.body().getResult();
                            if (list == null) list = new ArrayList<>();
                            Collections.sort(list, Comparator.comparing(EmployeeModel::getName, String.CASE_INSENSITIVE_ORDER));
                            updateEmployeeData(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModelEmployee> call, Throwable t) {
                        Toast.makeText(EmployeesListActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

}
