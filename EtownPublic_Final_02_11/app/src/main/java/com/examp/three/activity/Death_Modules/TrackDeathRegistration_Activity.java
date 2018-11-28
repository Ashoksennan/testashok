package com.examp.three.activity.Death_Modules;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.examp.three.R;
import com.examp.three.adapter.Death.TrackDeathRegistrationAdapter;
import com.examp.three.common.Common;
import com.examp.three.common.RetrofitInstance;
import com.examp.three.common.RetrofitInterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by priyadharshini on 31/07/2018.
 */

public class TrackDeathRegistration_Activity extends AppCompatActivity implements TrackDeathRegistrationAdapter.DeathSpecificDetails {

    TrackDeathRegistrationAdapter adapter;
    RecyclerView tbr_rv;
    AlertDialog dialogBirthDetails;

    final static String TAG = TrackDeathRegistration_Activity.class.getSimpleName();

    ArrayList<com.examp.three.model.Birth_Death.TrackDeathRegistration_Pojo> beanlist =new ArrayList<>();
    @Nullable
    @BindView(R.id.et_req_no) EditText et_req_no;
    @Nullable
    @BindView(R.id.et_mob_no) EditText et_mob_no;
    @Nullable
    @BindView(R.id.btn_track) Button btn_track;
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.tv_track_title) TextView tv_track_title;
    @Nullable
    @BindView(R.id.li_parent_lay)
    LinearLayout li_parent_lay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_death_registration);
        ButterKnife.bind(this);
        beanlist = new ArrayList<>();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.Death_track_death_registration);

        tbr_rv = (RecyclerView)findViewById(R.id.tbr_rv);
        tv_track_title.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);

        tbr_rv.setLayoutManager(linearLayoutManager);

        et_req_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                et_mob_no.getText().clear();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_mob_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                et_req_no.getText().clear();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(tbr_rv != null)
        {
            tbr_rv.setHasFixedSize(true);
        }

        btn_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et_req_no.getText().toString().isEmpty() || !et_mob_no.getText().toString().isEmpty())
                    if(Common.isNetworkAvailable(TrackDeathRegistration_Activity.this))
                    getTrackDetails();
                    else
                    Snackbar.make(li_parent_lay,"No Internet Connection!",Snackbar.LENGTH_SHORT).show();

                else
                    Snackbar.make(li_parent_lay,"Please Enter Above Request no or mobile no",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    //This method is for retrieving the track details
    public void getTrackDetails(){
        beanlist.clear();
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofit().create(RetrofitInterface.class);
        final ProgressDialog pd = new ProgressDialog(TrackDeathRegistration_Activity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Call districtresult = retrofitInterface.getTrackingDetails(Common.ACCESS_TOKEN,"Death",et_mob_no.getText().toString(),et_req_no.getText().toString(),"","","");
        districtresult.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.e("fgd",response.toString());
                String response1 = new Gson().toJson(response.body());
                Log.e("result",response1);
                try {
                    JSONObject records = new JSONObject(response1);
                    JSONArray jsonArray = records.getJSONArray("recordsets");
                    if(jsonArray.length()>0){
                        JSONArray trackArray = jsonArray.getJSONArray(0);
                        if(trackArray.length()>0){
                            for(int i=0;i<trackArray.length();i++){
                                JSONObject trackObjects =trackArray.getJSONObject(i);

                                beanlist.add(new com.examp.three.model.Birth_Death.TrackDeathRegistration_Pojo(trackObjects.optString("RequestNo"),trackObjects.optString("RequestDate"),
                                        trackObjects.optString("District"),trackObjects.optString("Panchayat"),trackObjects.optString("DeceasedName"),
                                        trackObjects.optString("DoorNo"),trackObjects.optString("WardNo"),trackObjects.optString("Status"),
                                        trackObjects.optString("StreetName"),trackObjects.optString("MobileNo"),trackObjects.optString("EmailId"),trackObjects.optString("FatherName"),trackObjects.optString("Gender"),
                                        trackObjects.optString("DeathPlace"),trackObjects.optString("MotherName"),trackObjects.optString("DOD"),trackObjects.optString("HusbandWifeName"),trackObjects.optString("Age"),
                                        trackObjects.optString("AgeType")));
                            }
                            pd.dismiss();

                            Log.e(TAG,"list====>"+beanlist.size());
                          addAdapter();

                        }else{
                            Snackbar.make(li_parent_lay,"No Data Found!",Snackbar.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }else{
                        Snackbar.make(li_parent_lay,"No Data Found!",Snackbar.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                pd.dismiss();
                Log.e(TAG,t.toString());
            }
        });
    }
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    //This method is for setting adapter for retrieved track details
    public void addAdapter(){
        hideSoftKeyboard();
        if(beanlist.size()>0){
            tv_track_title.setVisibility(View.VISIBLE);
        }else{
            tv_track_title.setVisibility(View.GONE);
        }
        adapter = new TrackDeathRegistrationAdapter(beanlist,getApplicationContext(),TrackDeathRegistration_Activity.this);
        tbr_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getposition(int pos) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TrackDeathRegistration_Activity.this);
        View v2 = getLayoutInflater().inflate(R.layout.item_track_death, null);
        ImageView img_cancel =  (ImageView)v2.findViewById(R.id.img_cancel);
        TextView tv_age =  (TextView)v2.findViewById(R.id.tv_age);
        TextView tv_req_no =  (TextView)v2.findViewById(R.id.tv_req_no);
        TextView tv_gender =  (TextView)v2.findViewById(R.id.tv_gender);
        TextView tv_husband_name =  (TextView)v2.findViewById(R.id.tv_husband_name);
        TextView tv_diseased_name =  (TextView)v2.findViewById(R.id.tv_diseased_name);
        TextView tv_father_mob_no =  (TextView)v2.findViewById(R.id.tv_father_mob_no);
        TextView tv_email =  (TextView)v2.findViewById(R.id.tv_email);
        TextView tv_birthplace =  (TextView)v2.findViewById(R.id.tv_birthplace);
        TextView tv_dob =  (TextView)v2.findViewById(R.id.tv_dob);
        TextView tv_district =  (TextView)v2.findViewById(R.id.tv_district);
        TextView tv_panchayat =  (TextView)v2.findViewById(R.id.tv_panchayat);
        TextView tv_status =  (TextView)v2.findViewById(R.id.tv_status);
        if(!beanlist.get(pos).getmRequestNo().isEmpty()){
            tv_req_no.setText(beanlist.get(pos).getmRequestNo());
        }else{
            tv_req_no.setVisibility(View.GONE);
        }
        if(!beanlist.get(pos).getmAge().isEmpty()){
            tv_age.setText(beanlist.get(pos).getmAge());
        }else{
            tv_age.setVisibility(View.GONE);
        }
//        tv_gender.setText(beanlist.get(pos).getGender());
        if(!beanlist.get(pos).getmGender().isEmpty()){
            tv_gender.setText(beanlist.get(pos).getmGender());
        }else{
            tv_gender.setVisibility(View.GONE);
        }
//        tv_father_name.setText(beanlist.get(pos).getFatherName());
        if(!beanlist.get(pos).getmHusbandWifeName().isEmpty()){
            tv_husband_name.setText(beanlist.get(pos).getmHusbandWifeName());
        }else{
            tv_husband_name.setVisibility(View.GONE);
        }
        if(!beanlist.get(pos).getmDeceasedName().isEmpty()){
            tv_diseased_name.setText(beanlist.get(pos).getmDeceasedName());
        }else{
            tv_diseased_name.setVisibility(View.GONE);
        }
//        tv_father_mob_no.setText(beanlist.get(pos).getMobileNo());
        if(!beanlist.get(pos).getmMobileNo().isEmpty()){
            tv_father_mob_no.setText(beanlist.get(pos).getmMobileNo());
        }else{
            tv_father_mob_no.setVisibility(View.GONE);
        }
        if(!beanlist.get(pos).getmEmailId().isEmpty()){
            tv_email.setText(beanlist.get(pos).getmEmailId());
        }else{
            tv_email.setVisibility(View.GONE);
        }
        if(!beanlist.get(pos).getmBirthPlace().isEmpty()){
            tv_birthplace.setText(beanlist.get(pos).getmBirthPlace());
        }else{
            tv_birthplace.setVisibility(View.GONE);
        }
        if(!beanlist.get(pos).getmDOB().isEmpty()){
            tv_dob.setText(beanlist.get(pos).getmDOB());
        }else{
            tv_dob.setVisibility(View.GONE);
        }
        if(!beanlist.get(pos).getmDistrict().isEmpty()){
            tv_district.setText(beanlist.get(pos).getmDistrict());
        }else{
            tv_district.setVisibility(View.GONE);
        }
        if(!beanlist.get(pos).getmPanchayat().isEmpty()){
            tv_panchayat.setText(beanlist.get(pos).getmPanchayat());
        }else{
            tv_panchayat.setVisibility(View.GONE);
        }
        if(!beanlist.get(pos).getmStatus().isEmpty()){
            tv_status.setText(beanlist.get(pos).getmStatus());
        }else{
            tv_status.setVisibility(View.GONE);
        }
        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBirthDetails.dismiss();
            }
        });
        mBuilder.setView(v2);
        dialogBirthDetails = mBuilder.create();
        dialogBirthDetails.show();
    }
}
