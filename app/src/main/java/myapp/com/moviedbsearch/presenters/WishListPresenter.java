package myapp.com.moviedbsearch.presenters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import myapp.com.moviedbsearch.contracts.WishListContract;
import myapp.com.moviedbsearch.data.FavouriteItemContract;
import myapp.com.moviedbsearch.data.FavouriteItemDbHelper;
import myapp.com.moviedbsearch.models.SelectedItemDetails;

public class WishListPresenter implements WishListContract.Actions {

    private WishListContract.View mView;

    public WishListPresenter(WishListContract.View mView){
        this.mView = mView;
    }

    @Override
    public void getAllMoviesTvShows(Context context) {
        FavouriteItemDbHelper mDbHelper = new FavouriteItemDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                FavouriteItemContract.FavouriteItemEntry.COLUMN_ITEMID,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_TITLE,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_POSTERURL,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_SUMMARY,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_GENRE,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_RATINGS,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_RELEASEDATE,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_TRAILER,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_MEDIATYPE
        };

        Cursor cursor = db.query(
                FavouriteItemContract.FavouriteItemEntry.TABLE_NAME,                    // The table to query
                projection,                                                             // The columns to return
                null,                                                              // The columns for the WHERE clause
                null,                                                          // The values for the WHERE clause
                null,                                                           // don't group the rows
                null,                                                            // don't filter by row groups
                null                                                             // The sort order
        );

        List<SelectedItemDetails> selectedItemsDetails = new ArrayList<>();
        while(cursor.moveToNext()){
            String itemID = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_ITEMID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_TITLE));
            String posterURL = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_POSTERURL));
            String summary = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_SUMMARY));
            String genre = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_GENRE));
            String ratings = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_RATINGS));
            String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_RELEASEDATE));
            String trailer = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_TRAILER));
            String mediaType = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_MEDIATYPE));
            SelectedItemDetails selectedItemDetails = new SelectedItemDetails();

            selectedItemDetails.setId(itemID);
            selectedItemDetails.setTitle(title);
            selectedItemDetails.setImage(posterURL);
            selectedItemDetails.setSummary(summary);
            selectedItemDetails.setGenre(genre);
            selectedItemDetails.setRatings(ratings);
            selectedItemDetails.setRelease_date(releaseDate);
            selectedItemDetails.setTrailerUrl(trailer);
            selectedItemDetails.setItemType(mediaType);

            selectedItemsDetails.add(selectedItemDetails);
        }

        cursor.close();
        db.close();

        mView.showMoviesTvShows( selectedItemsDetails);
    }

    @Override
    public void getMoviesTvShows(String query, Context context) {
        FavouriteItemDbHelper mDbHelper = new FavouriteItemDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                FavouriteItemContract.FavouriteItemEntry.COLUMN_ITEMID,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_TITLE,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_POSTERURL,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_SUMMARY,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_GENRE,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_RATINGS,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_RELEASEDATE,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_TRAILER,
                FavouriteItemContract.FavouriteItemEntry.COLUMN_MEDIATYPE
        };
        String selection = FavouriteItemContract.FavouriteItemEntry.COLUMN_TITLE + " LIKE ?";
        String[] selectionArgs = { query };

        Cursor cursor = db.query(
                FavouriteItemContract.FavouriteItemEntry.TABLE_NAME,                    // The table to query
                projection,                                                             // The columns to return
                selection,                                                              // The columns for the WHERE clause
                selectionArgs,                                                          // The values for the WHERE clause
                null,                                                           // don't group the rows
                null,                                                            // don't filter by row groups
                null                                                             // The sort order
        );

        List<SelectedItemDetails> selectedItemsDetails = new ArrayList<>();
        while(cursor.moveToNext()){
            String itemID = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_ITEMID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_TITLE));
            String posterURL = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_POSTERURL));
            String summary = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_SUMMARY));
            String genre = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_GENRE));
            String ratings = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_RATINGS));
            String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_RELEASEDATE));
            String trailer = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_TRAILER));
            String mediaType = cursor.getString(cursor.getColumnIndexOrThrow(FavouriteItemContract.FavouriteItemEntry.COLUMN_MEDIATYPE));
            SelectedItemDetails selectedItemDetails = new SelectedItemDetails();

            selectedItemDetails.setId(itemID);
            selectedItemDetails.setTitle(title);
            selectedItemDetails.setImage(posterURL);
            selectedItemDetails.setSummary(summary);
            selectedItemDetails.setGenre(genre);
            selectedItemDetails.setRatings(ratings);
            selectedItemDetails.setRelease_date(releaseDate);
            selectedItemDetails.setTrailerUrl(trailer);
            selectedItemDetails.setItemType(mediaType);

            selectedItemsDetails.add(selectedItemDetails);
        }

        cursor.close();
        db.close();

        mView.showMoviesTvShows( selectedItemsDetails);
    }

}
