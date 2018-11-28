package com.examp.three.contactUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUs_Model {
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("contactNo")
    @Expose
    private String contactNo;
    @SerializedName("sno")
    @Expose
    private Integer sno;

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

}

