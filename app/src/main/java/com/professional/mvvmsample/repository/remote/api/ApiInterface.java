package com.professional.mvvmsample.repository.remote.api;

import com.professional.mvvmsample.repository.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/testdata.json")
    Call<DataModel> fetchData();
}
