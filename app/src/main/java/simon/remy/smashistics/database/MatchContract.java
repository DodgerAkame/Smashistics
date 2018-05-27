package simon.remy.smashistics.database;

import android.provider.BaseColumns;

public final class MatchContract {

    private MatchContract(){}

    public static class MatchEntry implements BaseColumns{
        public static final String TABLE_NAME = "Result";
        public static final String COLUMN_USER_CHARA = "Your_Character";
        public static final String COLUMN_OPP_CHARA = "Opponnent_Character";
        public static final String COLUMN_OPP_NICK = "Opponent_Nickname";
        public static final String COLUMN_HAS_WON = "Win";
    }

}
