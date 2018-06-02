package simon.remy.smashistics.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dodger on 12/11/16.
 */

public class MatchModel implements Parcelable {

    public static final Creator<MatchModel> CREATOR = new Creator<MatchModel>() {
        @Override
        public MatchModel createFromParcel(Parcel in) {
            String userChar = in.readString();
            String oppNickname = in.readString();
            String oppChar = in.readString();
            Boolean hasWon = in.readInt() != 0;

            return new MatchModel(userChar, oppNickname, oppChar, hasWon);
        }

        @Override
        public MatchModel[] newArray(int size) {
            return new MatchModel[size];
        }
    };

    private int id;
    private String userChar;
    private String oppChar;
    private int idOpp;
    private String oppNickname;
    private Boolean hasWon;

    public MatchModel(String userChar, String oppNicknamme, String oppChar, Boolean hasWon ) {
        this.oppNickname = oppNicknamme;
        this.oppChar = oppChar;
        this.hasWon = hasWon;
        this.userChar = userChar;
    }




    public String getUserChar() {
        return userChar;
    }

    public void setUserChar(String userChar) {
        this.userChar = userChar;
    }

    public Boolean getHasWon() {
        return hasWon;
    }

    public void setHasWon(Boolean hasWon) {
        this.hasWon = hasWon;
    }

    public String getOppChar() {
        return oppChar;
    }

    public void setOppChar(String oppChar) {
        this.oppChar = oppChar;
    }

    public String getOppNickname() { return oppNickname; }

    public void setOppNickname(String oppNickname) { this.oppNickname = oppNickname; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userChar);
        dest.writeString(oppNickname);
        dest.writeString(oppChar);
        dest.writeInt(hasWon ? 1 : 0);
    }

    @Override
    public String toString() {
        return "Opponent : " + oppNickname +"\nMatch : " +userChar+" vs "+ oppChar +"\nResult : "+(hasWon?"Won":"Loss");
    }
}
