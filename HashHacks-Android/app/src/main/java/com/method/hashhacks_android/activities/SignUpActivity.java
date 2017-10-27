package com.method.hashhacks_android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.method.hashhacks_android.R;
import com.method.hashhacks_android.api.SignupApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    EditText etName, etAadhar, etAddress, etEmail, etDOB, etPassword, etConfirmPassword;
    RadioButton rbMale, rbFemale;
    RadioButton rbBorrower, rbLender;
    Button btnSignup;


    SharedPreferences sharedPreferences;

    public static final String SHARED_PREFS_NAME = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        findViews();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SignupApi.User user = getUser();

                String url = getResources().getString(R.string.url);
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();

                SignupApi signupApi = retrofit.create(SignupApi.class);

                signupApi.createUser(user).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() != null) {
                            String mobile = response.body();

                            sharedPreferences.edit().putString("mobile", mobile).apply();

                            if (rbBorrower.isChecked()) {
                                Intent i = new Intent(SignUpActivity.this, BorrowerInactiveActivity.class);
                                startActivity(i);
                            } else {
                                Intent i = new Intent(SignUpActivity.this, LenderActivity.class);
                                startActivity(i);
                            }


                        } else {
                            Toast.makeText(SignUpActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


            }
        });
    }

    private SignupApi.User getUser() {
        SignupApi.User user = new SignupApi.User(etName.getText().toString(),
                etAadhar.getText().toString(),
                etAddress.getText().toString(),
                etEmail.getText().toString(),
                etDOB.getText().toString(),
                etPassword.getText().toString(),
                rbMale.isChecked(),
                rbBorrower.isChecked());


        return user;
    }

    private void findViews() {
        etName = (EditText) findViewById(R.id.et_signup_name);
        etPassword = (EditText) findViewById(R.id.et_signup_password);
        etConfirmPassword = (EditText) findViewById(R.id.et_signup_confirm_password);
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