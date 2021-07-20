package com.app.ashvaweartechtask;

import com.app.ashvaweartechtask.Model.DetailsExample;
import com.app.ashvaweartechtask.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://api.themoviedb.org/3/movie/";
    @GET("now_playing?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=undefined")
    Call<Example> getMovies();

    @GET("284052?api_key=55957fcf3ba81b137f8fc01ac5a31fb5")
    Call<DetailsExample> getMoviesDetails();


}
