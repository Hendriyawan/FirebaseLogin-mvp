package com.hdev.firebaselogin.view.login;

import android.text.TextUtils;

public class User implements UserView {

    public static final int EMAIL_EMPTY = 0;
    public static final int PASSWORD_EMPTY = 1;
    public static final int PASSWORD_NOT_VALID = 2;
    public static final int IS_VALID = -1;

    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int validate() {
        if (TextUtils.isEmpty(email)) {
            return EMAIL_EMPTY;

        } else if (TextUtils.isEmpty(password)) {
            return PASSWORD_EMPTY;

        } else if (getPassword().length() <= 6) {
            return PASSWORD_NOT_VALID;

        } else {
            return IS_VALID;

        }
    }
}
