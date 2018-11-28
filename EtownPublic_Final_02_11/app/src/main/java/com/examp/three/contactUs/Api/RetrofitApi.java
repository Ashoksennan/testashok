package com.examp.three.contactUs.Api;


import com.examp.three.activity.faq.Faq_Model;
import com.examp.three.contactUs.ContactUs_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

     @GET("getHelpDetails")
     Call<List<ContactUs_Model>> getContactDetails();

     @GET("getFAQDetails")
     Call<List<Faq_Model>> getFAQDetails();
}
