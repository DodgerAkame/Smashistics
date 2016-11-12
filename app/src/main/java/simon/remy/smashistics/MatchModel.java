package simon.remy.smashistics;

/**
 * Created by dodger on 12/11/16.
 */

public class MatchModel {

    private String userChar;
    private String oppChar;
    private Boolean hasWon;

    public MatchModel(String oppChar, Boolean hasWon, String userChar) {
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



}
