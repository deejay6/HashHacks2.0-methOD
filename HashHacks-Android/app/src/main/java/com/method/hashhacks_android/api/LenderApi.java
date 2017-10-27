package com.method.hashhacks_android.api;

import com.method.hashhacks_android.models.LoanGiven;
import com.method.hashhacks_android.models.LoanNeeded;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by piyush0 on 28/10/17.
 */

public interface LenderApi {

    @GET("loandisplay")
    Call<ArrayList<LoanNeeded>> getLoanDisplay(@Query("mobile") String mobile);


    @GET("loangiven")
    Call<ArrayList<LoanGiven>> getLoanGiven(@Query("mobile") String mobile);

}
