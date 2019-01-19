package myapp.com.moviedbsearch.presenters;

import java.util.Arrays;
import java.util.List;

import myapp.com.moviedbsearch.contracts.DetailsContract;
import myapp.com.moviedbsearch.models.Enums.ItemType;
import myapp.com.moviedbsearch.models.MovieDetails.MovieDetails;
import myapp.com.moviedbsearch.models.MovieDetails.Results;
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
            String movieDetailsUrl = "movie/" + result.getId() + "?api_key=" + FeedApi.API_KEY + "&append_to_response=videos";
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
            String tvDetailsUrl = "tv/" + result.getId() + "?api_key=" + FeedApi.API_KEY + "&append_to_response=videos";
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

        if(movieDetails.getPoster_path() != null && !movieDetails.getPoster_path().isEmpty()){
            selectedItemDetails.setImage("https://image.tmdb.org/t/p/original" + movieDetails.getPoster_path());
        }
        else {
            selectedItemDetails.setImage(null);
        }
        selectedItemDetails.setTitle(movieDetails.getTitle());
        String summary = movieDetails.getOverview() != null && !movieDetails.getOverview().trim().isEmpty() ? movieDetails.getOverview() : "N/A";
        selectedItemDetails.setSummary(summary);
        String genre = movieDetails.getGenres() != null && movieDetails.getGenres().length > 0 ? movieDetails.getGenres()[0].getName() : "N/A";
        selectedItemDetails.setGenre(genre);
        selectedItemDetails.setId(movieDetails.getId());
        selectedItemDetails.setItemType(ItemType.MOVIE.toString());

        if(movieDetails.getVideos() != null && movieDetails.getVideos().getResults() != null){
            for (Results res : movieDetails.getVideos().getResults())
            {
                if(res.getSite().equals("YouTube") && res.getType().equals("Trailer")){
                    selectedItemDetails.setTrailerUrl("https://www.youtube.com/embed/" + res.getKey());
                    break;
                }
            }

        }

        mView.showResultDetails(selectedItemDetails);
    }

    private void parseTvDetailsResponse(TvDetails tvDetails){

        SelectedItemDetails selectedItemDetails = new SelectedItemDetails();

        if(tvDetails.getPoster_path() != null && !tvDetails.getPoster_path().isEmpty()){
            selectedItemDetails.setImage("https://image.tmdb.org/t/p/original" + tvDetails.getPoster_path());
        }
        else {
            selectedItemDetails.setImage(null);
        }
        selectedItemDetails.setTitle(tvDetails.getName());
        String summary = tvDetails.getOverview() != null && !tvDetails.getOverview().trim().isEmpty() ? tvDetails.getOverview() : "N/A";
        selectedItemDetails.setSummary(summary);
        String genre = tvDetails.getGenres() != null && tvDetails.getGenres().length > 0 ? tvDetails.getGenres()[0].getName() : "N/A";
        selectedItemDetails.setGenre(genre);
        selectedItemDetails.setId(tvDetails.getId());
        selectedItemDetails.setItemType(ItemType.TV.toString());

        if(tvDetails.getVideos() != null && tvDetails.getVideos().getResults() != null){
            for (Results res : tvDetails.getVideos().getResults())
            {
                if(res.getSite().equals("YouTube") && res.getType().equals("Trailer")){
                    selectedItemDetails.setTrailerUrl("https://www.youtube.com/embed/" + res.getKey());
                    break;
                }
            }

        }

        mView.showResultDetails(selectedItemDetails);
    }
}
