package simon.remy.smashistics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.StringTokenizer;

/**
 * Created by dodger on 31/01/17.
 */

public class GraphActivity extends AppCompatActivity {

    private HorizontalScrollView hsv;
    private Bitmap bitmap;
    private Paint p;
    private ResultModel rm;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        rm = new ResultModel();
        Intent data = getIntent();

        int size = data.getIntExtra("list_size", 0);
        for (int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(data.getStringArrayExtra("match")[i].toString(),";");
            String userChar = st.nextToken();
            String opp = st.nextToken();
            String oppChar = st.nextToken();
            Boolean hasWon = (st.nextToken().equals("true"));


            rm.getResult().add(new MatchModel(userChar,opp,oppChar,hasWon));
        }

        hsv = (HorizontalScrollView) findViewById(R.id.canvas);


        hsv.post(new Runnable() {
            @Override
            public void run() {
                bitmap = Bitmap.createBitmap(hsv.getWidth(),hsv.getHeight(),Bitmap.Config.RGB_565);
            }
        });

        p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        p.setStyle(Paint.Style.STROKE);

        hsv.setLayerPaint(p);

        GraphView graphView = new GraphView(getApplicationContext());
        graphView.setP(p);
        graphView.setRm(rm);
        hsv.addView(graphView);
        graphView.draw(new Canvas());

        lv = (ListView) findViewById(R.id.table_result);
        ArrayAdapter ad= new ArrayAdapter<MatchModel>(this,R.layout.listview,rm.getResult());
        lv.setAdapter(ad);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
