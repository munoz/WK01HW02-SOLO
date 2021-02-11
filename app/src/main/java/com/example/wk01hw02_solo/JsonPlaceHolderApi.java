package com.example.wk01hw02_solo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface JsonPlaceHolderApi {

    //@GET("url after baseURL")

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") int userId);

}
