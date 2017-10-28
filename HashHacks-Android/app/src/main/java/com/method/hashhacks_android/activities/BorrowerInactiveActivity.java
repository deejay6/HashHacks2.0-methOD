package com.method.hashhacks_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.method.hashhacks_android.R;
import com.method.hashhacks_android.api.BorrowerApi;
import com.method.hashhacks_android.models.Borrower;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BorrowerInactiveActivity extends AppCompatActivity {

    public static final String TAG = "BorrInAc";

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
                String url = getString(R.string.url);
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();

                BorrowerApi borrowerApi = retrofit.create(BorrowerApi.class);
                BorrowerApi.LoanDetail loanDetail = new BorrowerApi.LoanDetail(etPurpose.getText().toString(),
                        etTenure.getText().toString(),
                        Integer.valueOf(etAmount.getText().toString()));
                Log.d(TAG, "onClick: " + getSharedPreferences(LoginActivity.SHARED_PREFS_NAME, MODE_PRIVATE).getString("mobile", "123"));
                borrowerApi.createLoan(loanDetail, getSharedPreferences(LoginActivity.SHARED_PREFS_NAME, MODE_PRIVATE).getString("mobile", "123")).enqueue(new Callback<BorrowerApi.PuneetResult>() {
                    @Override
                    public void onResponse(Call<BorrowerApi.PuneetResult> call, Response<BorrowerApi.PuneetResult> response) {
                        if (response.body() != null) {

//                            String loadId = response.body();

                            Intent i = new Intent(BorrowerInactiveActivity.this, BorrowerActiveActivity.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(BorrowerInactiveActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BorrowerApi.PuneetResult> call, Throwable t) {

                    }
                });
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
