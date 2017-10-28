package com.method.hashhacks_android.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by piyush0 on 28/10/17.
 */

public interface SignupApi {
    @POST("signup")
    Call<Result> createUser(@Body User user);

    public class User {
        String name, aadhar, address, mobile, dob, password, facebook, twitter;
        Boolean isMale, isBorrower;

        public User(String name, String aadhar, String address, String mobile, String dob, String password, Boolean isMale, Boolean isBorrower, String facebook, String twitter) {
            this.name = name;
            this.facebook = facebook;
            this.twitter = twitter;
            this.aadhar = aadhar;
            this.address = address;
            this.mobile = mobile;
            this.dob = dob;
            this.password = password;
            this.isMale = isMale;
            this.isBorrower = isBorrower;
        }


    }

    public class Result{
        String mobile;

        public String getMobile() {
            return mobile;
        }

        public Result(String mobile) {
            this.mobile = mobile;
        }
    }
}
