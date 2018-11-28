package com.examp.three.activity.faq;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Faq_Model {
@SerializedName("sNo")
@Expose
private String sNo;
    @SerializedName("faqQuestion")
    @Expose
    private String faqQuestion;
    @SerializedName("faqAnswer")
    @Expose
    private String faqAnswer;

    public String getSNo() {
        return sNo;
    }

    public void setSNo(String sNo) {
        this.sNo = sNo;
    }

    public String getFaqQuestion() {
        return faqQuestion;
    }

    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    public String getFaqAnswer() {
        return faqAnswer;
    }

    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }


}
