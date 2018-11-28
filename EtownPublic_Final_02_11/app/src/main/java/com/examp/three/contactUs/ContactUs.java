package com.examp.three.contactUs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.examp.three.R;
import com.examp.three.contactUs.Api.RetrofitApi;
import com.examp.three.contactUs.Api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactUs extends AppCompatActivity {
    Toolbar toolbar;
    TextView contact_tv, email_tv;
    RetrofitApi retrofitApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact Us");


        contact_tv = (TextView) findViewById(R.id.contact_tv);
        email_tv = (TextView) findViewById(R.id.email_tv);


        retrofitApi = RetrofitClient.getClient().create(RetrofitApi.class);
        getContactDetails();
    }

    public void getContactDetails() {
        Call<List<ContactUs_Model>> call = retrofitApi.getContactDetails();
        call.enqueue(new Callback<List<ContactUs_Model>>() {
            @Override
            public void onResponse(Call<List<ContactUs_Model>> call, Response<List<ContactUs_Model>> response) {
                List<ContactUs_Model> itemsList = response.body();
                setContactDetails(itemsList);
            }

            @Override
            public void onFailure(Call<List<ContactUs_Model>> call, Throwable t) {

            }
        });

    }

    public void setContactDetails(List<ContactUs_Model> itemsList) {

        for (int i = 0; i < itemsList.size(); i++) {
            ContactUs_Model item = itemsList.get(i);
            contact_tv.setText(contact_tv.getText() + item.getContactNo() + "\n");
            email_tv.setText(email_tv.getText() + item.getEmailId() + "\n");
        }
    }
}
