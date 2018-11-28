package com.examp.three.activity.Feedback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.examp.three.R;

public class Feedback extends AppCompatActivity implements View.OnClickListener {
    EditText subject_et, email_et, msg_et;
    Button btn_send;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Feedback Form");

        subject_et = (EditText) findViewById(R.id.subject_et);
        email_et = (EditText) findViewById(R.id.email_et);
        msg_et = (EditText) findViewById(R.id.msg_et);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

    }

    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("message/rfc822");
        intent.setData(Uri.parse("mailto:" + email_et.getText().toString().trim()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject_et.getText().toString().trim());
        intent.putExtra(Intent.EXTRA_TEXT, msg_et.getText().toString().trim());
        startActivity(Intent.createChooser(intent, "Send Email"));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send) {
            doValidation();
        }
    }

    public void doValidation() {
        if (TextUtils.isEmpty(subject_et.getText().toString())) {
            Toast.makeText(Feedback.this, "Enter Subject", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(msg_et.getText().toString())) {
            Toast.makeText(Feedback.this, "Enter Message", Toast.LENGTH_SHORT).show();
            return;
        } else {
            sendEmail();
        }
    }
}
