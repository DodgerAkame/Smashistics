package simon.remy.smashistics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by dodger on 05/02/17.
 */

public class SettingsActivity extends Activity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private RadioButton r1;
    private RadioButton r2;
    private EditText editText;
    private RadioGroup rg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsetting);

        rg = (RadioGroup) findViewById(R.id.settings);

        r1 = (RadioButton) findViewById(R.id.radioButton);
        r1.setOnCheckedChangeListener(this);

        editText = (EditText) findViewById(R.id.editText4);
        editText.setVisibility(View.GONE);
        active(false);

    }

    private void active(final boolean b) {
        editText.setEnabled(b);
        if (b) editText.requestFocus();
    }


    //Ne fonctionne pas sur Marshmallow
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radioButton:
                active(true);
                break;
            case R.id.radioButton2:
                active(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            active(true);
            editText.setVisibility(View.VISIBLE);
        } else{
            active(false);
            editText.setVisibility(View.GONE);
        }
    }
}

