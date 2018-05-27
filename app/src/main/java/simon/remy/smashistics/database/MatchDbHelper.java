package simon.remy.smashistics.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MatchDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MatchDB.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + MatchContract.MatchEntry.TABLE_NAME + "(" +
                    MatchContract.MatchEntry._ID + " INTEGER PRIMARY KEY," +
                    MatchContract.MatchEntry.COLUMN_USER_CHARA + " TEXT," +
                    MatchContract.MatchEntry.COLUMN_OPP_NICK + " TEXT," +
                    MatchContract.MatchEntry.COLUMN_OPP_CHARA + " TEXT," +
                    MatchContract.MatchEntry.COLUMN_HAS_WON + " NUMERIC)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MatchContract.MatchEntry.TABLE_NAME;

    public MatchDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
