package com.bancusoft.statdataexplorer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        setSupportActionBar(findViewById(R.id.toolbarEmployeesByStruct));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_employees_by_struct, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share_employees) {
            shareEmployees();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareEmployees() {
        if (adapter == null) return;
        List<EmployeeModel> employees = adapter.getAllEmployees();
        if (employees.isEmpty()) {
            Toast.makeText(this, "No employees to share", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(name).append("\n\n");
        }
        for (EmployeeModel e : employees) {
            sb.append(e.getName());

            if (e.getStar() != null && !e.getStar().isEmpty()) {
                sb.append(" - ").append(e.getStar());
            }

            if (e.getDepart() != null && !e.getDepart().isEmpty()) {
                sb.append(" - ").append(e.getDepart());
            }

            if (e.getSectia() != null && !e.getSectia().isEmpty()) {
                sb.append(" - ").append(e.getSectia());
            }

            if (e.getServiciu() != null && !e.getServiciu().isEmpty()) {
                sb.append(" - ").append(e.getServiciu());
            }

            if (e.getGalaxy() != null && !e.getGalaxy().isEmpty()) {
                sb.append(" - ").append(e.getGalaxy());
            }
            if (e.getPhonemobil() != null && !e.getPhonemobil().isEmpty()) {
                sb.append(" - ").append(e.getPhonemobil());
            }

            if (e.getEmail() != null && !e.getEmail().isEmpty()) {
                sb.append(" - ").append(e.getEmail());
            }


            sb.append("\n");
            sb.append("\n");
            sb.append("\n");
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_employees)));
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
