package myapp.com.moviedbsearch.presenters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import myapp.com.moviedbsearch.contracts.SearchMainContract;
import myapp.com.moviedbsearch.models.SearchMulti.MultiSearchResponse;
import myapp.com.moviedbsearch.models.SearchMulti.Result;
import myapp.com.moviedbsearch.models.SelectedItemDetails;
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
    public void getMoviesTvShows(String query) {
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
    public void getMoreMoviesTvShows(String query, int page) {
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
        List<SelectedItemDetails> selectedItemsDetails = convertToSelectedItemDetailsLIst(resultsResponse);
        mView.showMoviesTvShows(selectedItemsDetails, totalPages);
    }

    @Override
    public void filterMoreResults(List<Result> resultsResponse) {
        List<SelectedItemDetails> selectedItemsDetails = convertToSelectedItemDetailsLIst(resultsResponse);
        mView.showMoreMoviesTvShows(selectedItemsDetails);
    }

    private List<SelectedItemDetails> convertToSelectedItemDetailsLIst(List<Result> resultsResponse){
        List<SelectedItemDetails> filteredItemsDetails = new ArrayList<>();
        for (Result res : resultsResponse) {
            if(res.getMedia_type().equals("movie") || res.getMedia_type().equals("tv")){
                SelectedItemDetails item = new SelectedItemDetails();
                if(res.getMedia_type().equals("movie")){
                    item.setRelease_date(res.getRelease_date());
                    item.setTitle(res.getTitle());
                }
                else {
                    item.setRelease_date(res.getFirst_air_date());
                    item.setTitle(res.getName());
                }
                item.setRatings(res.getVote_average());
                item.setItemType(res.getMedia_type());
                item.setImage(res.getPoster_path());
                item.setId(res.getId());

                filteredItemsDetails.add(item);
            }
        }

        return filteredItemsDetails;
    }


}
