package com.pavel_karpov.first.myapp1;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

  private EditText editUsername, editPasswd;
  private Button imageReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editUsername = findViewById(R.id.edit_text_username);
        editPasswd = findViewById(R.id.edit_text_passwd);
        imageReg = findViewById(R.id.image_registr);
        imageReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                String passwd = editPasswd.getText().toString();
                if (passwd.isEmpty() && username.isEmpty()){
                    Toast.makeText(RegisterActivity.this,getResources().getString(R.string.pasword_and_username),Toast.LENGTH_SHORT).show();
                }
                else if (username.isEmpty()){
                    Toast.makeText(RegisterActivity.this,getResources().getString(R.string.username_add),Toast.LENGTH_SHORT).show();
                }
                else if (passwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this,getResources().getString(R.string.passwd_add),Toast.LENGTH_SHORT).show();
                }
                else if((!username.isEmpty()) && passwd.length()<8){
                    Toast.makeText(RegisterActivity.this,getResources().getString(R.string.password_length),Toast.LENGTH_SHORT).show();
                }
                else {
                    PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this).edit().putString("username",username).putString("passwd",passwd).apply();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        String username = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this).getString("username",null);
        String passwd = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this).getString("passwd",null);
        if (username==null && passwd == null){
            PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this).edit().putString("brightness","5").putString("selfdevelopment","5").putString("things","5")
                    .putString("family","5").putString("medical","5").putString("career","5").putString("money","5").putString("love","5").apply();

           // Toast.makeText(RegisterActivity.this,"aaaaaaaa",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
