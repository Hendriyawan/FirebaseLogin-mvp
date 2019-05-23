package com.hdev.firebaselogin.view.login;

public interface LoginView {
    void onStartProgress();

    void onStopProgress();

    void onSuccess(String message);

    void onFailed(String message);

    void onUserSigned();
}
