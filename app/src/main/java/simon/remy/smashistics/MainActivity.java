package simon.remy.smashistics;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private final static int ADD_RESULT = 1;
    private final static int CANCEL_RESULT = 10;
    private final static int LIST = 6;
    private ResultModel rm;
    private ListView lv;
    ArrayList<MatchModel> last10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        rm = new ResultModel();
        rm.populate(getApplicationContext().getResources().getStringArray(R.array.chars));

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
                String[] match = new String[rm.getResult().size()];
                int i = 0;
                for (MatchModel mm : rm.getResult()) {
                    match[i] = mm.getUserChar() + ";" + mm.getOppNickname() + ";" + mm.getOppChar() + ";" + mm.getHasWon().toString() + ";" + mm.getDate() + ";" + mm.getComment();
                    i++;
                }
                intent.putExtra("match", match);
                startActivityForResult(intent, LIST);

            }
        });


        for (int i = rm.getResult().size() - 1; i > rm.getResult().size() - 11; i--)
            last10.add(rm.getResult().get(i));
        lv = (ListView) findViewById(R.id.table_result);
        ArrayAdapter ad = new ArrayAdapter<MatchModel>(this, R.layout.listview, last10);
        lv.setAdapter(ad);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_RESULT && resultCode == RESULT_OK) {
            MatchModel mm = data.getParcelableExtra("match");
            rm.addResult(mm);
            last10.clear();
            for (int i = rm.getResult().size() - 1; i > rm.getResult().size() - 11; i--)
                last10.add(rm.getResult().get(i));
            ArrayAdapter ad = new ArrayAdapter<MatchModel>(this, R.layout.listview, last10);
            lv.setAdapter(ad);
        }
        if (resultCode == CANCEL_RESULT && requestCode == LIST) {
            switch (data.getStringExtra("mode")) {
                case "delete":
                    int position = data.getIntExtra("position", 0);
                    rm.getResult().remove(rm.getResult().size() - position - 1);
                    last10.clear();
                    Toast.makeText(this, "Match successfully deleted", Toast.LENGTH_SHORT).show();
                    for (int i = rm.getResult().size() - 1; i > rm.getResult().size() - 11; i--)
                        last10.add(rm.getResult().get(i));
                    ArrayAdapter ad = new ArrayAdapter<MatchModel>(this, R.layout.listview, last10);
                    lv.setAdapter(ad);
                    break;
                case "update":
                    MatchModel mm = data.getParcelableExtra("match");
                    int pos = data.getIntExtra("position", 0);
                    rm.getResult().set(rm.getResult().size() - pos - 1, mm);
                    last10.clear();
                    Toast.makeText(this, "Match successfully updated", Toast.LENGTH_SHORT).show();
                    for (int i = rm.getResult().size() - 1; i > rm.getResult().size() - 11; i--)
                        last10.add(rm.getResult().get(i));
                    ArrayAdapter ad2 = new ArrayAdapter<MatchModel>(this, R.layout.listview, last10);
                    lv.setAdapter(ad2);
                    break;
                default:
                    break;
            }
        }
    }
}
