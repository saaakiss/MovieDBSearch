package myapp.com.moviedbsearch.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
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
import myapp.com.moviedbsearch.contracts.WishListContract;
import myapp.com.moviedbsearch.interfaces.RecyclerClickListener;
import myapp.com.moviedbsearch.models.SelectedItemDetails;
import myapp.com.moviedbsearch.presenters.WishListPresenter;
import myapp.com.moviedbsearch.utils.Utilities;

public class WishListActivity extends AppCompatActivity implements WishListContract.View, RecyclerClickListener {

    private WishListContract.Actions wishListPresenter;
    private SearchMainAdapter searchMainAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private static final String SELECTEDCACHEDITEM = "sel_cached_tem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("My WishList");

        recyclerView = findViewById(R.id.rv_movies_tvShows_Cached);
        wishListPresenter = new WishListPresenter(this);
        wishListPresenter.getAllMoviesTvShows(this);
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
                wishListPresenter.getMoviesTvShows(query, WishListActivity.this);
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
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMoviesTvShows(List<SelectedItemDetails> selectedItemsDetails) {

        searchMainAdapter = new SearchMainAdapter(getApplicationContext(), selectedItemsDetails, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchMainAdapter);

    }

    @Override
    public void showError() {

    }

    @Override
    public void onItemClicked(Object object) {

    }
}
