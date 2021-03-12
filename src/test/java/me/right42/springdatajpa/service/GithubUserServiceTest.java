package me.right42.springdatajpa.service;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class GithubUserServiceTest {

    @Test
    void retrofitTest() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubUserService githubUserService = retrofit.create(GithubUserService.class);
        Response<User> response = githubUserService.getUser("right42").execute();
        User body = response.body();

        assertThat(body).isNotNull();
        assertThat(body.getLogin()).isEqualTo("right42");

    }

    @Test
    void retrofitGeneratorServiceTest() throws IOException {

        GithubUserService githubUserService = GithubServiceGenerator.createService(GithubUserService.class);
        Response<User> response = githubUserService.getUser("right42").execute();
        User body = response.body();

        assertThat(body).isNotNull();
        assertThat(body.getLogin()).isEqualTo("right42");

    }

}