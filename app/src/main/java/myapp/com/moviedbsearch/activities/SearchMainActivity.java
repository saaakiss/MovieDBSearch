package myapp.com.moviedbsearch.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import myapp.com.moviedbsearch.R;
import myapp.com.moviedbsearch.adapters.SearchMainAdapter;
import myapp.com.moviedbsearch.contracts.SearchMainContract;
import myapp.com.moviedbsearch.models.Result;
import myapp.com.moviedbsearch.presenters.SearchMainPresenter;

public class SearchMainActivity extends AppCompatActivity implements SearchMainContract.View {

    private SearchMainContract.Actions searchMainPresenter;
    private SearchMainAdapter searchMainAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_movies_tvShows);


        searchMainPresenter = new SearchMainPresenter(this);

        searchMainPresenter.getMoviewTvShows("star");
    }

    @Override
    public void showMoviesTvShows(List<Result> filteredResults) {

        searchMainAdapter = new SearchMainAdapter(this, filteredResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(searchMainAdapter);


    }

    @Override
    public void showError() {

    }
}
