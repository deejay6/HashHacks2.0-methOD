package com.method.hashhacks_android.models;

import java.util.ArrayList;

/**
 * Created by piyush0 on 28/10/17.
 */

public class LoanNeeded {
    private String ID;
    private String personID;
    private String purpose;
    private Integer amount;
    private Integer interest;
    private String tenure;
    private String riskCategory;
    private String timeLeft;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(String riskCategory) {
        this.riskCategory = riskCategory;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }


    public LoanNeeded(String ID, String personID, String purpose, Integer amount, Integer interest, String tenure, String riskCategory, String timeLeft) {
        this.ID = ID;
        this.personID = personID;
        this.purpose = purpose;
        this.amount = amount;
        this.interest = interest;
        this.tenure = tenure;
        this.riskCategory = riskCategory;
        this.timeLeft = timeLeft;
    }

    public static ArrayList<LoanNeeded> getLoanNeeded() {
        ArrayList<LoanNeeded> retVal = new ArrayList<>();

        LoanNeeded loanNeeded = new LoanNeeded("1", "xyz", "Shaadi", 120, 4, "15 years", "A", "10 months");

        retVal.add(loanNeeded);

        return retVal;

    }
}
