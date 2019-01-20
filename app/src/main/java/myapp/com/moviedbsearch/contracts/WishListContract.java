package myapp.com.moviedbsearch.contracts;

import android.content.Context;

import java.util.List;

import myapp.com.moviedbsearch.models.SelectedItemDetails;

public class WishListContract {

    public interface View{
        void showMoviesTvShows(List<SelectedItemDetails> selectedItemsDetails);
        void showError();
    }

    public interface Actions{
        void getAllMoviesTvShows(Context context);
        void getMoviesTvShows(String query, Context context);

    }
}
