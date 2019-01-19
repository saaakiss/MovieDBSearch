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
import myapp.com.moviedbsearch.models.SelectedItemDetails;
import myapp.com.moviedbsearch.utils.Utilities;

public class SearchMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private Context context;
    private List<SelectedItemDetails> selectedItemsDetails;
    private RecyclerClickListener recyclerClickListener;
    private boolean isLoadingAdded = false;

    public SearchMainAdapter(Context context, List<SelectedItemDetails> selectedItemsDetails, RecyclerClickListener recyclerClickListener){
        this.context = context;
        this.selectedItemsDetails = selectedItemsDetails;
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
                releaseDate = selectedItemsDetails.get(position).getRelease_date() != null ? Utilities.formatDate(selectedItemsDetails.get(position).getRelease_date()) : "N/A";
                searchMainHolder.txtViewReleaseDate.setText("Release Date: " + releaseDate);

                String title;
                title = selectedItemsDetails.get(position).getTitle() != null ? selectedItemsDetails.get(position).getTitle() : "N/A";
                searchMainHolder.txtViewTitle.setText(title);

                searchMainHolder.txtViewRatings.setText("Rating: " + selectedItemsDetails.get(position).getRatings());
                searchMainHolder.txtViewMediaType.setText(selectedItemsDetails.get(position).getItemType());

                if(selectedItemsDetails.get(position).getImage() != null && !selectedItemsDetails.get(position).getImage().trim().isEmpty()){
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.error(R.drawable.ic_placeholder);
                    Glide.with(context).load("https://image.tmdb.org/t/p/original" + selectedItemsDetails.get(position).getImage()).apply(requestOptions).into(searchMainHolder.ivLogo);
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
        return selectedItemsDetails == null ? 0 : selectedItemsDetails.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == selectedItemsDetails.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
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
                    recyclerClickListener.onItemClicked(selectedItemsDetails.get(getLayoutPosition()));
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
        add(new SelectedItemDetails());
    }

    public void add(SelectedItemDetails mc) {
        selectedItemsDetails.add(mc);
        notifyItemInserted(selectedItemsDetails.size() - 1);
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = selectedItemsDetails.size() - 1;
        SelectedItemDetails item = getItem(position);

        if (item != null) {
            selectedItemsDetails.remove(position);
            notifyItemRemoved(position);
        }
    }

    public SelectedItemDetails getItem(int position) {
        return selectedItemsDetails.get(position);
    }

    public void addAll(List<SelectedItemDetails> mcList) {
        for (SelectedItemDetails mc : mcList) {
            add(mc);
        }
    }

}
