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
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import myapp.com.moviedbsearch.R;
import myapp.com.moviedbsearch.interfaces.RecyclerClickListener;
import myapp.com.moviedbsearch.models.SearchMulti.Result;
import myapp.com.moviedbsearch.utils.Utilities;

public class SearchMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private Context context;
    private List<Result> results;
    private RecyclerClickListener recyclerClickListener;

    private boolean isLoadingAdded = false;



    public SearchMainAdapter(Context context, List<Result> results, RecyclerClickListener recyclerClickListener){
        this.context = context;
        this.results = results;
        this.recyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_row_search_main, parent, false);
        viewHolder = new SearchMainHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                SearchMainHolder searchMainHolder = (SearchMainHolder) holder;

                String releaseDate;
                if(results.get(position).getMedia_type().equals("movie")){
                    releaseDate = results.get(position).getRelease_date() != null ? Utilities.formatDate(results.get(position).getRelease_date()) : "N/A";
                }else{
                    releaseDate = results.get(position).getFirst_air_date() != null ? Utilities.formatDate(results.get(position).getFirst_air_date()) : "N/A";
                }

                String title;
                if(results.get(position).getMedia_type().equals("movie")){
                    title = results.get(position).getTitle() != null ? results.get(position).getTitle() : "N/A";
                }else{
                    title = results.get(position).getName() != null ? results.get(position).getName() : "N/A";
                }

                searchMainHolder.txtViewTitle.setText(title);

                searchMainHolder.txtViewReleaseDate.setText("Relase Date: " + releaseDate);
                searchMainHolder.txtViewRatings.setText("Rating: " + results.get(position).getVote_average());

                searchMainHolder.txtViewMediaType.setText(results.get(position).getMedia_type());

                if(results.get(position).getPoster_path() != null && !results.get(position).getPoster_path().trim().isEmpty()){
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.error(R.drawable.ic_placeholder);
                    Glide.with(context).load("https://image.tmdb.org/t/p/original" + results.get(position).getPoster_path()).apply(requestOptions).into(searchMainHolder.ivLogo);
                }
                else {
                    Glide.with(context).load(R.drawable.ic_placeholder).into(searchMainHolder.ivLogo);
                }



                break;
            case LOADING:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == results.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public class SearchMainHolder extends RecyclerView.ViewHolder{

        TextView txtViewTitle;
        TextView txtViewReleaseDate;
        TextView txtViewRatings;
        ImageView ivLogo;
        TextView txtViewMediaType;



        public SearchMainHolder(@NonNull View itemView) {
            super(itemView);

            txtViewTitle = itemView.findViewById(R.id.tv_title);
            txtViewReleaseDate = itemView.findViewById(R.id.tv_release_date);
            txtViewRatings = itemView.findViewById(R.id.tv_ratings);
            ivLogo = itemView.findViewById(R.id.logo);
            txtViewMediaType = itemView.findViewById(R.id.tv_media_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerClickListener.onItemClicked(results.get(getLayoutPosition()));
                }
            });

        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

    //////////////////////////
    //Helpers

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Result());
    }

    public void add(Result mc) {
        results.add(mc);
        notifyItemInserted(results.size() - 1);
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = results.size() - 1;
        Result item = getItem(position);

        if (item != null) {
            results.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Result getItem(int position) {
        return results.get(position);
    }

    public void addAll(List<Result> mcList) {
        for (Result mc : mcList) {
            add(mc);
        }
    }

}
