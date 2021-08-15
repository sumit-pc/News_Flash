package com.sumit.newsapp.Network;


import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {


    @GET("everything/")
    Call<JsonObject> getBooks(@QueryMap HashMap<String, String> defaultData);


}