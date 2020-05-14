package com.pavel_karpov.first.myapp1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.book_fragment_container);
        if (fragment==null){
            fragment = BookFragment.getInstance();
            fragmentManager.beginTransaction().add(R.id.book_fragment_container,fragment).commit();
        }
    }
}
