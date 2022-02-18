package com.colin.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.colin.ui.fragment.LoginAccountFragment;
import com.colin.ui.fragment.LoginFragment;
import com.colin.ygst.R;


/**
 * 2022.2.16 更新使用MVP架构来实现登陆功能 */
public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showLoginFragment();

    }

    public void showLoginFragment(){

        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_denglu,loginFragment)
        .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }







}