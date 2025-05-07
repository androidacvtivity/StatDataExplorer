package com.bancusoft.statdataexplorer.activities;

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

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        TextView tvGalaxy = findViewById(R.id.tvGalaxy);
        TextView tvStar = findViewById(R.id.tvStar);
        TextView tvServiciu = findViewById(R.id.tvServiciu);
        TextView tvSectia = findViewById(R.id.tvSectia);
        TextView tvDepart = findViewById(R.id.tvDepart);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvPhoneInternal = findViewById(R.id.tvPhoneInternal);
        TextView tvPhonemobil = findViewById(R.id.tvPhonemobil);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvPersonalinfo = findViewById(R.id.tvPersonalinfo);
        TextView tvFormname = findViewById(R.id.tvFormname);
        TextView tvFloor = findViewById(R.id.tvFloor);
        TextView tvOffice = findViewById(R.id.tvOffice);
        TextView tvNotice = findViewById(R.id.tvNotice);

        EmployeeModel emp = (EmployeeModel) getIntent().getSerializableExtra("employee");

        if (emp != null) {
            tvName.setText(emp.getName());
            tvDescription.setText(emp.getDescription());
            tvGalaxy.setText(emp.getGalaxy());
            tvStar.setText(emp.getStar());
            tvServiciu.setText(emp.getServiciu());
            tvSectia.setText(emp.getSectia());
            tvDepart.setText(emp.getDepart());
            tvPhone.setText(emp.getPhone());
            tvPhoneInternal.setText(emp.getPhoneinternal());
            tvPhonemobil.setText(emp.getPhonemobil());
            tvEmail.setText(emp.getEmail());
            tvPersonalinfo.setText(emp.getPersonalinfo());
            tvFormname.setText(emp.getFormname());
            tvFloor.setText(emp.getFloor());
            tvOffice.setText(emp.getOffice());
            tvNotice.setText(emp.getNotice());
        }
    }
}
