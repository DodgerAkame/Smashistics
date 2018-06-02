package simon.remy.smashistics.database;

import android.provider.BaseColumns;

public class OpponentContract{
    private OpponentContract(){}

    public static class OpponentEntry implements BaseColumns{
        public static final String TABLE_NAME = "Opponents";
        public static final String COLUMN_OPPONENT_NAME = "Opponent_Name";
    }
}
