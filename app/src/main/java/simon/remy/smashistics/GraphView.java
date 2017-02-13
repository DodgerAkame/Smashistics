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

        canvas.drawText("0%",25,150,p);
        canvas.drawText("50%",20,85,p);
        canvas.drawText("100%",10,20,p);

        canvas.translate(35,0);

        canvas.drawLine(10, 10, 10, 150, p);
        canvas.drawLine(10, 150, rm.getResult().size()+20, 150, p);
        canvas.drawText("Ratio de victoire (%)",15, 20,p);

        p.setColor(Color.RED);
        canvas.translate(10,150);

        for (MatchModel mm : rm.getResult()){
            total++;
            if(mm.getHasWon()) rate++;

            canvas.drawPoint(total,-(rate/total)*150,p);

            if ((total % 100) == 0 | total == rm.getResult().size() -1){
                p.setColor(Color.BLACK);
                canvas.drawLine(total,5,total,-5,p);
                canvas.drawText(String.valueOf(total), total-5, 20,p);
                p.setColor(Color.RED);
            }

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
