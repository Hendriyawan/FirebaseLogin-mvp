package com.hdev.firebaselogin.view.login;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hdev.firebaselogin.R;

/*
created by Hendriyawan 23 Mei 2019
 */
public class LoginPresenter {

    private AppCompatActivity activity;
    private LoginView loginView;
    private FirebaseAuth auth;

    public LoginPresenter(AppCompatActivity activity, LoginView loginView) {
        this.activity = activity;
        this.loginView = loginView;
        auth = FirebaseAuth.getInstance();

    }

    /*
    check user, if user is Signed in, then open the Main Menu (MainActivity)
     */
    public void checkUser() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            loginView.onUserSigned();
        }
    }

    /*
    Signin
    @params email, password, sign in with user email password
     */
    public void signIn(String email, String password) {
        User user = new User(email, password);
        switch (user.validate()) {
            case User.EMAIL_EMPTY:
                loginView.onFailed(activity.getResources().getString(R.string.email_empty_message));
                break;

            case User.PASSWORD_EMPTY:
                loginView.onFailed(activity.getResources().getString(R.string.password_empty_message));
                break;

            case User.PASSWORD_NOT_VALID:
                loginView.onFailed(activity.getResources().getString(R.string.password_not_valid_message));
                break;

            case User.IS_VALID:
                loginView.onStartProgress();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loginView.onStopProgress();
                                if (task.isSuccessful()) {
                                    loginView.onSuccess(activity.getResources().getString(R.string.signin_success_message));
                                } else {
                                    loginView.onFailed(activity.getResources().getString(R.string.signin_failed_message));
                                }
                            }
                        });
                break;
        }
    }

    /*
    Signup
    @params email password, create user with email and password
     */
    public void signUp(final String email, String password) {
        User user = new User(email, password);
        switch (user.validate()) {
            case User.EMAIL_EMPTY:
                loginView.onFailed(activity.getResources().getString(R.string.email_empty_message));
                break;

            case User.PASSWORD_EMPTY:
                loginView.onFailed(activity.getResources().getString(R.string.password_empty_message));
                break;

            case User.PASSWORD_NOT_VALID:
                loginView.onFailed(activity.getResources().getString(R.string.password_not_valid_message));
                break;

            case User.IS_VALID:
                loginView.onStartProgress();
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loginView.onStopProgress();
                                if (task.isSuccessful()) {
                                    loginView.onSuccess(activity.getResources().getString(R.string.signup_success_message));

                                } else {
                                    loginView.onFailed(activity.getResources().getString(R.string.signup_failed_message));
                                }
                            }
                        });
                break;
        }
    }

    /*
    signout
     */
    public static void signOut(FirebaseAuth auth) {
        auth.signOut();
    }
}
