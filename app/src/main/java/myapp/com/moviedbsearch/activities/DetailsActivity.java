package myapp.com.moviedbsearch.activities;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import myapp.com.moviedbsearch.R;
import myapp.com.moviedbsearch.contracts.DetailsContract;
import myapp.com.moviedbsearch.models.SelectedItemDetails;
import myapp.com.moviedbsearch.presenters.DetailsPresenter;
import myapp.com.moviedbsearch.utils.Utilities;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private static final String SELECTEDITEM = "sel_tem";
    private DetailsContract.Actions mPresenter;
    private ProgressBar progressBar;
    private NestedScrollView nestedScrollView;
    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewGenre;
    private TextView textViewSummary;
    private WebView videoWeb;
    private TextView textViewVideoWebTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nestedScrollView = findViewById(R.id.detailsLayout);
        nestedScrollView.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progress_bar);
        imageView = findViewById(R.id.imageview_item);
        textViewTitle = findViewById(R.id.movie_title);
        textViewGenre = findViewById(R.id.movie_genre);
        textViewSummary = findViewById(R.id.tv_summary);
        videoWeb = findViewById(R.id.videoWebView);
        textViewVideoWebTitle = findViewById(R.id.videoWebViewTitle);
        videoWeb.setVisibility(View.GONE);

        Intent intent = getIntent();
        SelectedItemDetails result = (SelectedItemDetails) intent.getSerializableExtra(SELECTEDITEM);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle(result.getItemType() + " details");

        mPresenter = new DetailsPresenter(this);

        if (Utilities.isNetworkAvailable(this)){
            mPresenter.getResultDetails(result);
        }
        else {
            Toast.makeText(this, getString(R.string.no_network_access), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.fav_ic:
                mPresenter.addItemToWishList(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showResultDetails(SelectedItemDetails selectedItemDetails) {
        if(selectedItemDetails.getImage() != null){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(R.drawable.ic_placeholder);
            Glide.with(getApplicationContext()).load(selectedItemDetails.getImage()).apply(requestOptions).into(imageView);
        }
        else {
            Glide.with(getApplicationContext()).load(R.drawable.ic_placeholder).into(imageView);
        }

        textViewTitle.setText(selectedItemDetails.getTitle());
        textViewGenre.setText("Genre: " + selectedItemDetails.getGenre());
        textViewSummary.setText(selectedItemDetails.getSummary());

        if(selectedItemDetails.getTrailerUrl() != null){
            textViewVideoWebTitle.setText("Watch Trailer");
            videoWeb.setVisibility(View.VISIBLE);

            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {
            } );
            videoWeb.loadData("<iframe width=\"100%\" height=\"100%\" src=\"" + selectedItemDetails.getTrailerUrl() + "\" frameborder=\"0\" allowfullscreen></iframe>", "text/html" , "utf-8" );
        }
        else {
            textViewVideoWebTitle.setText("Trailer Unavailable");
        }

        progressBar.setVisibility(View.GONE);
        nestedScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void notifyAboutQueryResult(String message) {
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, R.string.error_occurred, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
