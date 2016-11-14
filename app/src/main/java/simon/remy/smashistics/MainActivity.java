package simon.remy.smashistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final static int ADD_RESULT = 1;
    private final static int LIST_RESULT = 4;
    private ResultModel rm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rm = new ResultModel();

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

        //TODO Continuer ici
        Button listButton = (Button) findViewById(R.id.list);

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
