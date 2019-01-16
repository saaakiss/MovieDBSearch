package myapp.com.moviedbsearch.presenters;

import java.util.Arrays;

import myapp.com.moviedbsearch.contracts.DetailsContract;
import myapp.com.moviedbsearch.models.Enums.ItemType;
import myapp.com.moviedbsearch.models.MovieDetails.MovieDetails;
import myapp.com.moviedbsearch.models.SearchMulti.MultiSearchResponse;
import myapp.com.moviedbsearch.models.SearchMulti.Result;
import myapp.com.moviedbsearch.models.SelectedItemDetails;
import myapp.com.moviedbsearch.models.TvDetails.TvDetails;
import myapp.com.moviedbsearch.services.FeedApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsPresenter implements DetailsContract.Actions {

    private DetailsContract.View mView;
    private Retrofit retrofit;

    public DetailsPresenter(DetailsContract.View mView){

        this.mView = mView;

        retrofit = new Retrofit.Builder()
                .baseUrl(FeedApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultDetails(Result result) {

        FeedApi feedApi = retrofit.create(FeedApi.class);

        if(result.getMedia_type().equals("movie")){
            String movieDetailsUrl = "movie/" + result.getId() + "?" + "api_key=" + FeedApi.API_KEY;
            Call<MovieDetails> call = feedApi.getMovieDetails(movieDetailsUrl);

            call.enqueue(new Callback<MovieDetails>() {
                @Override
                public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                    MovieDetails movieDetails = response.body();

                    parseMoviesDetailsResponse(movieDetails);
                }

                @Override
                public void onFailure(Call<MovieDetails> call, Throwable t) {

                }
            });

        }
        else {
            String tvDetailsUrl = "tv/" + result.getId() + "?" + "api_key=" + FeedApi.API_KEY;
            Call<TvDetails> call = feedApi.getTvDetails(tvDetailsUrl);

            call.enqueue(new Callback<TvDetails>() {
                @Override
                public void onResponse(Call<TvDetails> call, Response<TvDetails> response) {
                    TvDetails tvDetails = response.body();

                    parseTvDetailsResponse(tvDetails);
                }

                @Override
                public void onFailure(Call<TvDetails> call, Throwable t) {

                }
            });
        }

    }


    private void parseMoviesDetailsResponse(MovieDetails movieDetails){

        SelectedItemDetails selectedItemDetails = new SelectedItemDetails();

        selectedItemDetails.setImage("https://image.tmdb.org/t/p/original" + movieDetails.getPoster_path());
        selectedItemDetails.setTitle(movieDetails.getTitle());
        String summary = movieDetails.getOverview() != null && !movieDetails.getOverview().trim().isEmpty() ? movieDetails.getOverview() : "N/A";
        selectedItemDetails.setSummary(summary);
        String genre = movieDetails.getGenres() != null && movieDetails.getGenres().length > 0 ? movieDetails.getGenres()[0].getName() : "N/A";
        selectedItemDetails.setGenre(genre);
        selectedItemDetails.setId(movieDetails.getId());
        selectedItemDetails.setItemType(ItemType.MOVIE.toString());

        mView.showResultDetails(selectedItemDetails);
    }

    private void parseTvDetailsResponse(TvDetails tvDetails){

        SelectedItemDetails selectedItemDetails = new SelectedItemDetails();

        selectedItemDetails.setImage("https://image.tmdb.org/t/p/original" + tvDetails.getPoster_path());
        selectedItemDetails.setTitle(tvDetails.getName());
        String summary = tvDetails.getOverview() != null && !tvDetails.getOverview().trim().isEmpty() ? tvDetails.getOverview() : "N/A";
        selectedItemDetails.setSummary(summary);
        String genre = tvDetails.getGenres() != null && tvDetails.getGenres().length > 0 ? tvDetails.getGenres()[0].getName() : "N/A";
        selectedItemDetails.setGenre(genre);
        selectedItemDetails.setId(tvDetails.getId());
        selectedItemDetails.setItemType(ItemType.TV.toString());

        mView.showResultDetails(selectedItemDetails);
    }
}
