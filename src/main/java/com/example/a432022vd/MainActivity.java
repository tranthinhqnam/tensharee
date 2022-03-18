package com.example.a432022vd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String BT_SHARE_PREFS = "BT_SHARE_PREFS";
    private static final String CURRENT_COLOR_KEY = "CURRENT_COLOR_KEY";
    private static final String BLACK_KEY = "BLACK_KEY";
    private static final String RED_KEY = "RED_KEY";
    private static final String BLUE_KEY = "BLUE_KEY";
    private static final String GREEN_KEY = "GREEN_KEY";
    private SharedPreferences sharedPref;
    private TextView textView;
    private String currentColorKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onInit();

    }

    private void onInit(){
        sharedPref = getSharedPreferences(BT_SHARE_PREFS,MODE_PRIVATE);
        textView = findViewById(R.id.textView);

        currentColorKey = sharedPref.getString(CURRENT_COLOR_KEY,BT_SHARE_PREFS);
        onBtnColorClick(currentColorKey);

        TextView btnBlack = findViewById(R.id.btnBlack);
        TextView btnRed = findViewById(R.id.btnRed);
        TextView btnBlue = findViewById(R.id.btnBlue);
        TextView btnGreen = findViewById(R.id.btnGreen);

        btnBlack.setOnClickListener(view -> onBtnColorClick(BLACK_KEY));
        btnRed.setOnClickListener(view -> onBtnColorClick(RED_KEY));
        btnBlue.setOnClickListener(view -> onBtnColorClick(BLUE_KEY));
        btnGreen.setOnClickListener(view -> onBtnColorClick(GREEN_KEY));

        TextView btnCount = findViewById(R.id.btnCount);
        btnCount.setOnClickListener(view -> onBtnCountClick());

        TextView btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(view -> onBtnResetClick());

    }

    private void onBtnCountClick(){
        if(currentColorKey.equals(BT_SHARE_PREFS)){
            return;
        }
        int newValue =  Integer.parseInt(textView.getText().toString()) + 1;
        SharedPreferences.Editor editor =   sharedPref.edit();
        editor.putInt(currentColorKey, newValue);
        editor.apply();

        textView.setText(String.valueOf(newValue));
    }

    private void onBtnColorClick(String KEY){
        currentColorKey = KEY;
        int numberInSharedPref = sharedPref.getInt(KEY,0);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(CURRENT_COLOR_KEY, KEY);
        editor.apply();

        textView.setText(String.valueOf(numberInSharedPref));
        textView.setBackgroundColor(getColorId(KEY));
    }

    private int getColorId(String KEY){
        switch (KEY){
            case BLACK_KEY: return getResources().getColor(R.color.black);
            case RED_KEY: return getResources().getColor(R.color.red);
            case BLUE_KEY: return getResources().getColor(R.color.blue);
            case GREEN_KEY: return getResources().getColor(R.color.green);
            default: return getResources().getColor(R.color.white);
        }
    }

    private void onBtnResetClick(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        currentColorKey = BT_SHARE_PREFS;
        textView.setText("0");
        textView.setBackgroundColor(getColorId(currentColorKey));
    }
}