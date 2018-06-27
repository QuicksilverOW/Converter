package com.tatarinovich.converter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements View.OnTouchListener {

    Switch switch1;
    Switch switch2;
    Switch switch3;
    Switch switch4;
    Switch switch5;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();

        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        switch4 = findViewById(R.id.switch4);
        switch5 = findViewById(R.id.switch5);
        button = findViewById(R.id.button);
        button.setOnTouchListener(this);

        switch1.setChecked(intent.getBooleanExtra(MainActivity.EXTRA_VALUES, false));
        switch2.setChecked(intent.getBooleanExtra(MainActivity.JAPAN_VALUES, false));
        switch3.setChecked(intent.getBooleanExtra(MainActivity.CHINA_VALUES, false));
        switch4.setChecked(intent.getBooleanExtra(MainActivity.CULINARY_VALUES, false));
        switch5.setChecked(intent.getBooleanExtra(MainActivity.OLD_VALUES, false));
    }
    public void onClick (View view){
        Intent result = new Intent();
        result.putExtra(MainActivity.EXTRA_VALUES, switch1.isChecked());
        result.putExtra(MainActivity.JAPAN_VALUES, switch2.isChecked());
        result.putExtra(MainActivity.CHINA_VALUES, switch3.isChecked());
        result.putExtra(MainActivity.CULINARY_VALUES, switch4.isChecked());
        result.putExtra(MainActivity.OLD_VALUES, switch5.isChecked());
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_animation_down);
                view.startAnimation(animation);
            } break;
            default: break;
        }
        return false;
    }
}
