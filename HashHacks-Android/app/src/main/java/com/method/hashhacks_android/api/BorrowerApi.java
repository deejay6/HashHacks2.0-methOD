package com.method.hashhacks_android.api;

import com.method.hashhacks_android.models.Borrower;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by piyush0 on 28/10/17.
 */

public interface BorrowerApi {
    @GET("isactive")
    Call<Borrower> getBorrower(@Query("mobile") String mobile);

    @POST("createloan")
    Call<PuneetResult> createLoan(@Body LoanDetail loanDetail, @Query("mobile") String mobile);

    public class LoanDetail {
        private String purpose, tenure;
        private Integer amount;

        public LoanDetail(String purpose, String tenure, Integer amount) {
            this.purpose = purpose;
            this.tenure = tenure;
            this.amount = amount;
        }
    }

    public class PuneetResult{
        String loanID;

        public String getLoanID() {
            return loanID;
        }
    }
}
