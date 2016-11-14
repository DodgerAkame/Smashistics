package simon.remy.smashistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by dodger on 31/10/16.
 */

public class AddFriendActivity extends AppCompatActivity{

    private Button valid;
    private RadioButton win;
    private RadioButton loss;
    private RadioButton result;
    private RadioGroup radioGroup;
    private Spinner userchar;
    private Spinner oppchar;
    private String user;
    private String opp;
    private EditText oppNickname_edit;
    private String oppNickname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);

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
        oppNickname_edit = (EditText) findViewById(R.id.oppNickname);

    }

    public void onValid(View v) {
        Boolean hasWon = false;
        int resultId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(resultId);
        user = userchar.getSelectedItem().toString();
        opp = oppchar.getSelectedItem().toString();
        oppNickname = oppNickname_edit.getText().toString();

        if (radioButton.getText().equals("Win")) hasWon = true;
        MatchModel currentMatch = new MatchModel(user,oppNickname,opp,hasWon);

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
}
