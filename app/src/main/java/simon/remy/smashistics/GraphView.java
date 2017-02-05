package simon.remy.smashistics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by dodger on 31/01/17.
 */

public class GraphView extends View {

    Canvas canvas;
    private Paint p;
    private Context context;
    private ResultModel rm;

    public GraphView(Context content) {
        super(content);
        context = content;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        float total = 0;
        float rate= 0;


        p.setColor(Color.BLACK);

        canvas.drawLine(10, 10, 10, 150, p);
        canvas.drawLine(10, 150, rm.getResult().size()+20, 150, p);

        p.setColor(Color.RED);
        canvas.translate(10,150);

        for (MatchModel mm : rm.getResult()){
            total++;
            if(mm.getHasWon()) rate++;

            canvas.drawPoint(total,-(rate/total)*150,p);

        }



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = rm.getResult().size()+ 100;
        int height = 2000;
        setMeasuredDimension(width, height);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Paint getP() {
        return p;
    }

    public void setP(Paint p) {
        this.p = p;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ResultModel getRm() {
        return rm;
    }

    public void setRm(ResultModel rm) {
        this.rm = rm;
    }
}
