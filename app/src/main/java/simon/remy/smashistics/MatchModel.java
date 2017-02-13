package simon.remy.smashistics;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

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
            String date = in.readString();
            String comment = in.readString();

            return new MatchModel(userChar, oppNickname, oppChar, hasWon,date, comment);
        }

        @Override
        public MatchModel[] newArray(int size) {
            return new MatchModel[size];
        }
    };

    private String userChar;
    private String oppChar;
    private String oppNickname;
    private Boolean hasWon;
    private String date;
    private String comment;

    public MatchModel(String userChar, String oppNicknamme, String oppChar, Boolean hasWon ) {
        this.oppNickname = oppNicknamme;
        this.oppChar = oppChar;
        this.hasWon = hasWon;
        this.userChar = userChar;
    }

    public MatchModel(String userChar, String oppNicknamme, String oppChar, Boolean hasWon, String date, String comment){
        this.oppNickname = oppNicknamme;
        this.oppChar = oppChar;
        this.hasWon = hasWon;
        this.userChar = userChar;
        this.date = date;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        dest.writeString(date);
        dest.writeString(comment);
    }

    @Override
    public String toString() {
        return "Date : " + date+ "\nOpponent : " + oppNickname +"\nMatch : " +userChar+" vs "+ oppChar +"\nResult : "+(hasWon?"Won":"Loss");
    }
}
