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

public interface LoginApi {

    @POST("login")
    Call<Boolean> checkIfBorrower(@Body LoginDetails loginDetails, @Query("mobile") String mobile);


    public class LoginDetails {
        String mobile, password;

        public LoginDetails(String mobile, String password) {
            this.mobile = mobile;
            this.password = password;
        }
    }
}
