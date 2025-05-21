package com.bancusoft.statdataexplorer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bancusoft.statdataexplorer.R;

public class EmployeeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_menu);



        findViewById(R.id.cardAlpha).setOnClickListener(v ->
                startActivity(new Intent(this, EmployeesListActivity.class))
        );

        findViewById(R.id.cardStar).setOnClickListener(v ->
                startActivity(new Intent(this, StarListActivity.class))
        );
//
//        findViewById(R.id.cardDepart).setOnClickListener(v ->
//                startActivity(new Intent(this, EmployeesByDepartActivity.class))
//        );
//
//        findViewById(R.id.cardGalaxy).setOnClickListener(v ->
//                startActivity(new Intent(this, EmployeesByGalaxyActivity.class))
//        );
    }
}
