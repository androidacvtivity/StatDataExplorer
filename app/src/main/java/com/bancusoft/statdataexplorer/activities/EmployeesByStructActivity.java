package com.bancusoft.statdataexplorer.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.models.EmployeeModel;
import com.bancusoft.statdataexplorer.network.ApiUtils;
import com.bancusoft.statdataexplorer.network.RestApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.google.gson.JsonSyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesByStructActivity extends BaseEmployeesActivity {

    private static final String TAG = EmployeesByStructActivity.class.getSimpleName();

    private RestApi api;
    private String type, name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_by_struct);

        type = getIntent().getStringExtra("type");
        name = getIntent().getStringExtra("name");

        // initialize recycler view and search functionality
        setupEmployeeList(new ArrayList<>());

//         // Click pe angajat
//        adapter.setOnItemClickListener(employee -> {
//            Intent intent = new Intent(this, EmployeeDetailActivity.class);
//            intent.putExtra("employee_id", employee.getId());
//            startActivity(intent);
//        });

        api = ApiUtils.getApiService();

        // Load employees for the selected structure
        loadEmployees();
    }

    private void loadEmployees() {
        api.getEmployeesByStruct(type, name).enqueue(new Callback<List<EmployeeModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                if (response.isSuccessful()) {
                    List<EmployeeModel> result = response.body();
                    if (result != null) {
                        if (!result.isEmpty()) {
                            Collections.sort(result, Comparator.comparing(EmployeeModel::getName, String.CASE_INSENSITIVE_ORDER));
                            updateEmployeeData(result);
                        } else {
                            Toast.makeText(EmployeesByStructActivity.this, "Nu s-au găsit angajați!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e(TAG, "Răspunsul de la server este null" );
                        Toast.makeText(EmployeesByStructActivity.this, "Răspuns invalid de la server", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EmployeesByStructActivity.this, "Nu s-au găsit angajați!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    Log.e(TAG, "Unexpected non-array response", t);
                    Toast.makeText(EmployeesByStructActivity.this, "Răspuns invalid de la server", Toast.LENGTH_SHORT).show();
                } else {
                    t.printStackTrace();
                    Toast.makeText(EmployeesByStructActivity.this, "Eroare conectare server: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
