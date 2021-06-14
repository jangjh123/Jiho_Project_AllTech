package com.example.jiho_project_alltech;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiService {

    @GET("posts/")
    Call<List<Post>> getData();
}