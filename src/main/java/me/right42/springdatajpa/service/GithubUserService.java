package me.right42.springdatajpa.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubUserService {

    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);
}
