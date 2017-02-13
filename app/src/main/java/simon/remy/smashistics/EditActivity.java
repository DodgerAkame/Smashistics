package simon.remy.smashistics;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.StringTokenizer;

/**
 * Created by dodger on 13/02/17.
 */

public class EditActivity extends AppCompatActivity {

    private Button valid;
    private RadioGroup radioGroup;
    private Spinner userchar;
    private Spinner oppchar;
    private EditText oppNickname_edit;
    private String user;
    private String opp;
    private DatePicker datePicker;
    private EditText comment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent data = getIntent();
        MatchModel mm = data.getParcelableExtra("match");
        final int position = data.getIntExtra("position", 0);

        userchar = (Spinner) findViewById(R.id.user_charspinner);
        ArrayAdapter adap = (ArrayAdapter) userchar.getAdapter();
        int userPos = adap.getPosition(mm.getUserChar());
        userchar.setSelection(userPos);

        oppchar = (Spinner) findViewById(R.id.opponent_char_spinner);
        ArrayAdapter adap2 = (ArrayAdapter) oppchar.getAdapter();
        int oppPos = adap2.getPosition(mm.getOppChar());
        oppchar.setSelection(oppPos);

        oppNickname_edit = (EditText) findViewById(R.id.oppNickname);
        oppNickname_edit.setText(mm.getOppNickname());

        datePicker = (DatePicker) findViewById(R.id.datePicker);

        radioGroup = (RadioGroup) findViewById(R.id.matchResult);

        comment = (EditText) findViewById(R.id.comment);

        valid = (Button) findViewById(R.id.anon_validate);
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(EditActivity.this)
                        .setTitle("Validate?")
                        .setMessage("Validate your match?")
                        .setPositiveButton("OK",null)
                        .setNegativeButton("Cancel",null)
                        .create();

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button okButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean hasWon = false;
                                int resultId = radioGroup.getCheckedRadioButtonId();
                                RadioButton radioButton = (RadioButton) findViewById(resultId);
                                user = userchar.getSelectedItem().toString();
                                opp = oppchar.getSelectedItem().toString();
                                String oppNickname = oppNickname_edit.getText().toString();
                                int year = datePicker.getYear();
                                int month = datePicker.getMonth()+1;
                                int day = datePicker.getDayOfMonth();
                                String comments = comment.getText().toString();

                                String date = day+"/"+month+"/"+year;

                                if (radioButton.getText().equals("Win")) hasWon = true;
                                MatchModel currentMatch = new MatchModel(user,oppNickname,opp,hasWon,date,comments);

                                Intent intent = getIntent();
                                intent.putExtra("position",position);
                                intent.putExtra("match", currentMatch);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        });

                        Button cancelButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });
                    }
                });

                alertDialog.show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
