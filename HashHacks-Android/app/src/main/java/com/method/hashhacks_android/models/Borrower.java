package com.method.hashhacks_android.models;


/**
 * Created by piyush0 on 28/10/17.
 */

public class Borrower {
    private String ID, purpose, tenure, timeLeft;
    private Integer amountRemaining, interest, amount;

    public static Borrower getBorrower() {
        Borrower retVal = new Borrower("1", "Shaadi", "15 months", "10 days", 150, 8, 1000);
        return retVal;
    }

    public Borrower(String ID, String purpose, String tenure, String timeLeft, Integer amountRemaining, Integer interest, Integer amount) {
        this.ID = ID;
        this.purpose = purpose;
        this.tenure = tenure;
        this.timeLeft = timeLeft;
        this.amountRemaining = amountRemaining;
        this.interest = interest;
        this.amount = amount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Integer getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(Integer amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
