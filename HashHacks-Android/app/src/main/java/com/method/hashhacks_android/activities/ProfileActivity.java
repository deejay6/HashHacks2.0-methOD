package com.method.hashhacks_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.method.hashhacks_android.FontsOverride;
import com.method.hashhacks_android.R;
import com.method.hashhacks_android.api.BorrowerApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "jsd";
    TextView tvFiveRisk, tvFiveInterest, tvTenRisk, tvTenInterest, tvTwentyRisk, tvTwentyInterest, tvGreaterRisk, tvGreaterInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA, getWindow());

        findViews();
        getResults();
    }

    private void getResults() {
        String url = getResources().getString(R.string.url);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();
        BorrowerApi borrowerApi = retrofit.create(BorrowerApi.class);

        borrowerApi.getProfile(getSharedPreferences(LoginActivity.SHARED_PREFS_NAME, MODE_PRIVATE).getString("mobile", "123")).enqueue(new Callback<ArrayList<BorrowerApi.ProfileResult>>() {
            @Override
            public void onResponse(Call<ArrayList<BorrowerApi.ProfileResult>> call, Response<ArrayList<BorrowerApi.ProfileResult>> response) {
                if (response.body() != null) {
                    ArrayList<BorrowerApi.ProfileResult> results = response.body();
                    Log.d(TAG, "onResponse: " + "api success");
                    setViews(results);
                } else {
                    Toast.makeText(ProfileActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BorrowerApi.ProfileResult>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setViews(ArrayList<BorrowerApi.ProfileResult> results) {
        tvFiveRisk.setText(results.get(0).getRiskCategory()+ "%");
        tvFiveInterest.setText(results.get(0).getInterest());

        tvTenRisk.setText(results.get(1).getRiskCategory()+ "%");
        tvTenInterest.setText(results.get(1).getInterest());

        tvTwentyRisk.setText(results.get(2).getRiskCategory()+ "%");
        tvTwentyInterest.setText(results.get(2).getInterest());

        tvGreaterRisk.setText(results.get(3).getRiskCategory()+ "%");
        tvGreaterInterest.setText(results.get(3).getInterest());
    }

    private void findViews() {
        tvFiveRisk = (TextView) findViewById(R.id.tv_profile_five_risk);
        tvFiveInterest = (TextView) findViewById(R.id.tv_profile_five_interest);
        tvTenRisk = (TextView) findViewById(R.id.tv_profile_ten_risk);
        tvTenInterest = (TextView) findViewById(R.id.tv_profile_ten_interest);
        tvTwentyRisk = (TextView) findViewById(R.id.tv_profile_twenty_risk);
        tvTwentyInterest = (TextView) findViewById(R.id.tv_profile_twenty_interest);
        tvGreaterRisk = (TextView) findViewById(R.id.tv_profile_greater_risk);
        tvGreaterInterest = (TextView) findViewById(R.id.tv_profile_greater_interest);
    }
}
