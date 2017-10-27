package com.method.hashhacks_android.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by piyush0 on 28/10/17.
 */

public interface SignupApi {
    @POST("signup")
    Call<String> createUser(@Body User user);

    public class User {
        String name, aadhar, address, email, dob, password;
        Boolean isMale, isBorrower;

        public User(String name, String aadhar, String address, String email, String dob, String password, Boolean isMale, Boolean isBorrower) {
            this.name = name;
            this.aadhar = aadhar;
            this.address = address;
            this.email = email;
            this.dob = dob;
            this.password = password;
            this.isMale = isMale;
            this.isBorrower = isBorrower;
        }


    }
}
