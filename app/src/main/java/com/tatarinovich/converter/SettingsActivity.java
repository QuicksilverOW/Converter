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
    Switch switch6;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();

        if (intent.getBooleanExtra(MainActivity.THEME, false)) {
                setTheme(R.style.Dark);
            } else {
                setTheme(R.style.Light);
            }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        switch4 = findViewById(R.id.switch4);
        switch5 = findViewById(R.id.switch5);
        switch6 = findViewById(R.id.switch6);
        button = findViewById(R.id.button);
        button.setOnTouchListener(this);

        switch1.setChecked(intent.getBooleanExtra(MainActivity.EXTRA_VALUES, false));
        switch2.setChecked(intent.getBooleanExtra(MainActivity.JAPAN_VALUES, false));
        switch3.setChecked(intent.getBooleanExtra(MainActivity.CHINA_VALUES, false));
        switch4.setChecked(intent.getBooleanExtra(MainActivity.CULINARY_VALUES, false));
        switch5.setChecked(intent.getBooleanExtra(MainActivity.OLD_VALUES, false));
        switch6.setChecked(intent.getBooleanExtra(MainActivity.THEME, false));
    }
    public void onClick (View view){
        Intent result = new Intent(this, MainActivity.class);
        result.putExtra(MainActivity.EXTRA_VALUES, switch1.isChecked());
        result.putExtra(MainActivity.JAPAN_VALUES, switch2.isChecked());
        result.putExtra(MainActivity.CHINA_VALUES, switch3.isChecked());
        result.putExtra(MainActivity.CULINARY_VALUES, switch4.isChecked());
        result.putExtra(MainActivity.OLD_VALUES, switch5.isChecked());
        result.putExtra(MainActivity.THEME, switch6.isChecked());
        finish();
        startActivity(result);
        overridePendingTransition(R.anim.main_activity_open, R.anim.settings_activity_close);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent result = new Intent(this, MainActivity.class);
        finish();
        startActivity(result);
        overridePendingTransition(R.anim.main_activity_open, R.anim.settings_activity_close);
    }
}
