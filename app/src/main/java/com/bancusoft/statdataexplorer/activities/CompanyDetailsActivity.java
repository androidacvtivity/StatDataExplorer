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

        @SuppressWarnings("deprecation")
        CompanyModel company = (CompanyModel) getIntent().getSerializableExtra("company");

        if (company != null) {
            ((TextView) findViewById(R.id.tvDenumire)).setText(getSafe(company.getDenumire()));
            ((TextView) findViewById(R.id.tvIdno)).setText(getSafe(company.getIdno()));
            ((TextView) findViewById(R.id.tvAdresa)).setText(getSafe(company.getAdresa()));
            ((TextView) findViewById(R.id.tvForma)).setText(getSafe(company.getFormaOrganizare()));
            ((TextView) findViewById(R.id.tvConditii)).setText(getSafe(company.getConditii()));
            ((TextView) findViewById(R.id.tvFondatori)).setText(getSafe(company.getFondatori()));
            ((TextView) findViewById(R.id.tvFaraLicenta)).setText(getSafe(company.getActivitatiFaraLicenta()));
            ((TextView) findViewById(R.id.tvCuLicenta)).setText(getSafe(company.getActivitatiCuLicenta()));
            ((TextView) findViewById(R.id.tvStatut)).setText(getSafe(company.getStatutul()));
            ((TextView) findViewById(R.id.tvData)).setText(getSafe(company.getDataInregistrarii()));
            ((TextView) findViewById(R.id.tvAct)).setText(getSafe(company.getAct()));
        } else {
            finish(); // închidem activitatea dacă nu avem date
        }
    }

    private String getSafe(String value) {
        return value != null ? value : "";
    }
}
