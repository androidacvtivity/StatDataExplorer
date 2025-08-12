package com.bancusoft.statdataexplorer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.models.EmployeeModel;

public class EmployeeDetailsActivity extends AppCompatActivity {

    private EmployeeModel emp;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);


        emp = (EmployeeModel) getIntent().getSerializableExtra("employee");

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
//        ((TextView) findViewById(R.id.tvNotice)).setText(emp.getNotice());

        Button shareButton = findViewById(R.id.btnShare);
        shareButton.setOnClickListener(v -> {
            StringBuilder details = new StringBuilder();
            details.append("Nume, Prenume: ").append(emp.getName()).append('\n');
            details.append("Adresa: ").append(emp.getDescription()).append('\n');
            details.append("Funcția: ").append(emp.getGalaxy()).append('\n');
            details.append("Direcția Generală: ").append(emp.getStar()).append('\n');
            details.append("Serviciu: ").append(emp.getServiciu()).append('\n');
            details.append("Secția: ").append(emp.getSectia()).append('\n');
            details.append("Direcția: ").append(emp.getDepart()).append('\n');
            details.append("Telefon fix: ").append(emp.getPhone()).append('\n');
            details.append("Telefon intern: ").append(emp.getPhoneinternal()).append('\n');
            details.append("Telefon Mobil: ").append(emp.getPhonemobil()).append('\n');
            details.append("Email: ").append(emp.getEmail()).append('\n');
            details.append("Info: ").append(emp.getPersonalinfo()).append('\n');
            details.append("Denumirea raportului: ").append(emp.getFormname()).append('\n');
            details.append("Etajul: ").append(emp.getFloor()).append('\n');
            details.append("Oficiul: ").append(emp.getOffice());

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, emp.getName());
            shareIntent.putExtra(Intent.EXTRA_TEXT, details.toString());

            startActivity(Intent.createChooser(shareIntent, "Share employee"));
        });
    }
}
