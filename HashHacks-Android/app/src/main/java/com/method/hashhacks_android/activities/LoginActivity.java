package com.method.hashhacks_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.method.hashhacks_android.R;

public class LoginActivity extends AppCompatActivity {

    EditText etMobile, etPassword;
    Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isBorrower()) {

                } else {
                    Intent i = new Intent(LoginActivity.this, LenderActivity.class);
                    startActivity(i);
                }

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // rbBorrower.isChecked();

                // Save in shared preference if borrower or lender

                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }


    private boolean isBorrower() {
        // TODO:
        return false;
    }

    private void findViews() {
        etMobile = (EditText) findViewById(R.id.et_login_mobile);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login_enter);
        btnSignup = (Button) findViewById(R.id.btn_login_signup);


    }
}
