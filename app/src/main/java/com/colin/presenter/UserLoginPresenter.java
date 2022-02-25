package com.colin.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.bean.LoginBean;
import com.colin.bean.UserAvaterBean;
import com.colin.bean.UserBean;
import com.colin.model.ServerApi;
import com.colin.request.RequestInterceptor;
import com.colin.ui.activity.MainActivity;
import com.colin.util.Constants;
import com.colin.util.MD5Util;
import com.colin.view.IUserLoginView;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date:2022-02-16
 * Time:17:29
 * author:colin
 */
public class UserLoginPresenter {
    private IUserLoginView userLoginView;
    private Activity mContext;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(Activity mContext, IUserLoginView userLoginView) {
        this.mContext = mContext;
        this.userLoginView = userLoginView;
    }

    public void login() {

        Log.e("++++test", userLoginView.getUserName());

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.api_main) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        UserBean userBean = new UserBean();
        userBean.setPhoneNum(userLoginView.getUserName());

        try {
            //这里要加密
            userBean.setPassword(MD5Util.getMD5String(userLoginView.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String captcha = userLoginView.getCaptcha();
//        Log.e("+sobCaptchaKey" , RequestInterceptor.sobCaptchaKey);

        Call<LoginBean> call = serverApi.userLogin(RequestInterceptor.sobCaptchaKey, userBean, captcha);

        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                //Response<LoginBean> responseBody  = call.execute();

                Log.e("+++login", response.headers().toString());

                //登陆成功后，获取sob_token
                String sob_token = response.headers().get("sob_token");

//                Log.e("+++login++sob_token" , sob_token );
//                response.headers().

                LoginBean loginBean = response.body();
                if (loginBean != null || !"".equals(loginBean)) {
                    showTips(loginBean);
                }
                if (loginBean.isSuccess()) {
                    //从DengluActivity切换到MianShiActivity 直接跳
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClass(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                    mContext.finish();
                }

            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable throwable) {

            }
        });

    }

    public void setUserAvater(String acoount, ImageView mVerificationImg) {


        //自动设置用户头像
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.api_main) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);


        Call<UserAvaterBean> call = serverApi.setUserAvater(acoount);


        call.enqueue(new Callback<UserAvaterBean>() {
            @Override
            public void onResponse(Call<UserAvaterBean> call, Response<UserAvaterBean> response) {
                UserAvaterBean userAvaterBean = response.body();

                if (userAvaterBean.isSuccess()) {
                    //根据图片地址加载图片
                    Glide.with(mContext)
                            .load(String.format(userAvaterBean.getData()))
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(mVerificationImg);

                }
            }

            @Override
            public void onFailure(Call<UserAvaterBean> call, Throwable throwable) {

            }
        });

    }

    public void showTips(LoginBean loginBean) {
        Log.e("+++login", loginBean.toString());
        Toast.makeText(mContext, loginBean.getMessage(), Toast.LENGTH_SHORT).show();

    }


}
