package com.bancusoft.statdataexplorer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bancusoft.statdataexplorer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.bancusoft.statdataexplorer.databinding.ActivityMainBinding binding =
                com.bancusoft.statdataexplorer.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardViewScientists.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EmployeeMenuActivity.class); // sau cum se numește noua activitate
            startActivity(intent);
        });

        // Card: Lista companiilor
        binding.cardViewCompanies.setOnClickListener(v -> {
            Toast.makeText(this, "Se încarcă companiile...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ScientistsListActivity.class));
        });


    }

}
