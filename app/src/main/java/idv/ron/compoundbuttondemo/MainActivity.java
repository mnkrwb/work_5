package idv.ron.compoundbuttondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private TextView tvCount;
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private int count = 0;

    private TextView tvText;
    private SeekBar sbSize;

    private TextView tvMessage;
    private RadioGroup rgGender;
    private Switch swWifi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViews();
        tvCount.setText(String.valueOf(count));
    }

    public void one(View v){
        Intent i = new Intent();
        i.setClass(this,Main2Activity.class);
        startActivity(i);
    }

    private void findViews() {
        tvCount = (TextView) findViewById(R.id.tvCount);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        tvText = (TextView) findViewById(R.id.tvText);
        sbSize = (SeekBar) findViewById(R.id.sbSize);

        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvText.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "start size = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "stop size = " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        tvMessage = (TextView) findViewById(R.id.tvMessage);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        swWifi = (Switch) findViewById(R.id.swWifi);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group
                        .findViewById(checkedId);
                tvMessage.setText(radioButton.getText());
            }
        });

        swWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Switch sw = (Switch) buttonView;
                String swName = sw.getText().toString();
                String message = "";
                if (isChecked) {
                    message += swName + " " + sw.getTextOn();
                } else {
                    message += swName + " " + sw.getTextOff();
                }
                tvMessage.setText(message);
            }
        });
    }

    public void onAddClick(View view) {
        count++;
        tvCount.setText(String.valueOf(count));

        TextView textView = new TextView(this);
        textView.setText(String.valueOf(count));
        linearLayout.addView(textView);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    public void onPlaceClick(View v) {
        CheckBox checkBox = (CheckBox) v;
        String checkBoxName = checkBox.getText().toString();
        String message;
        if (checkBox.isChecked())
            message = getString(R.string.checked) + " " + checkBoxName;
        else {
            message = getString(R.string.unchecked) + " " + checkBoxName;
        }
        tvMessage.setText(message);

    }

    public void onVibrateClick(View v) {
        ToggleButton toggleButton = (ToggleButton) v;
        tvMessage.setText(toggleButton.getText());
    }

}
