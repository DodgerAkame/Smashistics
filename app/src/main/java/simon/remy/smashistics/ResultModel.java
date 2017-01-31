package simon.remy.smashistics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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


}
