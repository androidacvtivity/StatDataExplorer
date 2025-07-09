package com.bancusoft.statdataexplorer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.StarAdapter;
import com.bancusoft.statdataexplorer.models.StarModel;
import com.bancusoft.statdataexplorer.models.StarResponseModel;
import com.bancusoft.statdataexplorer.network.ApiUtils;
import com.bancusoft.statdataexplorer.network.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestApi api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_list);

        recyclerView = findViewById(R.id.recyclerViewStars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        api = ApiUtils.getApiService();
        loadStars();
    }

    private void loadStars() {
        api.getStars("GET_DISTINCT_STAR").enqueue(new Callback<StarResponseModel>() {
            @Override
            public void onResponse(Call<StarResponseModel> call, Response<StarResponseModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResult() != null) {
                    List<StarModel> stars = response.body().getResult();
                    StarAdapter adapter = new StarAdapter(StarListActivity.this, stars, star -> {
                        Intent intent = new Intent(StarListActivity.this, EmployeesListActivity.class);
                        intent.putExtra("star", star.getStar());
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(StarListActivity.this, "Nu există direcții!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StarResponseModel> call, Throwable t) {
                Toast.makeText(StarListActivity.this, "Eroare rețea: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
