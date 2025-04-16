// ScientistsListActivity.java
package com.bancusoft.statdataexplorer.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.CompanyAdapter;
import com.bancusoft.statdataexplorer.models.CompanyModel;
import com.bancusoft.statdataexplorer.models.ResponseModel;
import com.bancusoft.statdataexplorer.network.RestApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScientistsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static final String BASE_URL = "http://bancusoft.com/PHP/production/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientists_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi api = retrofit.create(RestApi.class);

        api.getViewData().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CompanyModel> list = response.body().getResult();
                    if (list != null && !list.isEmpty()) {
                        CompanyAdapter adapter = new CompanyAdapter(ScientistsListActivity.this, list);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(ScientistsListActivity.this, "Lista este goală sau nulă", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ScientistsListActivity.this, "Eroare la răspuns.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ScientistsListActivity.this, "Eșec rețea: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
