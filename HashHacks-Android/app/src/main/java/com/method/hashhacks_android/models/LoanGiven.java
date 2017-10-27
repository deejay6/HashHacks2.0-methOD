package com.method.hashhacks_android.models;

import java.util.ArrayList;

/**
 * Created by piyush0 on 28/10/17.
 */

public class LoanGiven {

    private String ID;
    private String borrowerID;
    private Integer amount;
    private String when;
    private String tenure;
    private Integer interest;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    public LoanGiven(String ID, String borrowerID, Integer amount, String when, String tenure, Integer interest) {
        this.ID = ID;
        this.borrowerID = borrowerID;
        this.amount = amount;
        this.when = when;
        this.tenure = tenure;
        this.interest = interest;
    }

    public static ArrayList<LoanGiven> getLoanGiven() {
        ArrayList<LoanGiven> retVal = new ArrayList<>();

        LoanGiven loanGiven = new LoanGiven("1", "B_13", 150, "Long time", "Long", 5);
        retVal.add(loanGiven);


        return retVal;
    }
}
