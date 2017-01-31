package simon.remy.smashistics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by dodger on 31/01/17.
 */

public class GraphView extends View {

    private Paint p;
    private Context context;
    Canvas canvas;

    public GraphView(Context content){
        super(content);
        context = content;

        //DisplayMetrics metrics = new DisplayMetrics();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

        canvas.drawLine(0,0,200,200,p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 2000;
        int height = 2000;
        setMeasuredDimension(width,height);
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
}
