package com.bancusoft.statdataexplorer.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.models.EmployeeModel;

public class EmployeeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        @SuppressWarnings("deprecation")
        EmployeeModel emp = (EmployeeModel) getIntent().getSerializableExtra("employee");


        if (emp == null) return;

        ((TextView) findViewById(R.id.tvName)).setText(emp.getName());
        ((TextView) findViewById(R.id.tvDescription)).setText(emp.getDescription());
        ((TextView) findViewById(R.id.tvGalaxy)).setText(emp.getGalaxy());
        ((TextView) findViewById(R.id.tvStar)).setText(emp.getStar());
        ((TextView) findViewById(R.id.tvServiciu)).setText(emp.getServiciu());
        ((TextView) findViewById(R.id.tvSectia)).setText(emp.getSectia());
        ((TextView) findViewById(R.id.tvDepart)).setText(emp.getDepart());
        ((TextView) findViewById(R.id.tvPhone)).setText(emp.getPhone());
        ((TextView) findViewById(R.id.tvPhoneInternal)).setText(emp.getPhoneinternal());
        ((TextView) findViewById(R.id.tvEmail)).setText(emp.getEmail());
        ((TextView) findViewById(R.id.tvPersonalinfo)).setText(emp.getPersonalinfo());
        ((TextView) findViewById(R.id.tvFormname)).setText(emp.getFormname());
        ((TextView) findViewById(R.id.tvPhonemobil)).setText(emp.getPhonemobil());
        ((TextView) findViewById(R.id.tvFloor)).setText(emp.getFloor());
        ((TextView) findViewById(R.id.tvOffice)).setText(emp.getOffice());
        ((TextView) findViewById(R.id.tvNotice)).setText(emp.getNotice());
    }
}
