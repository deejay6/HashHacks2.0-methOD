package com.method.hashhacks_android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.method.hashhacks_android.FontsOverride;
import com.method.hashhacks_android.R;
import com.method.hashhacks_android.api.BorrowerApi;
import com.method.hashhacks_android.api.LoginApi;
import com.method.hashhacks_android.models.Borrower;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText etMobile, etPassword;
    Button btnLogin, btnSignup, btnBorrower, btnInactive;
    SharedPreferences sharedPreferences;

    public static final String SHARED_PREFS_NAME = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA,getWindow());

        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        findViews();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mobile = etMobile.getText().toString();
                String password = etPassword.getText().toString();

                String url = getResources().getString(R.string.url);
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();
                LoginApi loginApi = retrofit.create(LoginApi.class);

                loginApi.checkIfBorrower(new LoginApi.LoginDetails(mobile, password), mobile).enqueue(new Callback<LoginApi.Res>() {
                    @Override
                    public void onResponse(Call<LoginApi.Res> call, Response<LoginApi.Res> response) {

                        boolean isBorrower;
                        if (response.body() != null) {
                            isBorrower = response.body().getBorrower();


                            sharedPreferences.edit().putString("mobile", mobile).apply();

                            if (isBorrower) {


                                String url = getResources().getString(R.string.url);
                                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();

                                BorrowerApi borrowerApi = retrofit.create(BorrowerApi.class);

                                borrowerApi.getBorrower(mobile).enqueue(new Callback<Borrower>() {
                                    @Override
                                    public void onResponse(Call<Borrower> call, Response<Borrower> response) {
                                        if (response.body() != null) {

                                            Borrower borrower = response.body();

                                            if (borrower.getActive()) {
                                                Intent i = new Intent(LoginActivity.this, BorrowerActiveActivity.class);
                                                startActivity(i);
                                            } else {
                                                Intent i = new Intent(LoginActivity.this, BorrowerInactiveActivity.class);
                                                startActivity(i);
                                            }


                                        } else {
                                            Toast.makeText(LoginActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<Borrower> call, Throwable t) {

                                    }
                                });


                            } else {
                                Intent i = new Intent(LoginActivity.this, LenderActivity.class);
                                startActivity(i);
                            }


                        } else {
                            Toast.makeText(LoginActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginApi.Res> call, Throwable t) {

                    }
                });
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        btnBorrower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, BorrowerActiveActivity.class);
                startActivity(i);

            }
        });

        btnInactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, BorrowerInactiveActivity.class);
                startActivity(i);
            }
        });
    }


    private void findViews() {
        etMobile = (EditText) findViewById(R.id.et_login_mobile);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login_enter);
        btnSignup = (Button) findViewById(R.id.btn_login_signup);
        btnBorrower = (Button) findViewById(R.id.btn_borrowerSingIn);
        btnInactive = (Button) findViewById(R.id.btn_borrowerInactiveSingIn);

    }
}