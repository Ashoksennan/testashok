package com.examp.three.activity.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examp.three.R;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.examp.three.common.SharedPreferenceHelper.pref_login_address;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_city;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_contact;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_country;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_email;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_firstname;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_lastname;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_pincode;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_state;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_title;

public class Profile extends AppCompatActivity {

    @Nullable
    @BindView(R.id.profile_toolbar) Toolbar profile_toolbar;
    @NotEmpty(message = "Title field Should not be Empty")
    @Nullable
    @BindView(R.id.et_title) TextView et_title;
    @NotEmpty(message = "First Name Should not be Empty")

    @Nullable
    @BindView(R.id.et_emailid) TextView et_emailid;
    @NotEmpty(message = "Contact No Should not be Empty")
    @Length(min = 10,max = 10,message = "Mobile number should be 10 digit")
    @Nullable
    @BindView(R.id.et_contactno) TextView et_contactno;
    @NotEmpty(message = "Address Should not be Empty")
    @Nullable
    @BindView(R.id.et_address_one) TextView et_address_one;

    @Nullable
    @BindView(R.id.et_cityname) TextView et_cityname;
    @NotEmpty(message = "State Name Should not be Empty")
    @Nullable
    @BindView(R.id.et_statename) TextView et_statename;
    @NotEmpty(message = "Country Should not be Empty")
    @Nullable
    @BindView(R.id.et_country) TextView et_country;
    @NotEmpty(message = "Country Should not be Empty")
    @Length(min = 6,max = 6,message = "Pincode should be Six Digit")
    @Nullable
    @BindView(R.id.et_pincode) TextView et_pincode;
    @Nullable
    @BindView(R.id.li_parent_lay) LinearLayout li_parent_lay;

String mTitle = "Mr.";
String mFirstName = "Vediyappan";
String mLastName="V";
String mEmailId="vedi115@gmail.com";
String mContactNo="9952102045";
String mAddress1="Pallathur Village";
String mAddress2="Uthangarai Tk,";
String mAddress3="Krishnagiri Dt,";
String mCityName="Krishnagiri Dt,";
String mPinCode="635307";
String mState="Tamil Nadu";
String mCountry="India";
Validator validator;
SharedPreferences mSharedPreferences;
    final String MyPREFERENCES = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setSupportActionBar(profile_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mSharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mTitle = mSharedPreferences.getString(pref_login_title, "");
        mFirstName = mSharedPreferences.getString(pref_login_firstname, "");
        mLastName = mSharedPreferences.getString(pref_login_lastname, "");
        mEmailId = mSharedPreferences.getString(pref_login_email, "");
        mContactNo = mSharedPreferences.getString(pref_login_contact, "");
        mAddress1 = mSharedPreferences.getString(pref_login_address, "");
        mCityName = mSharedPreferences.getString(pref_login_city, "");
        mPinCode = mSharedPreferences.getString(pref_login_pincode, "");
        mState = mSharedPreferences.getString(pref_login_state, "");
        mCountry = mSharedPreferences.getString(pref_login_country, "");
        mAddress2 = "";
        mAddress3 = "";


        et_title.setText(": " + mTitle + " " + mFirstName + " " + mLastName);

        et_emailid.setText(": " + mEmailId);
        et_contactno.setText(": " + mContactNo);
        et_address_one.setText(": " + mAddress1);

        et_cityname.setText(": " + mCityName);
        et_statename.setText(": " + mState);
        et_country.setText(": " + mCountry);
        et_pincode.setText(": " + mPinCode);

    }
}
