package com.pavel_karpov.first.myapp1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class AddNote extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;
    private ImageView imageAdd;
    private ImageButton imageMic;
    private EditText editAddNote;
    private UUID uuid;
    private String date;
    private MediaRecorder mRecoreder;
    private String mAudioPath=null;
    Display display;
    DisplayMetrics displayMetrics;
    WindowManager windowManager;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = Objects.requireNonNull(windowManager).getDefaultDisplay();
        displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy");
        date =  simpleDateFormat.format(new Date());
        toolbar = findViewById(R.id.toolbar_add);
        editAddNote = findViewById(R.id.edit_add_note);
        editAddNote.setFocusableInTouchMode(true);
        editAddNote.requestFocus();
        editAddNote.setHint(R.string.text_note);

        final int pos = getIntent().getIntExtra("position",-1);
        if (pos!=-1){
            String text = getIntent().getStringExtra("text");
            editAddNote.setText(text);
            date = getIntent().getStringExtra("date");
            uuid =(UUID)getIntent().getSerializableExtra("uuid");
        }
        else {uuid =(UUID)getIntent().getSerializableExtra("uuid");}

        setSupportActionBar(toolbar);
        textView = toolbar.findViewById(R.id.text_toolbar_add);
        textView.setText(R.string.new_note);
        if(displayMetrics.widthPixels>=1200){
            textView.setTextSize(26);
        }
        else {textView.setTextSize(20);}
        textView.append("\n"+date);
        imageAdd = findViewById(R.id.image_add_note_toolbar);
        imageMic = findViewById(R.id.image_mic);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text =editAddNote.getText().toString();
                if (!text.isEmpty() && pos==-1){
                    NoteLab.getInstance(AddNote.this).addTaskNote(new Note(text,date,uuid.toString()));
                    finish();
                }
            }
        });
        imageMic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_BUTTON_PRESS:

                        break;
                    case MotionEvent.ACTION_UP:
                       mRecoreder.stop();
                       NoteLab.getInstance(AddNote.this).addTaskNote(new Note("text.3gp"+editAddNote.getText().toString(),date,mAudioPath));
                        finish();
                        break;
                        case MotionEvent.ACTION_DOWN:
                            if(checkPermissions()){
                                @SuppressLint("SimpleDateFormat") String string = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
                                String audioString = "audio "+ string + "." + ".3gp";
                                File dir = getFilesDir();
                                File audio = new File(dir,audioString);
                                mAudioPath = audio.getAbsolutePath();
                                MediaRecorderReady();
                                try {
                                    mRecoreder.prepare();
                                    mRecoreder.start();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else {requestPermissions();}
                            break;
                }
                return false;
            }
        });

        if (pos!=-1){
            imageAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text =editAddNote.getText().toString();
                    if (!text.isEmpty() ) {
                        NoteLab.getInstance(AddNote.this).updateTaskNote(new Note(text, date, uuid.toString()));
                        finish();
                    }
                }
            });
        }

    }

    public boolean checkPermissions(){
        int res = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int res1 = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.RECORD_AUDIO);
        return res== PackageManager.PERMISSION_GRANTED && res1 == PackageManager.PERMISSION_GRANTED;
    }
    public void requestPermissions(){
        ActivityCompat.requestPermissions(AddNote.this,new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }
    public void MediaRecorderReady(){
        mRecoreder = new MediaRecorder();
        mRecoreder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecoreder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecoreder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecoreder.setOutputFile(mAudioPath);
    }

}
