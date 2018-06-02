package simon.remy.smashistics.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MatchDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MatchDB.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MATCH =
            "CREATE TABLE " + MatchContract.MatchEntry.TABLE_NAME + "(" +
                    MatchContract.MatchEntry._ID + " INTEGER PRIMARY KEY," +
                    MatchContract.MatchEntry.COLUMN_USER_CHARA + " TEXT," +
                    MatchContract.MatchEntry.COLUMN_OPP_NICK + " TEXT," +
                    MatchContract.MatchEntry.COLUMN_OPP_CHARA + " TEXT," +
                    MatchContract.MatchEntry.COLUMN_HAS_WON + " NUMERIC)";

    private static final String SQL_DELETE_ENTRIES_MATCH =
            "DROP TABLE IF EXISTS " + MatchContract.MatchEntry.TABLE_NAME;

    private static final String SQL_CREATE_TABLE_OPPONENTS =
            "CREATE TABLE " + OpponentContract.OpponentEntry.TABLE_NAME +"(" +
                    OpponentContract.OpponentEntry._ID + " INTEGER PRIMARY KEY," +
                    OpponentContract.OpponentEntry.COLUMN_OPPONENT_NAME + " NAME)";

    private static final String SQL_DELETE_ENTRIES_OPPONENTS =
            "DROP TABLE IF EXISTS " + OpponentContract.OpponentEntry.TABLE_NAME;

    public MatchDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MATCH);
        db.execSQL(SQL_CREATE_TABLE_OPPONENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_MATCH);
        db.execSQL(SQL_DELETE_ENTRIES_OPPONENTS);
        onCreate(db);
    }

    public void onDelete(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES_MATCH);
        db.execSQL(SQL_DELETE_ENTRIES_OPPONENTS);
    }
}