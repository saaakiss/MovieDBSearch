package myapp.com.moviedbsearch.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import myapp.com.moviedbsearch.R;
import myapp.com.moviedbsearch.models.Result;

public class SearchMainAdapter extends RecyclerView.Adapter<SearchMainAdapter.SearchMainHolder> {

    private Context context;
    private List<Result> results;

    public SearchMainAdapter(Context context, List<Result> results){
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public SearchMainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_row_search_main, viewGroup, false);
        return new SearchMainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMainHolder searchMainHolder, int i) {

        String releaseDate = results.get(i).getFirst_air_date() != null ? results.get(i).getFirst_air_date() : "N/A";

        searchMainHolder.txtViewTitle.setText(results.get(i).getName() != null ? results.get(i).getName() : "N/A");
        searchMainHolder.txtViewReleaseDate.setText("Relase Date: " + releaseDate);
        searchMainHolder.txtViewRatings.setText("Rating: " + results.get(i).getVote_average());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + results.get(i).getPoster_path().trim()).into(searchMainHolder.ivLogo);


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class SearchMainHolder extends RecyclerView.ViewHolder{

        TextView txtViewTitle;
        TextView txtViewReleaseDate;
        TextView txtViewRatings;
        ImageView ivLogo;



        public SearchMainHolder(@NonNull View itemView) {
            super(itemView);

            txtViewTitle = itemView.findViewById(R.id.tv_title);
            txtViewReleaseDate = itemView.findViewById(R.id.tv_release_date);
            txtViewRatings = itemView.findViewById(R.id.tv_ratings);
            ivLogo = itemView.findViewById(R.id.logo);

        }
    }

}
