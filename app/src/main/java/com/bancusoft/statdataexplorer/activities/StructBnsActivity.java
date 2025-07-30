package com.bancusoft.statdataexplorer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.StructBnsAdapter;
import com.bancusoft.statdataexplorer.models.StructBns;
import com.bancusoft.statdataexplorer.network.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StructBnsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StructBnsAdapter adapter;
    private List<StructBns> structList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struct_bns);
        Toast.makeText(this, "StructBnsActivity a pornit!", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recyclerViewStructBns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StructBnsAdapter(structList, struct -> {
            Intent intent = new Intent(this, EmployeesByStructActivity.class);
            intent.putExtra("type", struct.getType());
            intent.putExtra("name", struct.getName());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        ApiUtils.getApiService().getStructBns().enqueue(new Callback<List<StructBns>>() {
            @Override
            public void onResponse(Call<List<StructBns>> call, Response<List<StructBns>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    structList.clear();
                    structList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<StructBns>> call, Throwable t) {
                Toast.makeText(StructBnsActivity.this, "Eroare la încărcare!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

