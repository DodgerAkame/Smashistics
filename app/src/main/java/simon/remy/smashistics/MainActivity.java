package simon.remy.smashistics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private final static int ADD_RESULT = 1;
    //private final static int LIST_RESULT = 4;
    private ResultModel rm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rm = new ResultModel();
        rm.populate();

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
       /* listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                //intent.putExtra("result",(Parcelable) rm.getResult());
                intent.putExtra("list_size",rm.getResult().size());
                String[] match= new String[rm.getResult().size()];
                int i = 0;
                for (MatchModel mm : rm.getResult()){
                    match[i] = mm.getUserChar() + ";" + mm.getOppNickname() + ";" + mm.getOppChar() + ";" + mm.getHasWon().toString();
                    i++;
                }
                intent.putExtra("match",match);
                startActivity(intent);

            }
        });*/

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_RESULT && resultCode == RESULT_OK){
            MatchModel mm = data.getParcelableExtra("match");
            rm.addResult(mm);
        }
    }
}
