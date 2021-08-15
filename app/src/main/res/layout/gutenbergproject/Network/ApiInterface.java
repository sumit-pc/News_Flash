package com.sumit.gutenbergproject.Network;


import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {


    @GET("books/")
    Call<JsonObject> getBooks(@QueryMap HashMap<String, String> defaultData);


}