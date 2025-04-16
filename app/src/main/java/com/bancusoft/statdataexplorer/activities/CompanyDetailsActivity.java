package com.bancusoft.statdataexplorer.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.models.CompanyModel;

public class CompanyDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        CompanyModel company = (CompanyModel) getIntent().getSerializableExtra("company");

        TextView tvDetails = findViewById(R.id.tvDetails);

        if (company != null) {
            String details = "Denumire: " + company.getDenumire() + "\n" +
                    "IDNO: " + company.getIdno() + "\n" +
                    "Adresa: " + company.getAdresa() + "\n" +
                    "Forma organizare: " + company.getFormaOrganizare() + "\n" +
                    "Condiții: " + company.getConditii() + "\n" +
                    "Fondatori: " + company.getFondatori() + "\n" +
                    "Activități fără licență: " + company.getActivitatiFaraLicenta() + "\n" +
                    "Activități cu licență: " + company.getActivitatiCuLicenta() + "\n" +
                    "Statut: " + company.getStatutul() + "\n" +
                    "Data înregistrării: " + company.getDataInregistrarii() + "\n" +
                    "Act: " + company.getAct();

            tvDetails.setText(details);
        } else {
            tvDetails.setText("Nu s-au găsit date pentru această companie.");
        }
    }
}
