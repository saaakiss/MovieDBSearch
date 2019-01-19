package myapp.com.moviedbsearch.data;

import android.provider.BaseColumns;

public class FavouriteItemContract {

    private FavouriteItemContract(){}

    public static class FavouriteItemEntry implements BaseColumns{
        public static final String TABLE_NAME = "FavouriteItems";
        public static final String COLUMN_ITEMID = "item_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTERURL = "poster_url";
        public static final String COLUMN_SUMMARY = "summary";
        public static final String COLUMN_GENRE = "genre";
        public static final String COLUMN_RATINGS = "ratings";
        public static final String COLUMN_RELEASEDATE = "release_date";
        public static final String COLUMN_TRAILER = "trailer";
        public static final String COLUMN_MEDIATYPE = "media_type";
    }
}
