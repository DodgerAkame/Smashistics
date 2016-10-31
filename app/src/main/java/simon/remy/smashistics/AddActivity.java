package simon.remy.smashistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
}
