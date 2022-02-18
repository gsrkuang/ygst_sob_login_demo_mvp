package com.colin.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.bean.LoginBean;
import com.colin.presenter.UserLoginPresenter;
import com.colin.ui.activity.LoginActivity;
import com.colin.util.Constants;
import com.colin.view.IUserLoginView;
import com.colin.ygst.R;

/**
 * Date:2022-02-18
 * Time:15:06
 * author:colin
 * 用户登录界面
 */
public class LoginAccountFragment extends Fragment implements IUserLoginView {

    private Button mLogin;
    private EditText mVerificationCode;
    private EditText mUserName;
    private EditText mPassWord;
    private AppCompatImageView mVerificationImg;

    UserLoginPresenter userLoginPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //该View表示该碎片的主界面,最后要返回该view
        View view = inflater.inflate(R.layout.account_login_fragment,container,false);
        initView(view);
        initEvent();
        refreshCaptcha();

        userLoginPresenter = new UserLoginPresenter(getContext(), this);
        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    private void initEvent(){

        mVerificationImg.setOnClickListener(view -> refreshCaptcha());

        mLogin.setOnClickListener(view -> {

            userLoginPresenter.login();

        });

    }

    private void initView(View view) {
        mLogin = view.findViewById(R.id.login);
        mVerificationCode = view.findViewById(R.id.verificationCode);
        mPassWord = view.findViewById(R.id.password);
        mUserName = view.findViewById(R.id.username);
        mVerificationImg = view.findViewById(R.id.iv_login_verify_code);
    }

    private void refreshCaptcha(){
        Glide.with(getContext())
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
