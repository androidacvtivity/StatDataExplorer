package com.bancusoft.statdataexplorer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.EmployeeAdapter;
import com.bancusoft.statdataexplorer.models.EmployeeModel;
import com.bancusoft.statdataexplorer.models.ResponseModelEmployee;
import com.bancusoft.statdataexplorer.network.ApiUtils;
import com.bancusoft.statdataexplorer.network.RestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesByGroupActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EmployeeAdapter adapter;
    List<EmployeeModel> employeeList = new ArrayList<>();
    RestApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_by_group);

        recyclerView = findViewById(R.id.recyclerEmployees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        api = ApiUtils.getApiService();

        String tip = getIntent().getStringExtra("tip");
        String valoare = getIntent().getStringExtra("valoare");

        loadEmployeesByGroup(tip, valoare);
    }

    private void loadEmployeesByGroup(String tip, String valoare) {
        api.getEmployeesByGroup("GET_EMPLOYEES_BY_GROUP", tip, valoare)
                .enqueue(new Callback<ResponseModelEmployee>() {
                    @Override
                    public void onResponse(Call<ResponseModelEmployee> call, Response<ResponseModelEmployee> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            employeeList = response.body().getResult();
                            adapter = new EmployeeAdapter(EmployeesByGroupActivity.this, employeeList);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModelEmployee> call, Throwable t) {
                        Toast.makeText(EmployeesByGroupActivity.this, "Eroare: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
