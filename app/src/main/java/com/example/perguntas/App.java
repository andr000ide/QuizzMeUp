package com.example.perguntas;

import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;

public class App extends Application {

    private static App mApplication;
    private static FirebaseDatabase firebaseDatabase;
    public static App getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public FirebaseDatabase getFirebaseDatabase(){
        return firebaseDatabase;
    }



}
