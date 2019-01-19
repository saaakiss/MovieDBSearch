package myapp.com.moviedbsearch.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import myapp.com.moviedbsearch.data.FavouriteItemContract;
import myapp.com.moviedbsearch.data.FavouriteItemDbHelper;

public class Utilities {

    // Check if there is a network connection.
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isWishListHasItems(Context context){
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
                FavouriteItemContract.FavouriteItemEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }

        cursor.close();
        return false;
    }

    public static String formatDate(String releaseDate){
        SimpleDateFormat fromApi = new SimpleDateFormat("yyyyy-mm-dd", Locale.US);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        try {
            String formattedDate = myFormat.format(fromApi.parse(releaseDate));
            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}
