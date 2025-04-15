package com.bancusoft.statdataexplorer.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bancusoft.statdataexplorer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardViewScientists.setOnClickListener(v -> {
            Toast.makeText(this, "Se încarcă lista...", Toast.LENGTH_SHORT).show();
            // În viitor: startActivity(new Intent(this, ScientistsListActivity.class));
        });
    }
}
