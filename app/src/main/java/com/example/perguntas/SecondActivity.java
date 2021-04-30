package com.example.perguntas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if(extras!= null){
            Fragment_historico fragmento = new Fragment_historico();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmento).addToBackStack(null).commit();
        }
        else{
            Fragment1 fragmento = new Fragment1();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmento).addToBackStack(null).commit();
        }

    }

    @Override
    public void onBackPressed() {
        emptyBackStack();
        super.onBackPressed();
    }

    public void emptyBackStack(){
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for(int i = 0; i < count; i++) {
            fm.popBackStack();
        }
    }
}
