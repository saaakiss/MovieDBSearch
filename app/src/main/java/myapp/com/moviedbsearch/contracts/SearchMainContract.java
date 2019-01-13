package myapp.com.moviedbsearch.contracts;

import java.util.List;

import myapp.com.moviedbsearch.models.Result;

public class SearchMainContract {

    public interface View{
        void showMoviesTvShows(List<Result> filteredResults, int totalPages);
        void showMoreMoviesTvShows(List<Result> moreFilteredResults);
        void showError();
    }

    public interface Actions{
        void getMoviewTvShows(String query);
        void getMoreMoviewTvSHows(String query, int page);
        void filterResults(List<Result> resultsResponse, int totalPages);
        void filterMoreResults(List<Result> resultsResponse);
    }
}
