package simon.remy.smashistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by dodger on 05/02/17.
 */

public class SettingsActivity extends Activity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {


    private EditText editText;
    private Spinner singleChar;
    private Spinner doubleChar1;
    private Spinner doubleChar2;
    private TextView text;
    private RadioGroup rg;
    private Button validate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsetting);

        Intent data = getIntent();
        final String[] match = data.getStringArrayExtra("match");

        rg = (RadioGroup) findViewById(R.id.settings);
        rg.setOnCheckedChangeListener(this);

        text = (TextView)findViewById(R.id.oppnick_name);

        editText = (EditText) findViewById(R.id.editText4);
        editText.setVisibility(View.GONE);

        singleChar = (Spinner) findViewById(R.id.singlechar_result);
        singleChar.setVisibility(View.GONE);

        doubleChar1 = (Spinner) findViewById(R.id.doublechar_result1);
        doubleChar1.setVisibility(View.GONE);

        doubleChar2 = (Spinner) findViewById(R.id.doublechar_result2);
        doubleChar2.setVisibility(View.GONE);

        validate = (Button) findViewById(R.id.result_validate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                ArrayList<String> buffer = new ArrayList<String>();
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.global_result:
                        intent.putExtra("list_size",match.length);
                        intent.putExtra("match", match);
                        startActivity(intent);
                        break;
                    case R.id.oppnick_result:
                        for (String result : match){
                            StringTokenizer st = new StringTokenizer(result,";");
                            st.nextToken();
                            String nick = st.nextToken();
                            if(nick.equalsIgnoreCase(editText.getText().toString())) {
                                buffer.add(result);
                            }
                        }
                        if (buffer.size() > 0){
                            intent.putExtra("list_size",buffer.size());
                            String[] yo = buffer.toArray(new String[buffer.size()]);
                            intent.putExtra("match", yo);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SettingsActivity.this, "No result found", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.userchar_result:
                        for(String result: match){
                            StringTokenizer st = new StringTokenizer(result,";");
                            String user = st.nextToken();
                            if (user.equalsIgnoreCase(singleChar.getSelectedItem().toString()))
                                buffer.add(result);
                        }
                            if (buffer.size() > 0){
                                intent.putExtra("list_size",buffer.size());
                                String[] yo = buffer.toArray(new String[buffer.size()]);
                                intent.putExtra("match", yo);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SettingsActivity.this, "No result found", Toast.LENGTH_SHORT).show();
                            }
                        break;
                    case R.id.oppchar_result:
                        for(String result: match){
                            StringTokenizer st = new StringTokenizer(result,";");
                            st.nextToken();
                            st.nextToken();
                            String opp = st.nextToken();
                            if (opp.equalsIgnoreCase(singleChar.getSelectedItem().toString()))
                                buffer.add(result);
                        }
                        if (buffer.size() > 0){
                            intent.putExtra("list_size",buffer.size());
                            String[] yo = buffer.toArray(new String[buffer.size()]);
                            intent.putExtra("match", yo);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SettingsActivity.this, "No result found", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.bothchar_result:
                        for(String result: match){
                            StringTokenizer st = new StringTokenizer(result,";");
                            String user = st.nextToken();
                            st.nextToken();
                            String opp = st.nextToken();
                            if (user.equalsIgnoreCase(doubleChar1.getSelectedItem().toString()) && opp.equalsIgnoreCase(doubleChar2.getSelectedItem().toString()))
                                buffer.add(result);
                        }
                        if (buffer.size() > 0){
                            intent.putExtra("list_size",buffer.size());
                            String[] yo = buffer.toArray(new String[buffer.size()]);
                            intent.putExtra("match", yo);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SettingsActivity.this, "No result found", Toast.LENGTH_SHORT).show();
                        }
                    default:
                        break;
                }
            }
        });
    }

    private void active( View v) {
        //editText.setEnabled(b);
        //if (b) editText.requestFocus();
        switch(v.getId()){
            case R.id.editText4:
                editText.setEnabled(true);
                editText.requestFocus();
                singleChar.setEnabled(false);
                break;
            case R.id.singlechar_result:
                singleChar.setEnabled(true);
                singleChar.requestFocus();
                editText.setEnabled(false);
                break;
            case R.id.doublechar_result1:
                editText.setEnabled(false);
                singleChar.setEnabled(false);
                doubleChar1.setEnabled(true);
                doubleChar2.setEnabled(true);
                doubleChar1.requestFocus();
                doubleChar2.requestFocus();
            default:
                editText.setEnabled(false);
                singleChar.setEnabled(false);
                break;
        }
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.global_result:
                active(editText);
                editText.setVisibility(View.GONE);
                singleChar.setVisibility(View.GONE);
                doubleChar1.setVisibility(View.GONE);
                doubleChar2.setVisibility(View.GONE);
                text.setText("");
                break;
            case R.id.userchar_result:
                active(singleChar);
                editText.setVisibility(View.GONE);
                singleChar.setVisibility(View.VISIBLE);
                doubleChar1.setVisibility(View.GONE);
                doubleChar2.setVisibility(View.GONE);
                //TODO mettre ID
                text.setText("Choose your character");
                break;
            case R.id.oppnick_result:
                active(editText);
                editText.setVisibility(View.VISIBLE);
                singleChar.setVisibility(View.GONE);
                doubleChar1.setVisibility(View.GONE);
                doubleChar2.setVisibility(View.GONE);
                //TODO mettre ID
                text.setText("Type your opponent's nickname");
                break;
            case R.id.oppchar_result:
                active(singleChar);
                editText.setVisibility(View.GONE);
                singleChar.setVisibility(View.VISIBLE);
                doubleChar1.setVisibility(View.GONE);
                doubleChar2.setVisibility(View.GONE);
                //TODO mettre ID
                text.setText("Choose your opponent's character");
                break;
            case R.id.bothchar_result:
                active(doubleChar1);
                editText.setVisibility(View.GONE);
                singleChar.setVisibility(View.GONE);
                doubleChar1.setVisibility(View.VISIBLE);
                doubleChar2.setVisibility(View.VISIBLE);
                //TODO mettre ID
                text.setText("Choose both characters");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {}
}

