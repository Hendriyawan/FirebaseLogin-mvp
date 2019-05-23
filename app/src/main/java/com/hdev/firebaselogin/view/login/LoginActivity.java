package com.hdev.firebaselogin.view.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hdev.firebaselogin.R;
import com.hdev.firebaselogin.view.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
created by Hendriyawan 23 Mei 2019
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private final String TAG = LoginActivity.this.getClass().getSimpleName();
    @BindView((R.id.input_email))
    TextInputEditText inputEmail;
    @BindView(R.id.input_password)
    TextInputEditText inputPassword;
    private LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;

    private String EMAIL_EMPTY;
    private String PASSWORD_EMPTY;
    private String PASSWORD_NOT_VALID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //BindView
        ButterKnife.bind(this);

        initToolbar();
        initProgressDialog();

        EMAIL_EMPTY = getResources().getString(R.string.email_empty_message);
        PASSWORD_EMPTY = getResources().getString(R.string.password_empty_message);
        PASSWORD_NOT_VALID = getResources().getString(R.string.password_not_valid_message);

        //login presenter
        loginPresenter = new LoginPresenter(this, this);
        loginPresenter.checkUser();
    }

    //OnClick Button Signin
    @OnClick(R.id.button_login)
    public void loginClick(View v) {
        Log.d(TAG, "button login clicked");
        loginPresenter.signIn(inputEmail.getText().toString().trim(), inputPassword.getText().toString().trim());
    }

    //OnClick TextView register
    @OnClick(R.id.tv_register)
    public void textRegisterClick(View v) {
        Log.d(TAG, "text register clicked");
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void onStartProgress() {
        progressDialog.show();
    }

    @Override
    public void onStopProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onSuccess(String message) {
        Log.d(TAG, "onSuccess() executed");
        showToast(message);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailed(String message) {
        Log.d(TAG, "onFailed() executed");
        if (message.equals(EMAIL_EMPTY)) {
            inputEmail.setError(message);
            inputEmail.requestFocus();
        }
        if (message.equals(PASSWORD_EMPTY)) {
            inputPassword.setError(message);
            inputPassword.requestFocus();
        }
        if(message.equals(PASSWORD_NOT_VALID)){
            inputPassword.setError(message);
            inputPassword.requestFocus();
        }
    }

    @Override
    public void onUserSigned() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //init Toolbar
    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
        }
    }

    //init ProgressDialog
    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login");
        progressDialog.setIndeterminate(false);
    }

    //show Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}