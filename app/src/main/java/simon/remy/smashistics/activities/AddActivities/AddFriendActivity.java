package simon.remy.smashistics.activities.AddActivities;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import simon.remy.smashistics.R;
import simon.remy.smashistics.database.MatchDbHelper;
import simon.remy.smashistics.database.OpponentContract;
import simon.remy.smashistics.model.MatchModel;

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
    private Spinner oppNick;
    private String user;
    private String opp;
    private EditText oppNickname_edit;
    private String oppNickname;
    private boolean isNewOpponent;

    private MatchDbHelper dbHelper;
    private SQLiteDatabase db;

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
        oppNick = (Spinner) findViewById(R.id.opp_spinner);
        radioGroup = (RadioGroup) findViewById(R.id.matchResult);
        win = (RadioButton) findViewById(R.id.win);
        loss = (RadioButton) findViewById(R.id.loss);
        oppNickname_edit = (EditText) findViewById(R.id.oppNickname);

        // Init the Spinner for Opponents' nicknames
        List<String> opponentsNames = new ArrayList<>();
        dbHelper = new MatchDbHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();

        // Requesting the database for opponent's nickname
        Cursor cursor = db.query(
                OpponentContract.OpponentEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            opponentsNames.add(cursor.getString(cursor.getColumnIndexOrThrow(OpponentContract.OpponentEntry.COLUMN_OPPONENT_NAME)));
        }

        opponentsNames.add("Add a new opponent");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, opponentsNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oppNick.setAdapter(adapter);
        oppNick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = oppNick.getSelectedItem().toString();
                if (selectedItem.equals("Add a new opponent")){
                   oppNickname_edit.setEnabled(true);
                   isNewOpponent = true;
                }
                else {
                    oppNickname_edit.setEnabled(false);
                    isNewOpponent = false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                        oppNickname = oppNickname_edit.getText().toString();

                        if (isNewOpponent){
                            db = dbHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put(OpponentContract.OpponentEntry.COLUMN_OPPONENT_NAME, oppNickname_edit.getText().toString());
                            db.insert(OpponentContract.OpponentEntry.TABLE_NAME, null, values);
                            db.close();
                        }

                        if (radioButton.getText().equals("Win")) hasWon = true;
                        MatchModel currentMatch = new MatchModel(user,oppNickname,opp,hasWon);

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
