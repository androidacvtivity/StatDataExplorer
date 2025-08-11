package com.bancusoft.statdataexplorer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.EmployeeAdapter;
import com.bancusoft.statdataexplorer.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Base activity providing common RecyclerView and SearchView setup for displaying employees.
 */
public abstract class BaseEmployeesActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected SearchView searchView;
    protected EmployeeAdapter adapter;

    /**
     * Initialize RecyclerView, SearchView and adapter with the provided employee list.
     */
    protected void setupEmployeeList(List<EmployeeModel> employees) {
        recyclerView = findViewById(R.id.recyclerViewEmployees);
        searchView = findViewById(R.id.searchViewEmployees);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (employees == null) {
            employees = new ArrayList<>();
        }
        adapter = new EmployeeAdapter(this, employees);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }

    /**
     * Update adapter data with a new list of employees.
     */
    protected void updateEmployeeData(List<EmployeeModel> employees) {
        if (adapter != null && employees != null) {
            adapter.updateData(employees);
        }
    }
}
