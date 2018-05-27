package simon.remy.smashistics.activities.AddActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import simon.remy.smashistics.R;
import simon.remy.smashistics.model.MatchModel;

/**
 * Created by dodger on 31/10/16.
 */

public class AddActivity extends AppCompatActivity {

    private final static int ADD_ANONYMOUS = 2;
    private final static int ADD_FRIEND = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button addAnonymous = (Button) findViewById(R.id.add_anonymous);
        addAnonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAnonymousActivity.class);
                startActivityForResult(intent, ADD_ANONYMOUS);
            }
        });

        Button addFriend = (Button) findViewById(R.id.add_friend);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddFriendActivity.class);
                startActivityForResult(intent, ADD_FRIEND);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ANONYMOUS && resultCode == RESULT_OK){
            MatchModel mm = data.getParcelableExtra("match");
            Toast.makeText(this, "Match successfully saved", Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            intent.putExtra("match", mm);
            setResult(RESULT_OK, intent);
            finish();
        }

        if (requestCode == ADD_FRIEND && resultCode == RESULT_OK){
            MatchModel mm = data.getParcelableExtra("match");
            Toast.makeText(this, "Match successfully saved", Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            intent.putExtra("match", mm);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
