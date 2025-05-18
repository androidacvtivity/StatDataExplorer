package com.bancusoft.statdataexplorer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bancusoft.statdataexplorer.R;

public class EmployeeMenuActivity extends AppCompatActivity {

    Button btnAlpha, btnStar, btnDepart, btnGalaxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_menu);

        btnAlpha = findViewById(R.id.btnAlpha);
        btnStar = findViewById(R.id.btnStar);
        btnDepart = findViewById(R.id.btnDepart);
        btnGalaxy = findViewById(R.id.btnGalaxy);

        btnAlpha.setOnClickListener(v ->
                startActivity(new Intent(this, EmployeesListActivity.class)));

//        btnStar.setOnClickListener(v ->
//                startActivity(new Intent(this, EmployeesByStarActivity.class)));
//
//        btnDepart.setOnClickListener(v ->
//                startActivity(new Intent(this, EmployeesByDepartActivity.class)));
//
//        btnGalaxy.setOnClickListener(v ->
//                startActivity(new Intent(this, EmployeesByGalaxyActivity.class)));
    }
}
