package myapp.com.moviedbsearch.presenters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import myapp.com.moviedbsearch.contracts.SearchMainContract;
import myapp.com.moviedbsearch.models.SearchMulti.MultiSearchResponse;
import myapp.com.moviedbsearch.models.SearchMulti.Result;
import myapp.com.moviedbsearch.services.FeedApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMainPresenter implements SearchMainContract.Actions {

    private SearchMainContract.View mView;
    private Retrofit retrofit;

    public SearchMainPresenter(SearchMainContract.View mView){

        this.mView = mView;
        retrofit = new Retrofit.Builder()
                .baseUrl(FeedApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getMoviewTvShows(String query) {

        FeedApi feedApi = retrofit.create(FeedApi.class);

        Call<MultiSearchResponse> call = feedApi.getMoviesTvShows(FeedApi.API_KEY, query);

        call.enqueue(new Callback<MultiSearchResponse>() {
            @Override
            public void onResponse(Call<MultiSearchResponse> call, Response<MultiSearchResponse> response) {
                MultiSearchResponse multiSearchResults = response.body();
                int totalPages = Integer.parseInt(multiSearchResults.getTotal_pages());
                filterResults(Arrays.asList(multiSearchResults.getResults()), totalPages);
            }

            @Override
            public void onFailure(Call<MultiSearchResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void getMoreMoviewTvSHows(String query, int page) {

        FeedApi feedApi = retrofit.create(FeedApi.class);

        Call<MultiSearchResponse> call = feedApi.getMoreMoviesTvShows(FeedApi.API_KEY, query, page);

        call.enqueue(new Callback<MultiSearchResponse>() {
            @Override
            public void onResponse(Call<MultiSearchResponse> call, Response<MultiSearchResponse> response) {
                MultiSearchResponse multiSearchResults = response.body();
                filterMoreResults(Arrays.asList(multiSearchResults.getResults()));
            }

            @Override
            public void onFailure(Call<MultiSearchResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void filterResults(List<Result> resultsResponse, int totalPages) {

        List<Result> filteredResults = new ArrayList<>();

        for (Result res : resultsResponse) {
            if(res.getMedia_type().equals("movie") || res.getMedia_type().equals("tv")){
                filteredResults.add(res);
            }
        }

        mView.showMoviesTvShows(filteredResults, totalPages);

    }

    @Override
    public void filterMoreResults(List<Result> resultsResponse) {
        List<Result> filteredResults = new ArrayList<>();

        for (Result res : resultsResponse) {
            if(res.getMedia_type().equals("movie") || res.getMedia_type().equals("tv")){
                filteredResults.add(res);
            }
        }

        mView.showMoreMoviesTvShows(filteredResults);
    }


}
