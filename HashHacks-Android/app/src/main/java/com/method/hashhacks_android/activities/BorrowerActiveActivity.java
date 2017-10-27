package com.method.hashhacks_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.method.hashhacks_android.R;
import com.method.hashhacks_android.models.Borrower;

public class BorrowerActiveActivity extends AppCompatActivity {
    TextView tvID, tvPurpose, tvAmount, tvInterest, tvTenure, tvTimeLeft, tvAmountRemaining;
    Button btnClaim;
    Borrower borrower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_active);
        fetchBorrower();

        initViews();
        setViews();
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
        borrower = Borrower.getBorrower();
    }

}
