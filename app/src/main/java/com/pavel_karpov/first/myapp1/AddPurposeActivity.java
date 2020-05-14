package com.pavel_karpov.first.myapp1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.UUID;

public class AddPurposeActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private SeekBar seekBar;
    private EditText editNamePurpose;
    private TextView textSeekbar,textPeriodExecution, textCategoryTextView,textView5;
    private Button btnLittle, btnMedium, btnLarge;
    private Toolbar toolbar;
    private ImageView imageCategory;
    String category;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purpose);
        category = getIntent().getStringExtra("category");
        toolbar = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        textCategoryTextView = findViewById(R.id.text_category_textview);
        imageCategory = findViewById(R.id.image_category);
        calendarView = findViewById(R.id.calendar_view);
        btnLittle = findViewById(R.id.btn_little);
        textView5 = findViewById(R.id.textView5);
        textPeriodExecution = findViewById(R.id.period_execution);
        btnMedium = findViewById(R.id.btn_medium);
        btnLarge = findViewById(R.id.btn_large);
        seekBar = findViewById(R.id.seekBar);
        textSeekbar = findViewById(R.id.text_seekbar);
        editNamePurpose = findViewById(R.id.editNamePurpose);
        if (category.equals(getResources().getString(R.string.brightness_life))){
            imageCategory.setImageResource(R.drawable.photo_2020_brightness);
            textCategoryTextView.setText(getText(R.string.brightness_life));
            textCategoryTextView.setTextColor(getResources().getColor(R.color.color_1));
            textView5.setText(getResources().getString(R.string.long_text,"яркость жизни"));
        }
        else if (category.equals(getResources().getString(R.string.self_development))){
            imageCategory.setImageResource(R.drawable.photo_2020_selfdevelopment);
            textCategoryTextView.setText(getText(R.string.self_development));
            textCategoryTextView.setTextColor(getResources().getColor(R.color.color_2));
            textView5.setText(getResources().getString(R.string.long_text,"саморазвитие"));
        }
        else if (category.equals(getResources().getString(R.string.things))){
            imageCategory.setImageResource(R.drawable.photo_2020_things);
            textCategoryTextView.setText(getText(R.string.things));
            textCategoryTextView.setTextColor(getResources().getColor(R.color.color_3));
            textView5.setText(getResources().getString(R.string.long_text,"вещи"));
        }
        else if (category.equals(getResources().getString(R.string.family))){
            imageCategory.setImageResource(R.drawable.photo_2020_family);
            textCategoryTextView.setText(getText(R.string.family));
            textCategoryTextView.setTextColor(getResources().getColor(R.color.color_4));
            textView5.setText(getResources().getString(R.string.long_text,"семья"));
        }
        else if (category.equals(getResources().getString(R.string.medical))){
            imageCategory.setImageResource(R.drawable.photo_2020_medical);
            textCategoryTextView.setText(getText(R.string.medical));
            textCategoryTextView.setTextColor(getResources().getColor(R.color.color_5));
            textView5.setText(getResources().getString(R.string.long_text,"здоровье"));
        }
        else if (category.equals(getResources().getString(R.string.career))){
            imageCategory.setImageResource(R.drawable.photo_2020_career);
            textCategoryTextView.setText(getText(R.string.career));
            textCategoryTextView.setTextColor(getResources().getColor(R.color.color_6));
            textView5.setText(getResources().getString(R.string.long_text,"карьера"));
        }
        else if (category.equals(getResources().getString(R.string.money))){
            imageCategory.setImageResource(R.drawable.photo_2020_money);
            textCategoryTextView.setText(getText(R.string.money));
            textCategoryTextView.setTextColor(getResources().getColor(R.color.color_7));
            textView5.setText(getResources().getString(R.string.long_text,"деньги"));
        }
        else if (category.equals(getResources().getString(R.string.love))){
            imageCategory.setImageResource(R.drawable.photo_2020_love);
            textCategoryTextView.setText(getText(R.string.love));
            textCategoryTextView.setTextColor(getResources().getColor(R.color.color_8));
            textView5.setText(getResources().getString(R.string.long_text,"отношения"));
        }



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress==0){
                    textSeekbar.setText("0.0");
                    btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                    btnLittle.setTextColor(Color.BLACK);
                    btnMedium.setBackground(getDrawable(R.drawable.shape_button));
                    btnMedium.setTextColor(Color.BLACK);
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                }
                if (progress>5 && progress<10){
                    textSeekbar.setText("0.1");
                    btnLittle.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnLittle.setTextColor(Color.WHITE);
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                }
                else if (progress >=10 && progress <20){
                    btnLittle.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnLittle.setTextColor(Color.WHITE);
                    textSeekbar.setText("0.2");
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                }
                else if (progress>=20 && progress <30){
                    btnLittle.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnLittle.setTextColor(Color.WHITE);
                    textSeekbar.setText("0.3");
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);

                }
                else if (progress>=30 && progress <40){
                    btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                    btnLittle.setTextColor(Color.BLACK);
                    btnMedium.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnMedium.setTextColor(Color.WHITE);
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                    textSeekbar.setText("0.4");
                }
                else if (progress>=40 && progress <50){
                    btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                    btnLittle.setTextColor(Color.BLACK);
                    btnMedium.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnMedium.setTextColor(Color.WHITE);
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                    textSeekbar.setText("0.5");
                }
                else if (progress>=50 && progress <60){
                    btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                    btnLittle.setTextColor(Color.BLACK);
                    btnMedium.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnMedium.setTextColor(Color.WHITE);
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                    textSeekbar.setText("0.6");
                }
                else if (progress>=60 && progress <70){
                    btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                    btnLittle.setTextColor(Color.BLACK);
                    btnMedium.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnMedium.setTextColor(Color.WHITE);
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                    textSeekbar.setText("0.7");
                }
                else if (progress>=70 && progress <80){
                    btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                    btnLittle.setTextColor(Color.BLACK);
                    btnMedium.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnMedium.setTextColor(Color.WHITE);
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                    textSeekbar.setText("0.8");
                }
                else if (progress>=90 && progress <100){
                    btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                    btnLittle.setTextColor(Color.BLACK);
                    btnMedium.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnMedium.setTextColor(Color.WHITE);
                    btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                    btnLarge.setTextColor(Color.BLACK);
                    textSeekbar.setText("0.9");
                }
                else if (progress==100){
                    btnLarge.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnLarge.setTextColor(Color.WHITE);
                    btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                    btnLittle.setTextColor(Color.BLACK);
                    btnMedium.setBackground(getDrawable(R.drawable.shape_button));
                    btnMedium.setTextColor(Color.BLACK);
                    btnLarge.setBackgroundColor(getResources().getColor(R.color.peach));
                    btnLarge.setTextColor(Color.WHITE);
                    textSeekbar.setText("1");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int i = month+1;
                textPeriodExecution.setText("" + dayOfMonth + "." + i + "." +year);

            }
        });
        btnLittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLittle.setBackgroundColor(getResources().getColor(R.color.peach));
                btnLittle.setTextColor(Color.WHITE);
                btnMedium.setBackground(getDrawable(R.drawable.shape_button));
                btnMedium.setTextColor(Color.BLACK);
                btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                btnLarge.setTextColor(Color.BLACK);
                seekBar.setProgress(5);
                textSeekbar.setText("0.2");
            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                btnLittle.setTextColor(Color.BLACK);
                btnLarge.setBackground(getDrawable(R.drawable.shape_button));
                btnLarge.setTextColor(Color.BLACK);
                btnMedium.setBackgroundColor(getResources().getColor(R.color.peach));
                btnMedium.setTextColor(Color.WHITE);
                seekBar.setProgress(55);
                textSeekbar.setText("0.5");
            }
        });
        btnLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLittle.setBackground(getDrawable(R.drawable.shape_button));
                btnLittle.setTextColor(Color.BLACK);
                btnMedium.setBackground(getDrawable(R.drawable.shape_button));
                btnMedium.setTextColor(Color.BLACK);
                btnLarge.setBackgroundColor(getResources().getColor(R.color.peach));
                btnLarge.setTextColor(Color.WHITE);
                seekBar.setProgress(100);
                textSeekbar.setText("1");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_add_menu:
                if (category.equals(getResources().getString(R.string.brightness_life))) {
                    String mPurpose = editNamePurpose.getText().toString();
                    if (textPeriodExecution.getText().toString().equals(getResources().getString(R.string.period_of_execution))){
                        Toast.makeText(AddPurposeActivity.this,"Укажите срок выполнения",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mDate = "23:59:" + textPeriodExecution.getText().toString();
                        String mRating = textSeekbar.getText().toString();
                        String mCategory = textCategoryTextView.getText().toString();
                        UUID mUUID = UUID.randomUUID();
                        int isCompleted = 0;
                        int flagCompleted = 0;
                        Purpose purpose = new Purpose(mPurpose, mDate, mRating, mCategory, mUUID.toString(),isCompleted,flagCompleted,"","");
                        PurposeLab.getInstance(this).addPurpose(purpose);
                        finish();
                    }
                }
               else if (category.equals(getResources().getString(R.string.self_development))) {
                    String mPurpose = editNamePurpose.getText().toString();
                    if (textPeriodExecution.getText().toString().equals(getResources().getString(R.string.period_of_execution))){
                        Toast.makeText(AddPurposeActivity.this,"Укажите срок выполнения",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mDate = "23:59:" + textPeriodExecution.getText().toString();
                        String mRating = textSeekbar.getText().toString();
                        String mCategory = textCategoryTextView.getText().toString();
                        UUID mUUID = UUID.randomUUID();
                        int isCompleted = 0;
                        int flagCompleted = 0;
                        Purpose purpose = new Purpose(mPurpose, mDate, mRating, mCategory, mUUID.toString(),isCompleted,flagCompleted,"","");
                        PurposeLab.getInstance(this).addPurpose(purpose);
                        finish();
                    }
                }
                else if (category.equals(getResources().getString(R.string.things))) {
                    String mPurpose = editNamePurpose.getText().toString();
                    if (textPeriodExecution.getText().toString().equals(getResources().getString(R.string.period_of_execution))){
                        Toast.makeText(AddPurposeActivity.this,"Укажите срок выполнения",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mDate = "23:59:" + textPeriodExecution.getText().toString();
                        String mRating = textSeekbar.getText().toString();
                        String mCategory = textCategoryTextView.getText().toString();
                        UUID mUUID = UUID.randomUUID();
                        int isCompleted = 0;
                        int flagCompleted = 0;
                        Purpose purpose = new Purpose(mPurpose, mDate, mRating, mCategory, mUUID.toString(),isCompleted,flagCompleted,"","");
                        PurposeLab.getInstance(this).addPurpose(purpose);
                        finish();
                    }
                }
                else if (category.equals(getResources().getString(R.string.family))) {
                    String mPurpose = editNamePurpose.getText().toString();
                    if (textPeriodExecution.getText().toString().equals(getResources().getString(R.string.period_of_execution))){
                        Toast.makeText(AddPurposeActivity.this,"Укажите срок выполнения",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mDate = "23:59:" + textPeriodExecution.getText().toString();
                        String mRating = textSeekbar.getText().toString();
                        String mCategory = textCategoryTextView.getText().toString();
                        UUID mUUID = UUID.randomUUID();
                        int isCompleted = 0;
                        int flagCompleted = 0;
                        Purpose purpose = new Purpose(mPurpose, mDate, mRating, mCategory, mUUID.toString(),isCompleted,flagCompleted,"","");
                        PurposeLab.getInstance(this).addPurpose(purpose);
                        finish();
                    }
                }
                else if (category.equals(getResources().getString(R.string.medical))) {
                    String mPurpose = editNamePurpose.getText().toString();
                    if (textPeriodExecution.getText().toString().equals(getResources().getString(R.string.period_of_execution))){
                        Toast.makeText(AddPurposeActivity.this,"Укажите срок выполнения",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mDate = "23:59:" + textPeriodExecution.getText().toString();
                        String mRating = textSeekbar.getText().toString();
                        String mCategory = textCategoryTextView.getText().toString();
                        UUID mUUID = UUID.randomUUID();
                        int isCompleted = 0;
                        int flagCompleted = 0;
                        Purpose purpose = new Purpose(mPurpose, mDate, mRating, mCategory, mUUID.toString(),isCompleted,flagCompleted,"","");
                        PurposeLab.getInstance(this).addPurpose(purpose);
                        finish();
                    }
                }
                else if (category.equals(getResources().getString(R.string.career))) {
                    String mPurpose = editNamePurpose.getText().toString();
                    if (textPeriodExecution.getText().toString().equals(getResources().getString(R.string.period_of_execution))){
                        Toast.makeText(AddPurposeActivity.this,"Укажите срок выполнения",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mDate = "23:59:" + textPeriodExecution.getText().toString();
                        String mRating = textSeekbar.getText().toString();
                        String mCategory = textCategoryTextView.getText().toString();
                        UUID mUUID = UUID.randomUUID();
                        int isCompleted = 0;
                        int flagCompleted = 0;
                        Purpose purpose = new Purpose(mPurpose, mDate, mRating, mCategory, mUUID.toString(),isCompleted,flagCompleted,"","");
                        PurposeLab.getInstance(this).addPurpose(purpose);
                        finish();
                    }
                }
                else if (category.equals(getResources().getString(R.string.money))) {
                    String mPurpose = editNamePurpose.getText().toString();
                    if (textPeriodExecution.getText().toString().equals(getResources().getString(R.string.period_of_execution))){
                        Toast.makeText(AddPurposeActivity.this,"Укажите срок выполнения",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mDate = "23:59:" + textPeriodExecution.getText().toString();
                        String mRating = textSeekbar.getText().toString();
                        String mCategory = textCategoryTextView.getText().toString();
                        UUID mUUID = UUID.randomUUID();
                        int isCompleted = 0;
                        int flagCompleted = 0;
                        Purpose purpose = new Purpose(mPurpose, mDate, mRating, mCategory, mUUID.toString(),isCompleted,flagCompleted,"","");
                        PurposeLab.getInstance(this).addPurpose(purpose);
                        finish();
                    }
                }
                else if (category.equals(getResources().getString(R.string.love))) {
                    String mPurpose = editNamePurpose.getText().toString();
                    if (textPeriodExecution.getText().toString().equals(getResources().getString(R.string.period_of_execution))){
                        Toast.makeText(AddPurposeActivity.this,"Укажите срок выполнения",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mDate = "23:59:" + textPeriodExecution.getText().toString();
                        String mRating = textSeekbar.getText().toString();
                        String mCategory = textCategoryTextView.getText().toString();
                        UUID mUUID = UUID.randomUUID();
                        int isCompleted = 0;
                        int flagCompleted = 0;
                        Purpose purpose = new Purpose(mPurpose, mDate, mRating, mCategory, mUUID.toString(),isCompleted,flagCompleted,"","");
                        PurposeLab.getInstance(this).addPurpose(purpose);
                        finish();
                    }
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
