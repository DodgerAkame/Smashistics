package simon.remy.smashistics;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by dodger on 31/10/16.
 */

public class AddAnonymousActivity extends AppCompatActivity {

    private Button valid;
    private RadioGroup radioGroup;
    private Spinner userchar;
    private Spinner oppchar;
    private String user;
    private String opp;
    private DatePicker datePicker;

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
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        radioGroup = (RadioGroup) findViewById(R.id.matchResult);


    }

    public void onValid(View v) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
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
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth()+1;
                        int day = datePicker.getDayOfMonth();

                        String date = day+"/"+month+"/"+year;

                        if (radioButton.getText().equals("Win")) hasWon = true;
                        MatchModel currentMatch = new MatchModel(user,"anonymous",opp,hasWon,date," ");

                        Intent intent = getIntent();
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

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

}
