package myapp.com.moviedbsearch.activities;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import myapp.com.moviedbsearch.R;
import myapp.com.moviedbsearch.contracts.DetailsContract;
import myapp.com.moviedbsearch.models.SearchMulti.Result;
import myapp.com.moviedbsearch.models.SelectedItemDetails;
import myapp.com.moviedbsearch.presenters.DetailsPresenter;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private static final String RESULT = "result";

    private DetailsContract.Actions mPresenter;
    private ProgressBar progressBar;
    private NestedScrollView nestedScrollView;
    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewGenre;
    private TextView textViewSummary;


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

        Intent intent = getIntent();
        Result result = (Result) intent.getSerializableExtra(RESULT);



        mPresenter = new DetailsPresenter(this);

        mPresenter.getResultDetails(result);

    }

    @Override
    public void showResultDetails(SelectedItemDetails selectedItemDetails) {

        Glide.with(this).load(selectedItemDetails.getImage()).into(imageView);
        textViewTitle.setText(selectedItemDetails.getTitle());
        textViewGenre.setText(selectedItemDetails.getGenre());
        textViewSummary.setText(selectedItemDetails.getSummary());

        progressBar.setVisibility(View.GONE);
        nestedScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {

    }
}
