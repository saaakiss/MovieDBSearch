package myapp.com.moviedbsearch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;

import java.util.List;

import myapp.com.moviedbsearch.R;
import myapp.com.moviedbsearch.adapters.SearchMainAdapter;
import myapp.com.moviedbsearch.contracts.SearchMainContract;
import myapp.com.moviedbsearch.interfaces.RecyclerClickListener;
import myapp.com.moviedbsearch.models.SearchMulti.Result;
import myapp.com.moviedbsearch.presenters.SearchMainPresenter;
import myapp.com.moviedbsearch.utils.PaginationScrollListener;

public class SearchMainActivity extends AppCompatActivity implements SearchMainContract.View, RecyclerClickListener {

    private SearchMainContract.Actions searchMainPresenter;
    private SearchMainAdapter searchMainAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private int currentPage = PAGE_START;
    private String mQuery;

    private static final String RESULT = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_movies_tvShows);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.GONE);


        searchMainPresenter = new SearchMainPresenter(this);
    }

    private void initVariables(){
        isLastPage = false;
        isLoading = false;
        TOTAL_PAGES = 1;
        currentPage = PAGE_START;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                initVariables();
                mQuery  = query;
                progressBar.setVisibility(View.VISIBLE);
                searchMainPresenter.getMoviewTvShows(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_wishList:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void showMoviesTvShows(List<Result> filteredResults, int totalPages) {

        TOTAL_PAGES = totalPages;

        searchMainAdapter = new SearchMainAdapter(this, filteredResults, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchMainAdapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();

    }

    private void loadFirstPage(){
        progressBar.setVisibility(View.GONE);

        if (currentPage < TOTAL_PAGES) searchMainAdapter.addLoadingFooter();
        else isLastPage = true;
    }

    private void loadNextPage(){
        searchMainPresenter.getMoreMoviewTvSHows(mQuery, currentPage);


    }

    @Override
    public void showMoreMoviesTvShows(List<Result> moreFilteredResults) {

        searchMainAdapter.removeLoadingFooter();
        isLoading = false;


        searchMainAdapter.addAll(moreFilteredResults);


        if (currentPage != TOTAL_PAGES) {
            searchMainAdapter.addLoadingFooter();
        }
        else{
            isLastPage = true;
        }
    }

    @Override
    public void onItemClicked(Object result)
    {
        Intent resultIntent = new Intent(this, DetailsActivity.class);
        resultIntent.putExtra(RESULT, (Result)result);
        startActivity(resultIntent);
    }

    @Override
    public void showError() {

    }
}
