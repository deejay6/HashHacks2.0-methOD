package com.method.hashhacks_android.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.method.hashhacks_android.FontsOverride;
import com.method.hashhacks_android.R;
import com.method.hashhacks_android.api.BorrowerApi;
import com.method.hashhacks_android.models.Borrower;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BorrowerActiveActivity extends AppCompatActivity {
    TextView tvID, tvPurpose, tvAmount, tvInterest, tvTenure, tvTimeLeft, tvAmountRemaining;
    Button btnClaim;
    Borrower borrower;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_active);

        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA, getWindow());
        fetchBorrower();

        initViews();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BorrowerActiveActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

    }

    private void setViews() {
        tvID.setText(borrower.getID());
        tvPurpose.setText(borrower.getPurpose());
        tvAmount.setText(borrower.getAmount() + "");
        tvInterest.setText(borrower.getInterest() + "");
        tvTenure.setText(borrower.getTenure());
        tvTimeLeft.setText(borrower.getTimeLeft());
        tvAmountRemaining.setText(borrower.getAmountRemaining() + "");
    }

    private void initViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab_active_profile);
        tvID = (TextView) findViewById(R.id.tv_borrower_id);
        tvPurpose = (TextView) findViewById(R.id.tv_borrower_purpose);
        tvAmount = (TextView) findViewById(R.id.tv_borrower_amount);
        tvInterest = (TextView) findViewById(R.id.tv_borrower_interest);
        tvTenure = (TextView) findViewById(R.id.tv_borrower_tenure);
        tvTimeLeft = (TextView) findViewById(R.id.tv_borrower_time_left);
        tvAmountRemaining = (TextView) findViewById(R.id.tv_borrower_amount_remaining);
        btnClaim = (Button) findViewById(R.id.btn_borrower_claim);
    }

    private void fetchBorrower() {
        // TODO
//        borrower = Borrower.getBorrower();

        String url = getString(R.string.url);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();

        BorrowerApi borrowerApi = retrofit.create(BorrowerApi.class);

        borrowerApi.getBorrower(getSharedPreferences(LoginActivity.SHARED_PREFS_NAME, MODE_PRIVATE).getString("mobile", "123")).enqueue(new Callback<Borrower>() {
            @Override
            public void onResponse(Call<Borrower> call, Response<Borrower> response) {
                if (response.body() != null) {
                    borrower = response.body();
                    setViews();
                } else {
                    Toast.makeText(BorrowerActiveActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Borrower> call, Throwable t) {

            }
        });

    }

}
