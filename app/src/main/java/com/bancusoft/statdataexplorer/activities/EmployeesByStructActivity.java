package com.bancusoft.statdataexplorer.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.EmployeeAdapter;
import com.bancusoft.statdataexplorer.models.EmployeeModel;
import com.bancusoft.statdataexplorer.models.EmployeesApiResponse;
import com.bancusoft.statdataexplorer.network.ApiUtils;
import com.bancusoft.statdataexplorer.network.RestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class EmployeesByStructActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private List<EmployeeModel> employeeList = new ArrayList<>();
    private RestApi api;
    private String type, name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_by_struct);

        type = getIntent().getStringExtra("type");
        name = getIntent().getStringExtra("name");

        recyclerView = findViewById(R.id.recyclerViewEmployees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmployeeAdapter(this, employeeList);
        recyclerView.setAdapter(adapter);

//         // Click pe angajat
//        adapter.setOnItemClickListener(employee -> {
//            Intent intent = new Intent(this, EmployeeDetailActivity.class);
//            intent.putExtra("employee_id", employee.getId());
//            startActivity(intent);
//        });

        api = ApiUtils.getApiService();
//
//        loadEmployees();
    }

//    private void loadEmployees() {
//        api.getEmployeesByStruct(type, name).enqueue(new Callback<EmployeesApiResponse>() {
//            @Override
//            public void onResponse(Call<EmployeesApiResponse> call, Response<EmployeesApiResponse> response) {
//                if (response.isSuccessful() && response.body() != null && response.body().getResult() != null) {
//                    employeeList.clear();
//                    employeeList.addAll(response.body().getResult());
//                    adapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(EmployeesByStructActivity.this, "Nu s-au găsit angajați!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<EmployeesApiResponse> call, Throwable t) {
//                t.printStackTrace();
//                Toast.makeText(EmployeesByStructActivity.this, "Eroare conectare server: " + t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}
