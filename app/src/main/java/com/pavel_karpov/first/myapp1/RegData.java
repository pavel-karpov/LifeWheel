package com.pavel_karpov.first.myapp1;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegData extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView, textProfile, textDeleteProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_data);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textProfile = findViewById(R.id.text_profile);
        textDeleteProfile = findViewById(R.id.text_delete_profile);
        textView = toolbar.findViewById(R.id.text_toolbar);
        textView.setTextSize(20);
        textView.setText("Настройки");
        String username = PreferenceManager.getDefaultSharedPreferences(RegData.this).getString("username",null);
        textProfile.setText(username);
        textDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurposeLab.getInstance(RegData.this).deleteAll();
                TasksLab.getInstance(RegData.this).deleteAllTasks();
                NoteLab.getInstance(RegData.this).deleteAllNotes();
                PreferenceManager.getDefaultSharedPreferences(RegData.this).edit().remove("username").apply();
                PreferenceManager.getDefaultSharedPreferences(RegData.this).edit().remove("passwd").apply();
                startActivity(new Intent(RegData.this,RegisterActivity.class));

            }
        });

    }
}
