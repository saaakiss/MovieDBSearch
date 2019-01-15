package myapp.com.moviedbsearch.services;

import java.util.List;

import myapp.com.moviedbsearch.models.MovieDetails.MovieDetails;
import myapp.com.moviedbsearch.models.SearchMulti.MultiSearchResponse;
import myapp.com.moviedbsearch.models.TvDetails.TvDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface FeedApi {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String API_KEY = "6b2e856adafcc7be98bdf0d8b076851c";


    @GET("search/multi")
    Call<MultiSearchResponse> getMoviesTvShows(@Query("api_key") String api_key, @Query("query") String query);

    @GET("search/multi")
    Call<MultiSearchResponse> getMoreMoviesTvShows(@Query("api_key") String api_key, @Query("query") String query, @Query("page") int page);

    @GET
    Call<MovieDetails> getMovieDetails(@Url String movieDetailsUrl);

    @GET
    Call<TvDetails> getTvDetails(@Url String tvDetailsUrl);
}
