package myapp.com.moviedbsearch.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import myapp.com.moviedbsearch.data.FavouriteItemContract.FavouriteItemEntry;

public class FavouriteItemDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movie_finder.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FavouriteItemEntry.TABLE_NAME + " (" +
                    FavouriteItemEntry.COLUMN_ITEMID + " TEXT," +
                    FavouriteItemEntry.COLUMN_TITLE + " TEXT," +
                    FavouriteItemEntry.COLUMN_POSTERURL + " TEXT," +
                    FavouriteItemEntry.COLUMN_SUMMARY + " TEXT," +
                    FavouriteItemEntry.COLUMN_GENRE + " TEXT," +
                    FavouriteItemEntry.COLUMN_RATINGS + " TEXT," +
                    FavouriteItemEntry.COLUMN_RELEASEDATE + " TEXT," +
                    FavouriteItemEntry.COLUMN_TRAILER + " TEXT," +
                    FavouriteItemEntry.COLUMN_MEDIATYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FavouriteItemEntry.TABLE_NAME;

    public FavouriteItemDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
