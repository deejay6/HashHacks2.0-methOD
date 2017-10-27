package com.method.hashhacks_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SignUpActivity extends AppCompatActivity {

    EditText etName, etAadhar, etAddress, etEmail, etDOB;
    RadioButton rbMale, rbFemale;
    RadioButton rbBorrower, rbLender;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViews();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Sign up the person and begin dashboard activity
            }
        });
    }

    private void findViews() {
        etName = (EditText) findViewById(R.id.et_signup_name);
        etAadhar = (EditText) findViewById(R.id.et_signup_aadhar);
        etAddress = (EditText) findViewById(R.id.et_signup_addresss);
        etEmail = (EditText) findViewById(R.id.et_signup_email);
        etDOB = (EditText) findViewById(R.id.et_signup_date);
        rbBorrower = (RadioButton) findViewById(R.id.rb_signup_borrower);
        rbLender = (RadioButton) findViewById(R.id.rb_signup_lender);
        rbMale = (RadioButton) findViewById(R.id.rb_signup_male);
        rbFemale = (RadioButton) findViewById(R.id.rb_signup_female);
        btnSignup = (Button) findViewById(R.id.btn_signup_enter);

    }
}
