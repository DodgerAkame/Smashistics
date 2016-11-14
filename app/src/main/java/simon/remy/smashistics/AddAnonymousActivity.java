package simon.remy.smashistics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by dodger on 31/10/16.
 */

public class AddAnonymousActivity extends AppCompatActivity {

    private Button valid;
    private RadioButton win;
    private RadioButton loss;
    private RadioButton result;
    private RadioGroup radioGroup;
    private Spinner userchar;
    private Spinner oppchar;
    private String user;
    private String opp;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addanonymous);

        valid = (Button) findViewById(R.id.anon_validate);
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onValid(v);
            }
        });

        userchar = (Spinner) findViewById(R.id.user_charspinner);
        oppchar = (Spinner) findViewById(R.id.opponent_char_spinner);
        radioGroup = (RadioGroup) findViewById(R.id.matchResult);
        win = (RadioButton) findViewById(R.id.win);
        loss = (RadioButton) findViewById(R.id.loss);

    }

    public void onValid(View v) {
        Boolean hasWon = false;
        int resultId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(resultId);
        user = userchar.getSelectedItem().toString();
        opp = oppchar.getSelectedItem().toString();

        //TODO Instancier MatchModel, puis faire un Intent pour envoyer les résultats dans le MainActivity
        if (radioButton.getText().equals("Win")) hasWon = true;
        MatchModel currentMatch = new MatchModel(user,"anonymous",opp,hasWon);

        Intent intent = getIntent();
        intent.putExtra("match", currentMatch);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    //TODO fenêtre de validation
}
