package com.method.hashhacks_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.method.hashhacks_android.R;

public class BorrowerInactiveActivity extends AppCompatActivity {

    EditText etPurpose, etAmount, etTenure;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_inactive);

        initViews();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO;
            }
        });
    }

    private void initViews() {
        etAmount = (EditText) findViewById(R.id.et_inactive_amount);
        etPurpose = (EditText) findViewById(R.id.et_inactive_purpose);
        etTenure = (EditText) findViewById(R.id.et_inactive_tenure);
        btnSubmit = (Button) findViewById(R.id.btn_inactive_submit);


    }
}
