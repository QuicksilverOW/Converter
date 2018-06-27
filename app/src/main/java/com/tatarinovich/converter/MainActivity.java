package com.tatarinovich.converter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.PopupMenu;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements OnLongClickListener, OnTouchListener{

    private final static double

            COEFFICIENT_METR_3 = 1000.0,
            COEFFICIENT_DECIMETR_3 = 1.0,
            COEFFICIENT_SANTIMETR_3 = 0.001,
            COEFFICIENT_MILLIMETR_3 = 0.000001,

            COEFFICIENT_BARREL = 159.0,
            COEFFICIENT_YARD_3 = 76.46,
            COEFFICIENT_FUT_3 = 28.32,
            COEFFICIENT_GALLON = 4.0,
            COEFFICIENT_DUYM_3 = 0.0164,

            COEFFICIENT_KOKU = 180.0,
            COEFFICIENT_TO = 18.0,
            COEFFICIENT_SHO = 1.8,
            COEFFICIENT_GO = 0.18,
            COEFFICIENT_SHAKU = 0.018,
            COEFFICIENT_SAY = 0.0018,

            COEFFICIENT_DAN = 100.0,
            COEFFICIENT_DOU = 10.0,
            COEFFICIENT_SHEN = 1.0,
            COEFFICIENT_GE = 0.1,
            COEFFICIENT_SHAO = 0.01,
            COEFFICIENT_CUO = 0.001,

            COEFFICIENT_STAKAN = 0.24,
            COEFFICIENT_STOL_LOZHKA = 0.015,
            COEFFICIENT_CHAY_LOZHKA = 0.005,

            COEFFICIENT_VOZ = 2000.0,
            COEFFICIENT_KORZINA = 25.0,
            COEFFICIENT_VEDRO = 20.0,
            COEFFICIENT_SKORLUPA = 1.0,
            COEFFICIENT_LADON = 0.128,
            COEFFICIENT_KULAK = 0.032,
            COEFFICIENT_SHEPOTKA = 0.008;

    private final static int SETTINGS_QUERY = 1;

    final static String
            EXTRA_VALUES = "extra",
            JAPAN_VALUES = "japan",
            CHINA_VALUES = "china",
            CULINARY_VALUES = "culinary",
            OLD_VALUES = "old",
            TEXT_VIEW_3_VALUE = "textView3",
            TEXT_VIEW_4_VALUE = "textView4",
            BUTTON_1_TEXT = "button1",
            BUTTON_2_TEXT = "button2",
            FIRST_COEFF = "firstCoeff",
            SECOND_COEFF = "secondCoeff",
            POINT_BUTTON_PRESSED = "pointButtonPressed",
            APP_SETTINGS = "Converter settings";

    private static boolean
            EXTRA, JAPAN, CHINA, CULINARY, OLD,
            pointButtonPressed = false,
            dialogCalled = false;

    private static double
            firstButtonCoeff,
            secondButtonCoeff,
            fullCoeff;

    TextView textView3, textView4;
    Button  button_clear,
            button_1,
            button_2,
            button_3,
            button_4,
            button_5,
            button_6,
            button_7,
            button_8,
            button_9,
            button_0,
            button_point,
            button1,
            button2;
    private static DecimalFormat decimalFormat;
    SharedPreferences sharedPreferences;


    private void selectButtonPressed (View view, double coeff){
        if (view.getId() == R.id.button1){
            firstButtonCoeff = coeff;
        } else {
            secondButtonCoeff = coeff;
        } calculateFullCoeff();
    }

    public void openPopupMenu (View view){
        Button button = (Button)view;
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popupmenu);

        if (!EXTRA) popupMenu.getMenu().removeGroup(R.id.menuGroupExtra);
        if (!JAPAN) popupMenu.getMenu().removeGroup(R.id.menuGroupJapan);
        if (!CHINA) popupMenu.getMenu().removeGroup(R.id.menuGroupChina);
        if (!CULINARY) popupMenu.getMenu().removeGroup(R.id.menuGroupCulinary);
        if (!OLD) popupMenu.getMenu().removeGroup(R.id.menuGroupOld);

        popupMenu.setOnMenuItemClickListener((MenuItem item) -> {
            switch (item.getItemId()){
                case R.id.menu1: {
                    button.setText(R.string.menu1);
                    selectButtonPressed(button, COEFFICIENT_METR_3);
                } return true;
                case R.id.menu2: {
                    button.setText(R.string.menu2);
                    selectButtonPressed(button, COEFFICIENT_DECIMETR_3);
                } return true;
                case R.id.menu3: {
                    button.setText(R.string.menu3);
                    selectButtonPressed(button, COEFFICIENT_SANTIMETR_3);
                } return true;
                case R.id.menu4: {
                    button.setText(R.string.menu4);
                    selectButtonPressed(button, COEFFICIENT_MILLIMETR_3);
                } return true;
                case R.id.menu5: {
                    button.setText(R.string.menu5);
                    selectButtonPressed(button, COEFFICIENT_BARREL);
                } return true;
                case R.id.menu6: {
                    button.setText(R.string.menu6);
                    selectButtonPressed(button, COEFFICIENT_YARD_3);
                } return true;
                case R.id.menu7: {
                    button.setText(R.string.menu7);
                    selectButtonPressed(button, COEFFICIENT_FUT_3);
                } return true;
                case R.id.menu8: {
                    button.setText(R.string.menu8);
                    selectButtonPressed(button, COEFFICIENT_GALLON);
                } return true;
                case R.id.menu9: {
                    button.setText(R.string.menu9);
                    selectButtonPressed(button, COEFFICIENT_DUYM_3);
                } return true;
                case R.id.menu10: {
                    button.setText(R.string.menu10);
                    selectButtonPressed(button, COEFFICIENT_KOKU);
                } return true;
                case R.id.menu11: {
                    button.setText(R.string.menu11);
                    selectButtonPressed(button, COEFFICIENT_TO);
                } return true;
                case R.id.menu12: {
                    button.setText(R.string.menu12);
                    selectButtonPressed(button, COEFFICIENT_SHO);
                } return true;
                case R.id.menu13: {
                    button.setText(R.string.menu13);
                    selectButtonPressed(button, COEFFICIENT_GO);
                } return true;
                case R.id.menu14: {
                    button.setText(R.string.menu14);
                    selectButtonPressed(button, COEFFICIENT_SHAKU);
                } return true;
                case R.id.menu15: {
                    button.setText(R.string.menu15);
                    selectButtonPressed(button, COEFFICIENT_SAY);
                } return true;
                case R.id.menu16: {
                    button.setText(R.string.menu16);
                    selectButtonPressed(button, COEFFICIENT_DAN);
                } return true;
                case R.id.menu17: {
                    button.setText(R.string.menu17);
                    selectButtonPressed(button, COEFFICIENT_DOU);
                } return true;
                case R.id.menu18: {
                    button.setText(R.string.menu18);
                    selectButtonPressed(button, COEFFICIENT_SHEN);
                } return true;
                case R.id.menu19: {
                    button.setText(R.string.menu19);
                    selectButtonPressed(button, COEFFICIENT_GE);
                } return true;
                case R.id.menu20: {
                    button.setText(R.string.menu20);
                    selectButtonPressed(button, COEFFICIENT_SHAO);
                } return true;
                case R.id.menu21: {
                    button.setText(R.string.menu21);
                    selectButtonPressed(button, COEFFICIENT_CUO);
                } return true;
                case R.id.menu22: {
                    button.setText(R.string.menu22);
                    selectButtonPressed(button, COEFFICIENT_STAKAN);
                } return true;
                case R.id.menu23: {
                    button.setText(R.string.menu23);
                    selectButtonPressed(button, COEFFICIENT_STOL_LOZHKA);
                } return true;
                case R.id.menu24: {
                    button.setText(R.string.menu24);
                    selectButtonPressed(button, COEFFICIENT_CHAY_LOZHKA);
                } return true;
                case R.id.menu25: {
                    button.setText(R.string.menu25);
                    selectButtonPressed(button, COEFFICIENT_VOZ);
                } return true;
                case R.id.menu26: {
                    button.setText(R.string.menu26);
                    selectButtonPressed(button, COEFFICIENT_KORZINA);
                } return true;
                case R.id.menu27: {
                    button.setText(R.string.menu27);
                    selectButtonPressed(button, COEFFICIENT_VEDRO);
                } return true;
                case R.id.menu28: {
                    button.setText(R.string.menu28);
                    selectButtonPressed(button, COEFFICIENT_SKORLUPA);
                } return true;
                case R.id.menu29: {
                    button.setText(R.string.menu29);
                    selectButtonPressed(button, COEFFICIENT_LADON);
                } return true;
                case R.id.menu30: {
                    button.setText(R.string.menu30);
                    selectButtonPressed(button, COEFFICIENT_KULAK);
                } return true;
                case R.id.menu31: {
                    button.setText(R.string.menu31);
                    selectButtonPressed(button, COEFFICIENT_SHEPOTKA);
                } return true;
                default: return false;
            }
        });

        popupMenu.show();
    }

    private void calculateFullCoeff() {
        if ((firstButtonCoeff != 0)&&(secondButtonCoeff != 0)){
            fullCoeff = firstButtonCoeff/secondButtonCoeff;
            calculateResult();
        }
    }

    private void calculateResult(){
        String st = textView3.getText().toString();
        if (st.charAt(st.length() - 1) != '.') {
            double firstField = Double.parseDouble(textView3.getText().toString());
            double secondfield = firstField * fullCoeff;
            if (decimalFormat.format(secondfield).length() <= 16) {
                textView4.setText(decimalFormat.format(secondfield));
            } else {
                if (!dialogCalled) showDialog(getString(R.string.longResult));
            }
        }
    }

    private void changeFirstField (char d){
        String string;
        if (!textView3.getText().toString().equals("0")){
        string = textView3.getText().toString() + d;
        } else if (d == '.') {
            string = "0.";
        } else {
            string = "" + d;
        }
        if (string.length() <= 16) {
            textView3.setText(string);
        } else {
            if (!dialogCalled) showDialog(getString(R.string.longInput));
        }
        if (d != '.'){
            calculateFullCoeff();
        }
    }

    public void onClick (View view){
        switch (view.getId()){
            case R.id.button_1: {
                changeFirstField('1');
            } break;
            case R.id.button_2: {
                changeFirstField('2');
            } break;
            case R.id.button_3: {
                changeFirstField('3');
            } break;
            case R.id.button_4: {
                changeFirstField('4');
            } break;
            case R.id.button_5: {
                changeFirstField('5');
            } break;
            case R.id.button_6: {
                changeFirstField('6');
            } break;
            case R.id.button_7: {
                changeFirstField('7');
            } break;
            case R.id.button_8: {
                changeFirstField('8');
            } break;
            case R.id.button_9: {
                changeFirstField('9');
            } break;
            case R.id.button_0: {
                if (!textView3.getText().toString().equals("0")) {
                    changeFirstField('0');
                }
            } break;
            case R.id.button_point: {
                if (!pointButtonPressed) {
                    pointButtonPressed = true;
                    changeFirstField('.');
                }
            } break;
            case R.id.button_clear: {
                String string = textView3.getText().toString();
                if (!string.equals("0")){
                    if (string.length() > 1){
                        if (string.charAt(string.length() - 1) == '.'){
                            string = string.substring(0, string.length() - 1);
                            pointButtonPressed = false;
                            textView3.setText(string);
                            calculateResult();
                        } else {
                            string = string.substring(0, string.length() - 1);
                            textView3.setText(string);
                            if (string.charAt(string.length() - 1) != '.'){
                                calculateResult();
                            } else {
                                String preResult = string + '0';
                                textView3.setText(preResult);
                                calculateResult();
                                textView3.setText(string);
                            }
                        }
                    } else {
                        pointButtonPressed = false;
                        textView3.setText(R.string.empty);
                        textView4.setText(R.string.empty);
                    }
                }
            } break;
            default: break;
        }
    }

    private void showDialog (String string){
        dialogCalled = true;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(string);
        alertDialog.setNegativeButton(R.string.clear, (DialogInterface dialog, int which) -> {
            pointButtonPressed = false;
            textView3.setText(R.string.empty);
            textView4.setText(R.string.empty);
            dialogCalled = false;
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    /*    sharedPreferences = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        EXTRA = sharedPreferences.getBoolean(EXTRA_VALUES, false);
        JAPAN = sharedPreferences.getBoolean(JAPAN_VALUES, false);
        CHINA = sharedPreferences.getBoolean(CHINA_VALUES, false);
        CULINARY = sharedPreferences.getBoolean(CULINARY_VALUES, false);
        OLD = sharedPreferences.getBoolean(OLD_VALUES, false);
        pointButtonPressed = sharedPreferences.getBoolean(POINT_BUTTON_PRESSED, false);
        firstButtonCoeff = Double.parseDouble(sharedPreferences.getString(FIRST_COEFF, "0"));
        secondButtonCoeff = Double.parseDouble(sharedPreferences.getString(SECOND_COEFF, "0"));
        String stringTV3 = sharedPreferences.getString(TEXT_VIEW_3_VALUE, getString(R.string.empty));
        String stringTV4 = sharedPreferences.getString(TEXT_VIEW_4_VALUE, getString(R.string.empty));
        String stringB1 = sharedPreferences.getString(BUTTON_1_TEXT, getString(R.string.no_select));
        String stringB2 = sharedPreferences.getString(BUTTON_2_TEXT, getString(R.string.no_select)); */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_point = findViewById(R.id.button_point);
        button_clear = findViewById(R.id.button_clear);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button_clear.setOnLongClickListener(this);
        button_clear.setOnTouchListener(this);
        button_point.setOnTouchListener(this);
        button_0.setOnTouchListener(this);
        button_1.setOnTouchListener(this);
        button_2.setOnTouchListener(this);
        button_3.setOnTouchListener(this);
        button_4.setOnTouchListener(this);
        button_5.setOnTouchListener(this);
        button_6.setOnTouchListener(this);
        button_7.setOnTouchListener(this);
        button_8.setOnTouchListener(this);
        button_9.setOnTouchListener(this);
        button1.setOnTouchListener(this);
        button2.setOnTouchListener(this);

     /*   textView3.setText(stringTV3);
        textView4.setText(stringTV4);
        button1.setText(stringB1);
        button2.setText(stringB2);  */

        decimalFormat = new DecimalFormat("#.###########");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SETTINGS_QUERY: {
                switch (resultCode){
                    case Activity.RESULT_OK: {
                        EXTRA = data.getBooleanExtra(EXTRA_VALUES, EXTRA);
                        JAPAN = data.getBooleanExtra(JAPAN_VALUES, JAPAN);
                        CHINA = data.getBooleanExtra(CHINA_VALUES, CHINA);
                        CULINARY = data.getBooleanExtra(CULINARY_VALUES, CULINARY);
                        OLD = data.getBooleanExtra(OLD_VALUES, OLD);
                    } break;
                    case Activity.RESULT_CANCELED: {} break;
                    default: break;
                }
            } break;
            default: break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(1, 1, 1, R.string.settings_label);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 1: {
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.putExtra(EXTRA_VALUES, EXTRA);
                intent.putExtra(JAPAN_VALUES, JAPAN);
                intent.putExtra(CHINA_VALUES, CHINA);
                intent.putExtra(CULINARY_VALUES, CULINARY);
                intent.putExtra(OLD_VALUES, OLD);
                startActivityForResult(intent, SETTINGS_QUERY);
            } break;
            default: break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()){
            case R.id.button_clear: {
                pointButtonPressed = false;
                textView3.setText(R.string.empty);
                textView4.setText(R.string.empty);
            } return true;
            default: return false;
        }
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

  /*  @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EXTRA_VALUES, EXTRA);
        editor.putBoolean(JAPAN_VALUES, JAPAN);
        editor.putBoolean(CHINA_VALUES, CHINA);
        editor.putBoolean(CULINARY_VALUES, CULINARY);
        editor.putBoolean(OLD_VALUES, OLD);
        editor.putBoolean(POINT_BUTTON_PRESSED, pointButtonPressed);
        editor.putString(TEXT_VIEW_3_VALUE, textView3.getText().toString());
        editor.putString(TEXT_VIEW_4_VALUE, textView4.getText().toString());
        editor.putString(BUTTON_1_TEXT, button1.getText().toString());
        editor.putString(BUTTON_2_TEXT, button2.getText().toString());
        editor.putString(FIRST_COEFF, decimalFormat.format(firstButtonCoeff));
        editor.putString(SECOND_COEFF, decimalFormat.format(secondButtonCoeff));
        editor.apply();
        super.onDestroy();
    } */
}
