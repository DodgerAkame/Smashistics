package simon.remy.smashistics.model;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by e1402378 on 14/11/2016.
 */

public class ResultModel {

    private List<MatchModel> result = new ArrayList<MatchModel>();
    private Paint p;

    public ResultModel() {

    }

    public List<MatchModel> getResult() {
        return result;
    }

    public void setResult(List<MatchModel> result) {
        this.result = result;
    }

    public void addResult(MatchModel mm){
        result.add(mm);
    }

    public List<MatchModel> getVictory(String winOrLoss){
        ArrayList<MatchModel> buffer = new ArrayList<MatchModel>();
        if (winOrLoss.equalsIgnoreCase("Win")){
            for (MatchModel mm : result){
                if (mm.getHasWon())  buffer.add(mm);
            }
        } else {
            for (MatchModel mm : result){
                if (!mm.getHasWon()) buffer.add(mm);
            }
        }
        return buffer;
    }

    public void populate(String[] characters){
        for (int i = 0; i < 5000; i++){
            double rdn = Math.random();
            Boolean hasWOn = (rdn > 0.35);
            String char1 = characters[new Random().nextInt(characters.length)];
            String char2 = characters[new Random().nextInt(characters.length)];
            result.add(new MatchModel(char1,"anonymous",char2,hasWOn));
        }
    }

}
