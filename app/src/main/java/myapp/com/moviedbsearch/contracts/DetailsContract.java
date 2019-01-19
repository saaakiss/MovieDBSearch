package myapp.com.moviedbsearch.contracts;

import android.content.Context;

import myapp.com.moviedbsearch.models.SearchMulti.Result;
import myapp.com.moviedbsearch.models.SelectedItemDetails;

public class DetailsContract {

    public interface View{
        void showResultDetails(SelectedItemDetails selectedItemDetails);
        void notifyAboutQueryResult(String message);
        void showError();

    }

    public interface Actions{
        void getResultDetails(SelectedItemDetails selectedItemDetails);
        void addItemToWishList(Context context);
    }
}
