package com.hdev.firebaselogin.view;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class FirebaseLoginApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
