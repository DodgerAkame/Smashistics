package simon.remy.smashistics;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.Collections;
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

    final private static int DELETE_RESULT = 1;
    final private static int CANCEL_DELETE = 10;
    final private static int DISPLAY_MATCH = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        rm = new ResultModel();
        Intent data = getIntent();

        int size = data.getIntExtra("list_size", 0);
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(data.getStringArrayExtra("match")[i].toString(), ";");
            String userChar = st.nextToken();
            String opp = st.nextToken();
            String oppChar = st.nextToken();
            Boolean hasWon = (st.nextToken().equals("true"));
            String date = st.nextToken();
            String comment = st.nextToken();


            rm.getResult().add(new MatchModel(userChar, opp, oppChar, hasWon, date, comment));
        }

        hsv = (HorizontalScrollView) findViewById(R.id.canvas);


        hsv.post(new Runnable() {
            @Override
            public void run() {
                bitmap = Bitmap.createBitmap(hsv.getWidth(), hsv.getHeight(), Bitmap.Config.RGB_565);
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
        Collections.reverse(rm.getResult());
        ArrayAdapter ad = new ArrayAdapter<MatchModel>(this, R.layout.listview, rm.getResult());
        lv.setAdapter(ad);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MatchModel mm = rm.getResult().get(position);
                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                intent.putExtra("matchdisplay",mm);
                intent.putExtra("position", position);
                startActivityForResult(intent, DISPLAY_MATCH);


                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DISPLAY_MATCH && resultCode == RESULT_OK ){
            int position = data.getIntExtra("position",0);
            String mode = data.getStringExtra("mode");
            Intent intent = getIntent();
            if (mode.equalsIgnoreCase("update")){
                intent.putExtra("match",data.getParcelableExtra("match"));
            }
            intent.putExtra("mode", mode);
            intent.putExtra("position",position);
            setResult(CANCEL_DELETE,intent);
            finish();
        }
    }
}
