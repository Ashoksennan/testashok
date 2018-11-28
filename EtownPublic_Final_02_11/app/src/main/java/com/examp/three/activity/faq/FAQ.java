package com.examp.three.activity.faq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.examp.three.R;
import com.examp.three.contactUs.Api.RetrofitApi;
import com.examp.three.contactUs.Api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQ extends AppCompatActivity {
    RecyclerView recyclerView;
    FAQ_Adapter adapter;
    RetrofitApi retrofitApi;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Any Questions ?");
        retrofitApi = RetrofitClient.getClient().create(RetrofitApi.class);
        recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getFAQList();
    }

    public void getFAQList(){
        Call<List<Faq_Model>> call = retrofitApi.getFAQDetails();
        call.enqueue(new Callback<List<Faq_Model>>() {
            @Override
            public void onResponse(Call<List<Faq_Model>> call, Response<List<Faq_Model>> response) {
                List<Faq_Model> itemsList = response.body();
                if(itemsList.size()>0) {
                    adapter = new FAQ_Adapter(FAQ.this, itemsList);
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(FAQ.this, "No items in the list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Faq_Model>> call, Throwable t) {

            }
        });
    }
}
