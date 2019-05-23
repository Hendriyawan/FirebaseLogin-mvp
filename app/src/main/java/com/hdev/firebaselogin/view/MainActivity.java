package com.hdev.firebaselogin.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.hdev.firebaselogin.R;
import com.hdev.firebaselogin.view.login.LoginPresenter;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BindView
        ButterKnife.bind(this);

        initToolbar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_exit:
                LoginPresenter.signOut(FirebaseAuth.getInstance());
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //init toolbar
    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.ic8_firebase));
        }
    }
}
