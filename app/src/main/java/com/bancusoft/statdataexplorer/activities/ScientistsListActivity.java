// 1. ScientistsListActivity.java
package com.bancusoft.statdataexplorer.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.ScientistAdapter;
import com.bancusoft.statdataexplorer.models.ResponseModelvw;
import com.bancusoft.statdataexplorer.models.Scientistsvw;
import com.bancusoft.statdataexplorer.network.RestApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScientistsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScientistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientists_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadScientists();
    }

    private void loadScientists() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://bancusoft.com/PHP/production/") // schimba cu adresa ta corecta
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi api = retrofit.create(RestApi.class);
        Call<ResponseModelvw> call = api.retrievevw();

        call.enqueue(new Callback<ResponseModelvw>() {
            @Override
            public void onResponse(Call<ResponseModelvw> call, Response<ResponseModelvw> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Scientistsvw> list = response.body().getResultvw();
                    adapter = new ScientistAdapter(ScientistsListActivity.this, list);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ScientistsListActivity.this, "Datele nu au putut fi încărcate.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelvw> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage());
                Toast.makeText(ScientistsListActivity.this, "Eroare: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
