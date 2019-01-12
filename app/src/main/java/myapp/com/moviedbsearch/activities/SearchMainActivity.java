package myapp.com.moviedbsearch.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import myapp.com.moviedbsearch.R;
import myapp.com.moviedbsearch.contracts.SearchMainContract;
import myapp.com.moviedbsearch.presenters.SearchMainPresenter;

public class SearchMainActivity extends AppCompatActivity implements SearchMainContract.View {

    private SearchMainContract.Actions searchMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        searchMainPresenter = new SearchMainPresenter(this);

        searchMainPresenter.getMoviewTvShows("star");
    }

    @Override
    public void showMoviesTvShows() {

    }

    @Override
    public void showError() {

    }
}
