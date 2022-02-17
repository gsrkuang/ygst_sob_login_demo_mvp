package com.colin.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.bean.LoginBean;
import com.colin.bean.UserBean;
import com.colin.model.ServerApi;
import com.colin.presenter.UserLoginPresenter;
import com.colin.request.RequestInterceptor;
import com.colin.util.Constants;
import com.colin.util.MD5Util;
import com.colin.view.IUserLoginView;
import com.colin.ygst.R;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 2022.2.16 更新使用MVP架构来实现登陆功能 */
public class LoginActivity extends AppCompatActivity implements IUserLoginView {

    private Button mLogin;
    private EditText mVerificationCode;
    private EditText mUserName;
    private EditText mPassWord;
    private AppCompatImageView mVerificationImg;


    UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this,LoginActivity.this);

    private String loginResponseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshCaptcha();
    }

    private void initView() {
        mLogin = findViewById(R.id.login);
        mVerificationCode = findViewById(R.id.verificationCode);
        mPassWord = findViewById(R.id.password);
        mUserName = findViewById(R.id.username);
        mVerificationImg = findViewById(R.id.iv_login_verify_code);
    }
    private void initEvent(){

        mVerificationImg.setOnClickListener(view -> refreshCaptcha());

        mLogin.setOnClickListener(view -> {

            userLoginPresenter.login();

        });

    }

    private void refreshCaptcha(){
        Glide.with(this)
                .load(String.format(Constants.SUNNY_BEACH_API_BASE_URL + "uc/ut/captcha?code=%s", System.currentTimeMillis()))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mVerificationImg);
    }


    @Override
    public void showLogin(LoginBean loginBean, String captcha) {

    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassWord.getText().toString();
    }

    @Override
    public String getCaptcha() {
        return mVerificationCode.getText().toString();
    }
}