package com.pavel_karpov.first.myapp1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimActivity extends AppCompatActivity {
    protected int color;
    protected String level;
    private SeekBar seekBar;
    private Button imageCat;
    private ImageView imageCategory;
    private TextView textCategoryLife, textSeekbarProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        seekBar = findViewById(R.id.seekbar_category);
        imageCat = findViewById(R.id.imageCat);
        textSeekbarProgress = findViewById(R.id.text_seekbar_progress);
        imageCategory = findViewById(R.id.image_hith_category);
        textCategoryLife = findViewById(R.id.text_category_life);
        color =  getIntent().getIntExtra("color",-1);
        level = getIntent().getStringExtra("level");
        if (color == R.color.color_1){
            imageCat.setBackground(getResources().getDrawable(R.drawable.btn_conf_selector));
            imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.photo_2020_brightness));
            textCategoryLife.setText(R.string.brightness_life);
            textCategoryLife.setTextColor(getResources().getColor(R.color.color_1));
            textSeekbarProgress.setTextColor(getResources().getColor(R.color.color_1));
            textSeekbarProgress.setText(level);
            seekBar.setProgress((int)Float.parseFloat(level)*10);
            imageCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(AnimActivity.this).edit().putString("brightness",textSeekbarProgress.getText().toString()).apply();
                    finish();
                }
            });
        }
        else if (color == R.color.color_2){
            imageCat.setBackground(getResources().getDrawable(R.drawable.btn_conf1_selector));
            imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.photo_2020_selfdevelopment));
            textCategoryLife.setText(R.string.self_development);
            textCategoryLife.setTextColor(getResources().getColor(R.color.color_2));
            textSeekbarProgress.setTextColor(getResources().getColor(R.color.color_2));
            textSeekbarProgress.setText(level);
            seekBar.setProgress((int)Float.parseFloat(level)*10);
            imageCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(AnimActivity.this).edit().putString("selfdevelopment",textSeekbarProgress.getText().toString()).apply();
                    finish();
                }
            });
        }
        else if (color == R.color.color_3){
            imageCat.setBackground(getResources().getDrawable(R.drawable.btn_conf2_selector));
            imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.photo_2020_things));
            textCategoryLife.setText(R.string.things);
            textCategoryLife.setTextColor(getResources().getColor(R.color.color_3));
            textSeekbarProgress.setTextColor(getResources().getColor(R.color.color_3));
            textSeekbarProgress.setText(level);
            seekBar.setProgress((int)Float.parseFloat(level)*10);
            imageCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(AnimActivity.this).edit().putString("things",textSeekbarProgress.getText().toString()).apply();
                    finish();
                }
            });
        }
        else if (color == R.color.color_4){
            imageCat.setBackground(getResources().getDrawable(R.drawable.btn_conf3_selector));
            imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.photo_2020_family));
            textCategoryLife.setText(R.string.family);
            textCategoryLife.setTextColor(getResources().getColor(R.color.color_4));
            textSeekbarProgress.setTextColor(getResources().getColor(R.color.color_4));
            textSeekbarProgress.setText(level);
            seekBar.setProgress((int)Float.parseFloat(level)*10);
            imageCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(AnimActivity.this).edit().putString("family",textSeekbarProgress.getText().toString()).apply();
                    finish();
                }
            });
        }
        else if (color == R.color.color_5){
            imageCat.setBackground(getResources().getDrawable(R.drawable.btn_conf4_selector));
            imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.photo_2020_medical));
            textCategoryLife.setText(R.string.medical);
            textCategoryLife.setTextColor(getResources().getColor(R.color.color_5));
            textSeekbarProgress.setTextColor(getResources().getColor(R.color.color_5));
            textSeekbarProgress.setText(level);
            seekBar.setProgress((int)Float.parseFloat(level)*10);
            imageCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(AnimActivity.this).edit().putString("medical",textSeekbarProgress.getText().toString()).apply();
                    finish();
                }
            });
        }
        else if (color == R.color.color_6){
            imageCat.setBackground(getResources().getDrawable(R.drawable.btn_conf5_selector));
            imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.photo_2020_career));
            textCategoryLife.setText(R.string.career);
            textCategoryLife.setTextColor(getResources().getColor(R.color.color_6));
            textSeekbarProgress.setTextColor(getResources().getColor(R.color.color_6));
            textSeekbarProgress.setText(level);
            seekBar.setProgress((int)Float.parseFloat(level)*10);
            imageCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(AnimActivity.this).edit().putString("career",textSeekbarProgress.getText().toString()).apply();
                    finish();
                }
            });
        }
        else if (color == R.color.color_7){
            imageCat.setBackground(getResources().getDrawable(R.drawable.btn_conf6_selector));
            imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.photo_2020_money));
            textCategoryLife.setText(R.string.money);
            textCategoryLife.setTextColor(getResources().getColor(R.color.color_7));
            textSeekbarProgress.setTextColor(getResources().getColor(R.color.color_7));
            textSeekbarProgress.setText(level);
            seekBar.setProgress((int)Float.parseFloat(level)*10);
            imageCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(AnimActivity.this).edit().putString("money",textSeekbarProgress.getText().toString()).apply();
                    finish();
                }
            });
        }
        else if (color == R.color.color_8){
            imageCat.setBackground(getResources().getDrawable(R.drawable.btn_conf7_selector));
            imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.photo_2020_love));
            textCategoryLife.setText(R.string.love);
            textCategoryLife.setTextColor(getResources().getColor(R.color.color_8));
            textSeekbarProgress.setTextColor(getResources().getColor(R.color.color_8));
            textSeekbarProgress.setText(level);
            seekBar.setProgress((int)Float.parseFloat(level)*10);
            imageCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(AnimActivity.this).edit().putString("love",textSeekbarProgress.getText().toString()).apply();
                    finish();
                }
            });
        }
    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (progress==0){
                textSeekbarProgress.setText("0");
            }
            if (progress>5 && progress<10){
                textSeekbarProgress.setText("0.5");
            }
            else if (progress >=10 && progress <15){
                textSeekbarProgress.setText("1");

            }
            else if (progress>=15 && progress <20){
                textSeekbarProgress.setText("1.5");
            }
            else if (progress>=20 && progress <25){
                textSeekbarProgress.setText("2");
            }
            else if (progress>=25 && progress <30){
                textSeekbarProgress.setText("2.5");
            }
            else if (progress>=30 && progress <35){
                textSeekbarProgress.setText("3");
            }
            else if (progress>=35 && progress <40){
                textSeekbarProgress.setText("3.5");
            }
            else if (progress>=40 && progress <45){
                textSeekbarProgress.setText("4");
            }
            else if (progress>=45 && progress <50){

                textSeekbarProgress.setText("4.5");
            }
            else if (progress>=50 && progress <55){
                textSeekbarProgress.setText("5");
            }
            else if (progress>=55 && progress <60){
                textSeekbarProgress.setText("5.5");
            }
            else if (progress>=60 && progress <65){
                textSeekbarProgress.setText("6");
            }
            else if (progress>=65 && progress <70){
                textSeekbarProgress.setText("6.5");
            }
            else if (progress>=70 && progress <75){
                textSeekbarProgress.setText("7");
            }
            else if (progress>=75 && progress <80){
                textSeekbarProgress.setText("7.5");
            }
            else if (progress>=80 && progress <85){
                textSeekbarProgress.setText("8");
            }
            else if (progress>=85 && progress <90){
                textSeekbarProgress.setText("8.5");
            }
            else if (progress>=90 && progress <95){
                textSeekbarProgress.setText("9");
            }
            else if (progress>=95 && progress <99){
                textSeekbarProgress.setText("9.5");
            }
            else if (progress==100){
                textSeekbarProgress.setText("10");
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    });

    }

}
