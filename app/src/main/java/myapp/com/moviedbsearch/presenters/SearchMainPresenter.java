package myapp.com.moviedbsearch.presenters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import myapp.com.moviedbsearch.contracts.SearchMainContract;
import myapp.com.moviedbsearch.models.MultiSearchResponse;
import myapp.com.moviedbsearch.models.Result;
import myapp.com.moviedbsearch.services.FeedApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMainPresenter implements SearchMainContract.Actions {

    private static final String TAG = "SearchMainPresenter";

    private SearchMainContract.View mView;

    public SearchMainPresenter(SearchMainContract.View mView){
        this.mView = mView;
    }

    @Override
    public void getMoviewTvShows(String query) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FeedApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeedApi feedApi = retrofit.create(FeedApi.class);

        Call<MultiSearchResponse> call = feedApi.getMoviesTvShows(FeedApi.API_KEY, query);

        call.enqueue(new Callback<MultiSearchResponse>() {
            @Override
            public void onResponse(Call<MultiSearchResponse> call, Response<MultiSearchResponse> response) {
                MultiSearchResponse multiSearchResults = response.body();
                filterResults(Arrays.asList(multiSearchResults.getResults()));
            }

            @Override
            public void onFailure(Call<MultiSearchResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void filterResults(List<Result> resultsResponse) {

        List<Result> filteredResults = new ArrayList<>();

        for (Result res : resultsResponse) {
            if(res.getMedia_type().equals("movie") || res.getMedia_type().equals("tv")){
                filteredResults.add(res);
            }
        }

        mView.showMoviesTvShows(filteredResults);

    }


}
