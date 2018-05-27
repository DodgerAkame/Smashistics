package simon.remy.smashistics.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import simon.remy.smashistics.R;
import simon.remy.smashistics.activities.AddActivities.AddActivity;
import simon.remy.smashistics.database.MatchContract;
import simon.remy.smashistics.database.MatchDbHelper;
import simon.remy.smashistics.model.MatchModel;
import simon.remy.smashistics.model.ResultModel;


public class MainActivity extends AppCompatActivity {

    private final static int ADD_RESULT = 1;
    ArrayList<MatchModel> last10;
    private ResultModel rm;
    private ListView lv;
    private  MatchDbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        rm = new ResultModel();
        //rm.populate(getApplicationContext().getResources().getStringArray(R.array.chars));
        dbHelper = new MatchDbHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                MatchContract.MatchEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){

            String userChar = cursor.getString(cursor.getColumnIndexOrThrow(MatchContract.MatchEntry.COLUMN_USER_CHARA));
            String oppChar = cursor.getString(cursor.getColumnIndexOrThrow(MatchContract.MatchEntry.COLUMN_OPP_CHARA));
            String oppNick = cursor.getString(cursor.getColumnIndexOrThrow(MatchContract.MatchEntry.COLUMN_OPP_NICK));
            boolean hasWon = cursor.getInt(cursor.getColumnIndexOrThrow(MatchContract.MatchEntry.COLUMN_HAS_WON)) == 1;

            rm.getResult().add(new MatchModel(userChar, oppNick, oppChar, hasWon));
        }

        last10 = new ArrayList<MatchModel>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, ADD_RESULT);
            }
        });


       Button listButton = (Button) findViewById(R.id.list);
       listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                String[] match= new String[rm.getResult().size()];
                int i = 0;
                for (MatchModel mm : rm.getResult()){
                    match[i] = mm.getUserChar() + ";" + mm.getOppNickname() + ";" + mm.getOppChar() + ";" + mm.getHasWon().toString();
                    i++;
                }
                intent.putExtra("match",match);
                startActivity(intent);

            }
        });


       int i = rm.getResult().size() - 1;
       while(i >= 0) {
           last10.add(rm.getResult().get(i));
           i--;
       }
        lv = (ListView) findViewById(R.id.table_result);
        ArrayAdapter ad= new ArrayAdapter<MatchModel>(this,R.layout.listview,last10);
        lv.setAdapter(ad);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_RESULT && resultCode == RESULT_OK){
            MatchModel mm = data.getParcelableExtra("match");
            rm.addResult(mm);

            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(MatchContract.MatchEntry.COLUMN_USER_CHARA, mm.getUserChar());
            values.put(MatchContract.MatchEntry.COLUMN_OPP_NICK, mm.getOppNickname());
            values.put(MatchContract.MatchEntry.COLUMN_OPP_CHARA, mm.getOppChar());
            values.put(MatchContract.MatchEntry.COLUMN_HAS_WON, mm.getHasWon() ? 1 : 0);
            db.insert(MatchContract.MatchEntry.TABLE_NAME, null, values);

            last10.clear();
            int i = rm.getResult().size() - 1;
            while(i > -1) {
                last10.add(rm.getResult().get(i));
                i--;
            }
            ArrayAdapter ad= new ArrayAdapter<MatchModel>(this,R.layout.listview,last10);
            lv.setAdapter(ad);
        }
    }
}
