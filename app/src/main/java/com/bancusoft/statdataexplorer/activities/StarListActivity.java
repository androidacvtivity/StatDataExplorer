package com.bancusoft.statdataexplorer.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.SimpleValueAdapter;
import com.bancusoft.statdataexplorer.network.RestApi;
import com.bancusoft.statdataexplorer.network.ApiUtils;
import com.bancusoft.statdataexplorer.models.SimpleValueModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarListActivity extends AppCompatActivity {

    RecyclerView recyclerStar, recyclerDepart, recyclerSectia, recyclerServiciu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_list);

        recyclerStar = findViewById(R.id.recyclerStar);
        recyclerDepart = findViewById(R.id.recyclerDepart);
        recyclerSectia = findViewById(R.id.recyclerSectia);
        recyclerServiciu = findViewById(R.id.recyclerServiciu);

        recyclerStar.setLayoutManager(new LinearLayoutManager(this));
        recyclerDepart.setLayoutManager(new LinearLayoutManager(this));
        recyclerSectia.setLayoutManager(new LinearLayoutManager(this));
        recyclerServiciu.setLayoutManager(new LinearLayoutManager(this));

        // Aici setăm diferit "tipul" pentru fiecare listă (0=star, 1=depart, 2=sectie, 3=serviciu)
        loadList("GET_STARS", recyclerStar, 0);
        loadList("GET_DEPARTS", recyclerDepart, 1);
        loadList("GET_SECTII", recyclerSectia, 2);
        loadList("GET_SERVICII", recyclerServiciu, 3);
    }

    private void loadList(String action, RecyclerView recyclerView, int tip) {
        RestApi api = ApiUtils.getApiService();
        Call<SimpleListResponse> call = api.getSimpleList(action);

        call.enqueue(new Callback<SimpleListResponse>() {
            @Override
            public void onResponse(Call<SimpleListResponse> call, Response<SimpleListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SimpleValueModel> list = response.body().getResult();
                    SimpleValueAdapter adapter = new SimpleValueAdapter(StarListActivity.this, list, tip);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<SimpleListResponse> call, Throwable t) {
                Toast.makeText(StarListActivity.this, "Eroare: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
