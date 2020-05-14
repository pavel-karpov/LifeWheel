package com.pavel_karpov.first.myapp1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.recycler_container);
        if (fragment==null){
            fragment = RecyclerFragment.getInstance();
            fragmentManager.beginTransaction().add(R.id.recycler_container,fragment).commit();
        }
    }
}
