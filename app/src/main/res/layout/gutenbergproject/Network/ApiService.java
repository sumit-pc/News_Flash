package com.sumit.gutenbergproject.Network;



public class ApiService {

    private static ApiInterface apiInterface;

    public static ApiInterface getApiInstance() {
        if (apiInterface == null) {
            apiInterface = ApiClient.getCustomer().create(ApiInterface.class);
        }
        return apiInterface;
    }
}
