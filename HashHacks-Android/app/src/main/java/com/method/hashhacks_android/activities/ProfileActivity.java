package com.method.hashhacks_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.method.hashhacks_android.FontsOverride;
import com.method.hashhacks_android.R;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvAddress, tvAadhar, tvDOB, tvFacebook, tvTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA,getWindow());

        findViews();
    }

    private void findViews() {
        tvName = (TextView) findViewById(R.id.tv_profile_name);
        tvAddress = (TextView) findViewById(R.id.tv_profile_address);
        tvAadhar = (TextView) findViewById(R.id.tv_profile_aadhar);
        tvDOB = (TextView) findViewById(R.id.tv_profile_dob);
        tvFacebook = (TextView) findViewById(R.id.tv_profile_facebook);
        tvTwitter = (TextView) findViewById(R.id.tv_profile_twitter);
    }
}
