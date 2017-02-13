package simon.remy.smashistics;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dodger on 13/02/17.
 */

public class DisplayActivity extends AppCompatActivity {

    private TextView match;
    private TextView wonloss;
    private TextView date;
    private TextView comment;
    private Button editButton;
    private Button deleteButton;
    final private static int CANCEL_DELETE = 10;
    final private static int EDIT_ACTIVITY = 30;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Intent data = getIntent();
        final MatchModel mm = data.getParcelableExtra("matchdisplay");
        final int position = data.getIntExtra("position", 0);

        match = (TextView) findViewById(R.id.display_match);
        match.setText(mm.getUserChar() + " vs " + mm.getOppChar() + " (" + mm.getOppNickname() + ")");

        wonloss = (TextView) findViewById(R.id.display_match_wonloss);
        wonloss.setText((mm.getHasWon() ? "Won" : "Loss"));

        date = (TextView) findViewById(R.id.display_date);
        date.setText(mm.getDate());

        comment = (TextView) findViewById(R.id.display_comment);
        comment.setText(mm.getComment());

        editButton = (Button) findViewById(R.id.edit_display);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("match",mm);
                intent.putExtra("position", position);
                startActivityForResult(intent,EDIT_ACTIVITY);
            }
        });

        deleteButton = (Button) findViewById(R.id.delete_display);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(DisplayActivity.this)
                        .setTitle("Delete?")
                        .setMessage("Delete this match?")
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel!", null)
                        .create();

             alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                 @Override
                 public void onShow(DialogInterface dialog) {
                     Button okButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                     okButton.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Intent intent = getIntent();
                             intent.putExtra("mode","delete");
                             intent.putExtra("position", position);
                             setResult(RESULT_OK,intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_ACTIVITY && resultCode == RESULT_OK){
            Intent intent = getIntent();
            intent.putExtra("mode", "update");
            intent.putExtra("match",data.getParcelableExtra("match"));
            intent.putExtra("position", data.getIntExtra("position",0));
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
