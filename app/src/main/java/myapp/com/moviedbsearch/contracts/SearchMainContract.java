package myapp.com.moviedbsearch.contracts;

import java.util.List;

import myapp.com.moviedbsearch.models.SearchMulti.Result;
import myapp.com.moviedbsearch.models.SelectedItemDetails;

public class SearchMainContract {

    public interface View{
        void showMoviesTvShows(List<SelectedItemDetails> selectedItemsDetails, int totalPages);
        void showMoreMoviesTvShows(List<SelectedItemDetails> selectedItemsDetails);
        void showError();
    }

    public interface Actions{
        void getMoviesTvShows(String query);
        void getMoreMoviesTvShows(String query, int page);
        void filterResults(List<Result> resultsResponse, int totalPages);
        void filterMoreResults(List<Result> resultsResponse);
    }
}
