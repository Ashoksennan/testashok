package com.examp.three;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.examp.three.Grievance_admin.GrievanceAdminActivity;
import com.examp.three.VolleySingleton.AppSingleton;
import com.examp.three.activity.RootNavigation;
import com.examp.three.activity.Shared_Modules.Shared_Common_All_Tax.MakePayment;
import com.examp.three.activity.forgotpassword.ForgotPasswordActivity;
import com.examp.three.activity.paymentgateway.utility.Params;
import com.examp.three.common.Common;
import com.examp.three.common.RetrofitInstance;
import com.examp.three.common.RetrofitInterface;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;

import static com.examp.three.common.Common.API_User_Login;
import static com.examp.three.common.Common.METHOD_LOGIN;
import static com.examp.three.common.Common.METHOD_REGISTRATION;
import static com.examp.three.common.Common.NAMESPACE;
import static com.examp.three.common.Common.SOAP_ACTION_Login;
import static com.examp.three.common.Common.SOAP_ACTION_Register;
import static com.examp.three.common.Common.URL_SOAP_DOMAIN;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_address;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_city;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_contact;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_country;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_district_department;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_email;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_firstname;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_lastname;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_panchayat_department;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_password;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_pincode;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_state;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_streetname;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_title;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_type;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_userid;
import static com.examp.three.common.SharedPreferenceHelper.pref_login_username;

public class Etown_Login extends AppCompatActivity {
    public String TAG = Etown_Login.class.getName();
    MaterialEditText edt_username, edt_signup_otp_mobile, edt_signup_otp, edt_signup_firstname,
            edt_signup_lastname, edt_signup_contactno, edt_signup_email,
            edt_signup_address, edt_signup_cityname, edt_signup_pincode;
    ShowHidePasswordEditText edt_password, edtsignup_password, edtsignupconfirm_password;
    LinearLayout cardlayout_signin, cardlayout_signup;
    LinearLayout rootlayout;
    TextView mforgotpassword, mtitlelogin, signuptxt, signIntxt;
    String Volley_UserId, Volley_Country, Volley_Title, Volley_FirstName, Volley_LastName, Volley_ContactNo, Volley_EmailId, Volley_StreetName, Volley_City, Volley_PinCode, Volley_State, Volley_Address;
    String responseuserId = null;
    android.app.AlertDialog waitingDialog;
    Boolean signandsignupcheck = true;
    Button btn_submit, btn_signup;
    SharedPreferences sharedPreference;
    String MyPREFERENCES = "User";
    String MyDEPARTMENTPREFERENCES = "Department";
    String OtpKey = null;

    String message = null;
    SharedPreferences.Editor editor;
    boolean TimeoutException = false, IndexException, HttpException = false, GeneralException = false;
    String mIntent_Type = null;

    Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{3,5}$", Pattern.CASE_INSENSITIVE);


    @BindView(R.id.btn_public)
    TextView btnPublic;
    Intent intent;

    @BindView(R.id.btn_department)
    TextView btnDepartment;

    Boolean isPubliclogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etown__login);

        ButterKnife.bind(this);

        initViewIds();

        onClick();

    }


    public void initViewIds() {
        intent = getIntent();

        cardlayout_signin = (LinearLayout) findViewById(R.id.cardlayout_signin);
        cardlayout_signup = (LinearLayout) findViewById(R.id.cardlayout_signup);
        sharedPreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mforgotpassword = (TextView) findViewById(R.id.forgotpassword);

        mtitlelogin = (TextView) findViewById(R.id.titleoflogin);
        signuptxt = (TextView) findViewById(R.id.signuptxt);
        signIntxt = (TextView) findViewById(R.id.signIntxt);


        rootlayout = (LinearLayout) findViewById(R.id.rootlayout);
        edt_username = (MaterialEditText) findViewById(R.id.edt_username);
        edt_password = (ShowHidePasswordEditText) findViewById(R.id.edt_password);
        edt_password.setEnabled(true);

        edt_signup_firstname = (MaterialEditText) findViewById(R.id.edt_signup_firstname);
        edt_signup_lastname = (MaterialEditText) findViewById(R.id.edt_signup_lastname);
        edt_signup_contactno = (MaterialEditText) findViewById(R.id.edt_signup_contactno);
        edt_signup_email = (MaterialEditText) findViewById(R.id.edt_signup_email);


        edt_signup_address = (MaterialEditText) findViewById(R.id.edt_signup_address);
        edt_signup_cityname = (MaterialEditText) findViewById(R.id.edt_signup_cityname);
        edt_signup_pincode = (MaterialEditText) findViewById(R.id.edt_signup_pincode);
        edtsignup_password = (ShowHidePasswordEditText) findViewById(R.id.edtsignup_password);
        edtsignupconfirm_password = (ShowHidePasswordEditText) findViewById(R.id.edtsignupconfirm_password);
        edt_signup_otp = (MaterialEditText) findViewById(R.id.edt_signup_otp);
        edt_signup_otp_mobile = (MaterialEditText) findViewById(R.id.edt_signup_otp_mobile);


        btn_submit = (Button) findViewById(R.id.btn_signin);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        edt_signup_address.setEnabled(false);
        edt_signup_cityname.setEnabled(false);
        edt_signup_pincode.setEnabled(false);
        edtsignup_password.setEnabled(false);
        edtsignupconfirm_password.setEnabled(false);

        cardlayout_signup.setVisibility(View.GONE);

    }

    public void onClick() {
        btnPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPubliclogin = true;
                btnDepartment.setBackground(getResources().getDrawable(R.drawable.bg_login_textview_white));
                btnDepartment.setTextColor(getResources().getColor(R.color.grey));

                btnPublic.setBackground(getResources().getDrawable(R.drawable.bg_login_textview_cp));
                btnPublic.setTextColor(getResources().getColor(R.color.white));

                edt_username.setHint(getResources().getString(R.string.usernamehint));

                edt_username.getText().clear();
                edt_password.getText().clear();

            }
        });


        btnDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPubliclogin = false;

                btnPublic.setBackground(getResources().getDrawable(R.drawable.bg_login_textview_white));
                btnPublic.setTextColor(getResources().getColor(R.color.grey));

                btnDepartment.setBackground(getResources().getDrawable(R.drawable.bg_login_textview_cp));
                btnDepartment.setTextColor(getResources().getColor(R.color.white));

                edt_username.setHint("UserId");

                edt_username.getText().clear();
                edt_password.getText().clear();
            }
        });

        mforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etown_Login.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        edt_signup_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches() && s.toString().endsWith(".com")) {
                    edt_signup_otp.setVisibility(View.VISIBLE);


                    checkAlreadyUser("first3", edt_signup_email.getText().toString(), "signup");

                } else {
                    edt_signup_otp.setVisibility(View.GONE);


                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_signup_contactno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().length() == 10) {


                    boolean satisfy = isValid(edt_signup_contactno.getText().toString());

                    if (satisfy) {
                        edt_signup_otp_mobile.setVisibility(View.VISIBLE);
                        edt_signup_otp.setVisibility(View.GONE);
                        checkAlreadyUser("first2", edt_signup_contactno.getText().toString(), "signup");

                    } else {
                        edt_signup_otp_mobile.setVisibility(View.GONE);
                        Snackbar.make(rootlayout, "Invalid Mobile Number", Snackbar.LENGTH_SHORT).show();


                        edt_signup_address.setEnabled(false);
                        edt_signup_cityname.setEnabled(false);
                        edt_signup_pincode.setEnabled(false);
                        edtsignup_password.setEnabled(false);
                        edtsignupconfirm_password.setEnabled(false);

                    }
                } else if (s.toString().length() >= 11) {
                    edt_signup_otp_mobile.setVisibility(View.GONE);
                    Snackbar.make(rootlayout, "Enter Mobile Number", Snackbar.LENGTH_SHORT).show();


                    edt_signup_address.setEnabled(false);
                    edt_signup_cityname.setEnabled(false);
                    edt_signup_pincode.setEnabled(false);
                    edtsignup_password.setEnabled(false);
                    edtsignupconfirm_password.setEnabled(false);


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edt_signup_otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.length() == 6) {

                    Log.e(TAG, "" + OtpKey + " OTP " + s.toString());
                    if (Integer.parseInt(OtpKey) == Integer.parseInt(edt_signup_otp.getText().toString())) {

                        edt_signup_address.setEnabled(true);
                        edt_signup_cityname.setEnabled(true);
                        edt_signup_pincode.setEnabled(true);
                        edtsignup_password.setEnabled(true);
                        edtsignupconfirm_password.setEnabled(true);

                        edt_signup_email.setEnabled(false);
                        edt_signup_otp_mobile.setVisibility(View.GONE);

                        edt_signup_otp.setVisibility(View.GONE);

                        edt_signup_address.requestFocus();

                    } else {
                        Log.e(TAG, "" + OtpKey + "OTP fails " + s.toString());
                        Snackbar.make(rootlayout, "OTP not matched", Snackbar.LENGTH_SHORT).show();

                    }


                } else {
                    edt_signup_address.setEnabled(false);
                    edt_signup_cityname.setEnabled(false);
                    edt_signup_pincode.setEnabled(false);
                    edtsignup_password.setEnabled(false);
                    edtsignupconfirm_password.setEnabled(false);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_signup_otp_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.length() == 6) {

                    Log.e(TAG, "" + OtpKey + " OTP " + s.toString());
                    if (Integer.parseInt(OtpKey) == Integer.parseInt(edt_signup_otp_mobile.getText().toString())) {

                        edt_signup_address.setEnabled(true);
                        edt_signup_cityname.setEnabled(true);
                        edt_signup_pincode.setEnabled(true);
                        edtsignup_password.setEnabled(true);
                        edtsignupconfirm_password.setEnabled(true);

                        edt_signup_otp.setVisibility(View.GONE);

                        edt_signup_contactno.setEnabled(false);
                        edt_signup_otp_mobile.setVisibility(View.GONE);

                        edt_signup_email.requestFocus();

                    } else {
                        Log.e(TAG, "" + OtpKey + "OTP fails " + s.toString());
                        Snackbar.make(rootlayout, "OTP not matched", Snackbar.LENGTH_SHORT).show();

                    }


                } else {
                    edt_signup_address.setEnabled(false);
                    edt_signup_cityname.setEnabled(false);
                    edt_signup_pincode.setEnabled(false);
                    edtsignup_password.setEnabled(false);
                    edtsignupconfirm_password.setEnabled(false);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtitlelogin.setText("Sign Up");
                signandsignupcheck = false;
                cardlayout_signin.setVisibility(View.GONE);
                cardlayout_signup.setVisibility(View.VISIBLE);
            }
        });

        signIntxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signandsignupcheck = true;
                mtitlelogin.setText("Login");
                cardlayout_signin.setVisibility(View.VISIBLE);
                cardlayout_signup.setVisibility(View.GONE);
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Common.isNetworkAvailable(getApplicationContext())) {


                    Snackbar.make(rootlayout, "No Internet Connection !", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(edt_signup_firstname.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter FirstName", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edt_signup_lastname.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter LastName", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edt_signup_contactno.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter Mobile Number", Snackbar.LENGTH_SHORT).show();

                    return;

                }

                if (!isValid(edt_signup_contactno.getText().toString())) {

                    Snackbar.make(rootlayout, "Invalid Mobile Number", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(edt_signup_email.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter Email Id", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (!EMAIL_PATTERN.matcher(edt_signup_email.getText().toString()).matches()) {

                    Snackbar.make(rootlayout, "Email Id Pattern Invalid", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edt_signup_address.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter Address", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edt_signup_cityname.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter City Name", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edt_signup_pincode.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter Pincode", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (edt_signup_pincode.getText().toString().length() < 6) {

                    Snackbar.make(rootlayout, "Enter minimum of 6 characters", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtsignup_password.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter Password", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edtsignupconfirm_password.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter Confirm Password", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (!edtsignupconfirm_password.getText().toString().equalsIgnoreCase(edtsignup_password.getText().toString())) {

                    Snackbar.make(rootlayout, "Password doesn't match", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                new Registration().execute();

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Common.isNetworkAvailable(getApplicationContext())) {


                    Snackbar.make(rootlayout, "No Internet Connection !", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(edt_username.getText().toString())) {

                    Snackbar.make(rootlayout, "Enter Username", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edt_password.getText().toString())) {
                    Snackbar.make(rootlayout, "Enter Password", Snackbar.LENGTH_SHORT).show();


                    return;
                }


                if (isPubliclogin) {
                    new LoginTask().execute();
                } else {
                    new DepermentLogin().execute();
                }

            }
        });

    }

    private void checkAlreadyUser(String type, final String username, final String typeoflogin) {

        String REQUEST_TAG = "apigetFinYear";

        waitingDialog = new SpotsDialog(Etown_Login.this);
        waitingDialog.show();
        waitingDialog.setCancelable(false);
        String url = API_User_Login + "QType=" + URLEncoder.encode(type) + "&Input=" + URLEncoder.encode(username) + "";
        Log.e(TAG, "url" + url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                    waitingDialog.dismiss();

                    if (response.length() > 0) {

                        for (int j = 0; j < response.length(); j++) {
                            JSONObject jsonObject = (JSONObject) response.get(j);
                            Volley_UserId = jsonObject.getString("UserId");
                            Volley_Title = jsonObject.getString("Title");
                            Volley_FirstName = jsonObject.getString("FirstName");
                            Volley_LastName = jsonObject.getString("LastName");
                            Volley_ContactNo = jsonObject.getString("ContactNo");
                            Volley_EmailId = URLDecoder.decode(jsonObject.getString("EmailId"));
                            Volley_StreetName = jsonObject.getString("StreetName");
                            Volley_City = jsonObject.getString("City");
                            Volley_PinCode = jsonObject.getString("PinCode");
                            Volley_State = jsonObject.getString("State");
                            Volley_Country = jsonObject.getString("Country");
                            Volley_Address = jsonObject.getString("Address");

                        }

                        edt_password.requestFocus();
                        edt_password.setEnabled(true);

                        if (typeoflogin.equalsIgnoreCase("signup")) {
                            hideSoftKeyboard();

                            Snackbar.make(rootlayout, "Your are Already Having Login Credentials", Snackbar.LENGTH_LONG).setAction("Click Here To Login", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    signandsignupcheck = true;

//                                    msigninorsignup.setText("Sign Up");
                                    mtitlelogin.setText("Login");

//                                    mdoyouhaveaccount.setText(R.string.dohaveaccount);


                                    cardlayout_signin.setVisibility(View.VISIBLE);
                                    mforgotpassword.setVisibility(View.VISIBLE);

                                    cardlayout_signup.setVisibility(View.GONE);
                                }
                            }).setActionTextColor(Color.WHITE).show();

                        }

                    } else {
                        sendOTP(username);

//                        edt_password.setEnabled(false);
//                        if (typeoflogin.equalsIgnoreCase("signup")) {
//                            hideSoftKeyboard();
//
//                            Snackbar.make(rootlayout, "Your are not created Login", Snackbar.LENGTH_INDEFINITE).setAction("Click Here To Login", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    mtitlelogin.setText("Sign Up");
//                                    signandsignupcheck = false;
//
//                                    cardlayout_signin.setVisibility(View.GONE);
//                                    mforgotpassword.setVisibility(View.GONE);
//                                    cardlayout_signup.setVisibility(View.VISIBLE);
//                                }
//                            }).setActionTextColor(Color.WHITE).show();
//                        } else {


                       // }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    waitingDialog.dismiss();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                waitingDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    SnackShowTop("Time out", rootlayout);

                } else if (error instanceof AuthFailureError) {

                    SnackShowTop("Connection Time out", rootlayout);

                } else if (error instanceof ServerError) {

                    SnackShowTop("Could not connect server", rootlayout);

                } else if (error instanceof NetworkError) {

                    SnackShowTop("No Internet connection", rootlayout);

                } else if (error instanceof ParseError) {

                    SnackShowTop("Parse Error", rootlayout);

                } else {

                    SnackShowTop(error.getMessage(), rootlayout);

                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("accesstoken", Common.ACCESS_TOKEN);
                return params;
            }
        };

        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest, REQUEST_TAG);

    }

    public static boolean isValid(String s) {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    private class Registration extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog = new ProgressDialog(Etown_Login.this);
        String status;

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Please wait...");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Void doInBackground(final Void... unused) {

            SoapObject request = new SoapObject(NAMESPACE, METHOD_REGISTRATION);

            request.addProperty("sTitle", "Mrs");
            request.addProperty("sFirstName", edt_signup_firstname.getText().toString().trim());


            request.addProperty("sLastname", edt_signup_lastname.getText().toString().trim());
            request.addProperty("sEmailId", edt_signup_email.getText().toString().trim());
            request.addProperty("sContactNo", edt_signup_contactno.getText().toString().trim());
            request.addProperty("sAddress", edt_signup_address.getText().toString().trim());
            request.addProperty("sDoorNo", "null");
            request.addProperty("sStreetName", "null");
            request.addProperty("sCity", edt_signup_cityname.getText().toString().trim());
            request.addProperty("sPincode", edt_signup_pincode.getText().toString().trim());
            request.addProperty("sState", "Tamilnadu");
            request.addProperty("sCountry", "India");
            request.addProperty("sPassword", edtsignupconfirm_password.getText().toString().trim());
            request.addProperty("sRType", "Android");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_SOAP_DOMAIN);

            try {
                androidHttpTransport.call(SOAP_ACTION_Register, envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                String responseJSON = response.toString();
                String sArray[];
                System.out.println("Status : " + responseJSON);
                try {
                    sArray = responseJSON.split(",");
                    status = sArray[0];
                    responseuserId = sArray[1];

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } catch (SocketTimeoutException e) {
                TimeoutException = true;
                e.printStackTrace();
            } catch (UnknownHostException e) {
                HttpException = true;
                e.printStackTrace();
            } catch (Exception e) {
                GeneralException = true;
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (status.equals("S")) {

                editor = sharedPreference.edit();
                editor.putString(pref_login_username, edt_signup_contactno.getText().toString());
                editor.putString(pref_login_password, edtsignupconfirm_password.getText().toString());

                editor.putString(pref_login_type, "user");


                editor.putString(pref_login_userid, responseuserId);
                editor.putString(pref_login_title, "Mrs");
                editor.putString(pref_login_firstname, edt_signup_firstname.getText().toString());
                editor.putString(pref_login_lastname, edt_signup_lastname.getText().toString());
                editor.putString(pref_login_contact, edt_signup_contactno.getText().toString());
                editor.putString(pref_login_email, edt_signup_email.getText().toString());
                editor.putString(pref_login_streetname, "null");
                editor.putString(pref_login_city, edt_signup_cityname.getText().toString());
                editor.putString(pref_login_pincode, edt_signup_pincode.getText().toString());
                editor.putString(pref_login_state, "Tamilnadu");
                editor.putString(pref_login_country, "India");

                editor.putString(pref_login_address, edt_signup_address.getText().toString());

                editor.apply();

                Toast.makeText(Etown_Login.this, "Registration Success !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Etown_Login.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();


            } else {
                Toast.makeText(Etown_Login.this,
                        "Registration Failed",
                        Toast.LENGTH_LONG).show();
            }
            if (TimeoutException) {
                Toast.makeText(Etown_Login.this,
                        "Unable to connect to server, Please try again later",
                        Toast.LENGTH_LONG).show();

            } else if (HttpException) {
                Toast.makeText(Etown_Login.this,
                        "No Internet connection",
                        Toast.LENGTH_LONG).show();

            } else if (GeneralException) {
                Toast.makeText(Etown_Login.this, "Please try again later",
                        Toast.LENGTH_LONG).show();
            }
            TimeoutException = false;
            HttpException = false;
            GeneralException = false;
        }
    }

    private void SnackShowTop(String message, View view) {
        Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View view_layout = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view_layout.getLayoutParams();
        params.gravity = Gravity.TOP;
        view_layout.setLayoutParams(params);
        snack.show();
    }


    class DepermentLogin extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialogdepart = new ProgressDialog(Etown_Login.this);
        int statuscode;
        String loginStatus;

        @Override
        protected void onPreExecute() {
            dialogdepart.setMessage("Please wait...");
            dialogdepart.show();
            dialogdepart.setCancelable(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            final String SOAP_ACTION = "http://tempuri.org/EtownLoginDetails";
            final String NAMESPACE = "http://tempuri.org/";
            final String METHOD_NAME = "EtownLoginDetails";
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty(Params.sUserId, edt_username.getText().toString());
            request.addProperty(Params.sPassword, edt_password.getText().toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL_SOAP_DOMAIN);


            try {
                httpTransportSE.call(SOAP_ACTION, envelope);
                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
                String reponseJson = soapPrimitive.toString();

                JSONArray jsonArray = new JSONArray(reponseJson);


                loginStatus = jsonArray.getJSONObject(0).getString("LoginStatus");


                if (loginStatus.equalsIgnoreCase("Success")) {

                    String UserId = jsonArray.getJSONObject(0).getString("UserId");
                    String District = jsonArray.getJSONObject(0).getString("District");
                    String Panchayat = jsonArray.getJSONObject(0).getString("Panchayat");
                    String DistrictId = jsonArray.getJSONObject(0).getString("DistrictId");
                    String PanchayatId = jsonArray.getJSONObject(0).getString("PanchayatId");
//                    String DistrictTamil = jsonArray.getJSONObject(0).getString("DistrictTamil");
//                    String PanchayatTamil = jsonArray.getJSONObject(0).getString("PanchayatTamil");
                    String UserName = jsonArray.getJSONObject(0).getString("UserName");
                    String CollUserName = jsonArray.getJSONObject(0).getString("CollUserName");
                    String UserRole = jsonArray.getJSONObject(0).getString("UserRole");
                    String MobileNo = jsonArray.getJSONObject(0).getString("MobileNo");
                    String SMSRights = jsonArray.getJSONObject(0).getString("SMSRights");
                    String VerifyStatus = jsonArray.getJSONObject(0).getString("VerifyStatus");
                    String VerifyCode = jsonArray.getJSONObject(0).getString("VerifyCode");
                    String GrievanceRights = jsonArray.getJSONObject(0).getString("GrievanceRights");


                    if (CollUserName.equalsIgnoreCase("admin") && GrievanceRights.equalsIgnoreCase("Y")) {

                        editor = sharedPreference.edit();
                        editor.clear();

                        editor.putString(pref_login_username, UserName);
                        editor.putString(pref_login_password, edt_password.getText().toString());
                        editor.putString(pref_login_type, "department");


                        editor.putString(pref_login_userid, UserId);
                        editor.putString(pref_login_contact, MobileNo);
                        editor.putString(pref_login_district_department, District);
                        editor.putString(pref_login_panchayat_department, Panchayat);
                        editor.apply();


                        statuscode = 1;
                       /* editor.putString(Common.DepartmentDistrict,District);
                        editor.putString(Common.DepartmentPanchayat,Panchayat);
                        editor.putString(Common.DepartmentUserId,UserId);
                        editor.putString(Common.DepartmentUserName,UserName);*/

                        Log.e("vvxd=>", sharedPreference.getString(pref_login_district_department, "") + " fgbf");
                        Log.e("vvxd=>", sharedPreference.getString(pref_login_panchayat_department, "") + " fgbf");
                        Log.e("vvxd=>", sharedPreference.getString(pref_login_userid, "") + " fgbf");
                        Log.e("vvxd=>", sharedPreference.getString(pref_login_username, "") + " fgbf");

                    }


                } else {
                    statuscode = 0;

                }

            } catch (SocketTimeoutException e) {
                TimeoutException = true;
                e.printStackTrace();
            } catch (UnknownHostException e) {
                HttpException = true;
                e.printStackTrace();
            } catch (JSONException e) {
                IndexException = true;
                e.printStackTrace();
            } catch (Exception e) {
                GeneralException = true;
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (this.dialogdepart.isShowing()) {
                this.dialogdepart.dismiss();
            }
            if (TimeoutException) {
                SnackShowTop("Could not connect to server, Please try again later", rootlayout);
            } else if (HttpException) {
                SnackShowTop("No Internet connection", rootlayout);
            } else if (GeneralException) {
                SnackShowTop("Something went wrong please try later", rootlayout);
            } else if (statuscode == 1) {

                Intent i = new Intent(getApplicationContext(), GrievanceAdminActivity.class);
                startActivity(i);
                finish();

            } else if (statuscode == 0) {
                Toast.makeText(Etown_Login.this, loginStatus, Toast.LENGTH_SHORT).show();

            } else if (IndexException) {
                SnackShowTop("Incorrect Username / Password", rootlayout);

            }

            TimeoutException = false;
            HttpException = false;
            GeneralException = false;
            IndexException = false;

        }
    }

    private class LoginTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog = new ProgressDialog(Etown_Login.this);
        int loginStatus;

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Logging in...");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Void doInBackground(final Void... unused) {


            SoapObject request = new SoapObject(NAMESPACE, METHOD_LOGIN);
            request.addProperty(Params.sUserId, edt_username.getText().toString());
            request.addProperty(Params.sPassword, edt_password.getText().toString());

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_SOAP_DOMAIN);


            try {
                androidHttpTransport.call(SOAP_ACTION_Login, envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                String responseJSON = response.toString();
                System.out.println("responseJSON = " + responseJSON);
                JSONArray jsonArray = new JSONArray(responseJSON);
                System.out.println("jsonArray.length() = " + jsonArray.length());


                if (jsonArray.length() > 0) {
                    loginStatus = 1;

                } else if (jsonArray.getJSONObject(0).getString(Params.sFirstName) == null) {
                    loginStatus = 0;
                }
                Volley_Title = jsonArray.getJSONObject(0).getString(Params.sTitle);
                Volley_FirstName = jsonArray.getJSONObject(0).getString(Params.sFirstName);
                Volley_LastName = jsonArray.getJSONObject(0).getString(Params.sLastName);
                Volley_UserId = jsonArray.getJSONObject(0).getString(Params.cUserId);
                Volley_ContactNo = jsonArray.getJSONObject(0).getString(Params.sContactNo);
                Volley_EmailId = jsonArray.getJSONObject(0).getString(Params.sEmailId);
                // = jsonArray.getJSONObject(0).getString(Params.sDoorNo);
                Volley_StreetName = jsonArray.getJSONObject(0).getString(Params.sStreetName);
                Volley_Address = jsonArray.getJSONObject(0).getString(Params.sAddress);
                Volley_City = jsonArray.getJSONObject(0).getString(Params.sCity);
                Volley_PinCode = jsonArray.getJSONObject(0).getString(Params.sPincode);
                Volley_State = jsonArray.getJSONObject(0).getString(Params.sState);
                Volley_Country = jsonArray.getJSONObject(0).getString(Params.sCountry);


            } catch (SocketTimeoutException e) {
                TimeoutException = true;
                e.printStackTrace();
            } catch (UnknownHostException e) {
                HttpException = true;
                e.printStackTrace();
            } catch (JSONException e) {
                IndexException = true;
                e.printStackTrace();
            } catch (Exception e) {
                GeneralException = true;
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (TimeoutException) {
                SnackShowTop("Could not connect to server, Please try again later", rootlayout);
            } else if (HttpException) {
                SnackShowTop("Please check your Internet connection", rootlayout);
            } else if (GeneralException) {
                SnackShowTop("Something went wrong please try later", rootlayout);
            } else if (loginStatus == 1) {

                editor = sharedPreference.edit();

                editor.putString(pref_login_username, edt_username.getText().toString());
                editor.putString(pref_login_password, edt_password.getText().toString());
                editor.putString(pref_login_type, "user");

                editor.putString(pref_login_userid, Volley_UserId);
                editor.putString(pref_login_title, Volley_Title);
                editor.putString(pref_login_firstname, Volley_FirstName);
                editor.putString(pref_login_lastname, Volley_LastName);
                editor.putString(pref_login_contact, Volley_ContactNo);
                editor.putString(pref_login_email, Volley_EmailId);
                editor.putString(pref_login_streetname, Volley_StreetName);
                editor.putString(pref_login_city, Volley_City);
                editor.putString(pref_login_pincode, Volley_PinCode);
                editor.putString(pref_login_state, Volley_State);
                editor.putString(pref_login_address, Volley_Address);
                editor.putString(pref_login_country, Volley_Country);

                editor.apply();

                if (intent != null) {
                    mIntent_Type = intent.getStringExtra(Common.Type);


                    if (!TextUtils.isEmpty(mIntent_Type)) {


                        Intent splash = null;
                        if (mIntent_Type.equalsIgnoreCase("Building")) {
                            splash = new Intent(Etown_Login.this, NewNavigation.class);
                            splash.putExtra(Common.Type, mIntent_Type);

                        } else if (mIntent_Type.equalsIgnoreCase("P")) {
                            splash = new Intent(Etown_Login.this, MakePayment.class);
                            splash.putExtra(Common.Type, mIntent_Type);

                        } else if (mIntent_Type.equalsIgnoreCase("PR")) {
                            splash = new Intent(Etown_Login.this, MakePayment.class);
                            splash.putExtra(Common.Type, mIntent_Type);

                        } else if (mIntent_Type.equalsIgnoreCase("W")) {
                            splash = new Intent(Etown_Login.this, MakePayment.class);
                            splash.putExtra(Common.Type, mIntent_Type);

                        } else if (mIntent_Type.equalsIgnoreCase("Trade")) {
                            splash = new Intent(Etown_Login.this, NewNavigation.class);
                            splash.putExtra(Common.Type, mIntent_Type);

                        } else if (!mIntent_Type.equalsIgnoreCase("Home")) {
                            splash = new Intent(Etown_Login.this, RootNavigation.class);
                            splash.putExtra(Common.Type, mIntent_Type);

                        } else
                            splash = new Intent(Etown_Login.this, HomeActivity.class);

                        saveLogDetails(Volley_UserId, Volley_FirstName, "A", Volley_ContactNo);

                        startActivity(splash);
                        finish();
                    } else
                        startActivity(new Intent(Etown_Login.this, HomeActivity.class));

                }

            } else if (IndexException) {
                SnackShowTop("Incorrect username/password", rootlayout);

            }

            TimeoutException = false;
            HttpException = false;
            GeneralException = false;
            IndexException = false;
        }
    }


    public void saveLogDetails(String tempUserId, String username, String templogtype, String contactNo) {

        String tempuserName = "";
        String tempudateTime = "";
        String versionName = "";
        try {
            tempuserName = URLEncoder.encode(username, "UTF-8");
//            tempudateTime = URLEncoder.encode(tempDateTime, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            PackageInfo pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = pinfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String Device_androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);


        String URL_SAVE = Common.METHOD_NAME_LOGS + "?" +
                "UserId=" + tempUserId + "&UserName=" + tempuserName +
                "&LType=" + templogtype + "&VersionName=" + versionName + "&DeviceId=" + Device_androidId + "&contactNo=" + contactNo;

        System.out.println("URL_SAVE = " + URL_SAVE);


        final JsonObjectRequest request = new JsonObjectRequest(URL_SAVE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//
                        System.out.println("jsonArray = " + jsonObject);
                        try {

                            System.out.println(jsonObject.getString("code"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                volleyError.printStackTrace();
            }
        });

        AppSingleton.getInstance(Etown_Login.this).addToRequestQueue(request, "saveLogs");

    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                message = intent.getStringExtra("message");
                Log.e("messag= =>", message + " <====");


            }
        }
    };

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }


    public void sendOTP(String number) {

        waitingDialog = new SpotsDialog(Etown_Login.this);
        waitingDialog.show();
        waitingDialog.setCancelable(false);

        RetrofitInterface apiInterface = RetrofitInstance.getRetrofitPaypre().create(RetrofitInterface.class);

        Call call = apiInterface.sendOTP(number);

        call.enqueue(new Callback() {


            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                if (response.isSuccessful()) {

                    if (BuildConfig.DEBUG) {

                        Log.e(TAG, "" + response.raw().toString());
                    }

                    String sResponse = new Gson().toJson(response.body());

                    try {
                        JSONObject jsonObject = new JSONObject(sResponse);
                        String Message = jsonObject.optString("Message");

                        if (Message.equalsIgnoreCase("Success")) {

                            OtpKey = String.valueOf(jsonObject.optInt("PassKey"));


                            //edt_signup_otp.requestFocus();

                            hideSoftKeyboard();


                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                } else

                {

                    Snackbar.make(rootlayout, "Otp Not Send!", Snackbar.LENGTH_SHORT).show();
                    hideSoftKeyboard();
                }
                waitingDialog.dismiss();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                waitingDialog.dismiss();
                String errorType = null;
                if (t instanceof IOException) {

                    errorType = "Timeout";
                    Snackbar.make(rootlayout, "No Internet Connection !", Snackbar.LENGTH_SHORT).show();

                } else if (t instanceof IllegalStateException) {

                    errorType = "ConversionError";
                    Snackbar.make(rootlayout, errorType, Snackbar.LENGTH_SHORT).show();

                } else {

                    errorType = "Error";
                    Snackbar.make(rootlayout, errorType, Snackbar.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
        }


    }
}
