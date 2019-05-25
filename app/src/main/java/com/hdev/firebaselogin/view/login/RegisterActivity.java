package com.hdev.firebaselogin.view.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hdev.firebaselogin.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
created by Hendriyawan 23 Mei 2019
 */
public class RegisterActivity extends AppCompatActivity implements LoginView {
    private final String TAG = RegisterActivity.this.getClass().getSimpleName();
    @BindView(R.id.input_email)
    TextInputEditText inputEmail;
    @BindView(R.id.input_password)
    TextInputEditText inputPassword;
    @BindString(R.string.email_empty_message)
    String EMAIL_EMPTY;
    @BindString(R.string.password_empty_message)
    String PASSWORD_EMPTY;
    @BindString(R.string.password_not_valid_message)
    String PASSWORD_NOT_VALID;

    private ProgressDialog progressDialog;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //BindView
        ButterKnife.bind(this);

        initToolbar();
        initProgessDialog();

        //login presenter
        loginPresenter = new LoginPresenter(this, this);
    }

    //OnClick Button Signup
    @OnClick(R.id.button_regitser)
    public void registerClick(View v) {
        Log.d(TAG, "button register clicked");
        loginPresenter.signUp(inputEmail.getText().toString().trim(), inputPassword.getText().toString().trim());
    }

    //OnClick TextView signin
    @OnClick(R.id.tv_signin)
    public void textSigninClick(View v) {
        Log.d(TAG, "text signin clicked");
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //back to Login Page
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        showToast(message + " please login to your account !");
        finish();
    }

    @Override
    public void onFailed(String message) {
        Log.d(TAG, "onFailed() executed");
        if (message.equals(EMAIL_EMPTY)) {
            inputEmail.setError(message);
            inputEmail.requestFocus();

        } else if (message.equals(PASSWORD_EMPTY)) {
            inputPassword.setError(message);
            inputPassword.requestFocus();

        } else if (message.equals(PASSWORD_NOT_VALID)) {
            inputPassword.setError(message);
            inputPassword.requestFocus();

        } else {
            showToast(message);
        }
    }

    @Override
    public void onUserSigned() {

    }

    //init toolbar
    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    //init progress Dialog
    private void initProgessDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Register !");
        progressDialog.setIndeterminate(false);
    }

    //show Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
