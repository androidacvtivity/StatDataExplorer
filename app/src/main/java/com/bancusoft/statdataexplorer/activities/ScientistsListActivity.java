package com.bancusoft.statdataexplorer.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.adapters.CompanyAdapter;
import com.bancusoft.statdataexplorer.models.CompanyModel;
import com.bancusoft.statdataexplorer.models.ResponseModel;
import com.bancusoft.statdataexplorer.network.ApiClient;
import com.bancusoft.statdataexplorer.network.ApiUtils;
import com.bancusoft.statdataexplorer.network.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScientistsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private CompanyAdapter adapter;
  //  private static final String BASE_URL = "http://bancusoft.com/PHP/production/";

    private RestApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientists_list);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        api = ApiClient.getClient().create(RestApi.class);
        api = ApiUtils.getApiService(); // elegant


      //  RestApi api = ApiClient.getClient().create(RestApi.class);

        // Încărcare inițială fără filtru - cum este realizata la companiii si angajati ?
        searchCompany("");

        // Căutare live
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCompany(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCompany(newText); // Live
                return true;
            }
        });
    }

    private void searchCompany(String query) {
        api.searchvw("GET_PAGINATED_SEARCHVW", query, "0", "200").enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CompanyModel> list = response.body().getResult();
                    if (list != null && !list.isEmpty()) {
                        adapter = new CompanyAdapter(ScientistsListActivity.this, list, query.toLowerCase());
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(ScientistsListActivity.this, "Niciun rezultat găsit.", Toast.LENGTH_SHORT).show();
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
