package myapp.com.moviedbsearch.contracts;

import java.util.List;

import myapp.com.moviedbsearch.models.Result;

public class SearchMainContract {

    public interface View{
        void showMoviesTvShows(List<Result> filteredResults);
        void showError();
    }

    public interface Actions{
        void getMoviewTvShows(String query);
        void filterResults(List<Result> resultsResponse);
    }
}
