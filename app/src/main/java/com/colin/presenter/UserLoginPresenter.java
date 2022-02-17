package com.colin.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.colin.bean.LoginBean;
import com.colin.bean.UserBean;
import com.colin.model.ServerApi;
import com.colin.request.RequestInterceptor;
import com.colin.ui.activity.LoginActivity;
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
    private Context mContext;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(Context mContext ,IUserLoginView userLoginView)
    {
        this.mContext = mContext;
        this.userLoginView = userLoginView;
    }

    public void login(){

        Log.e("++++test" ,userLoginView.getUserName()) ;

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SUNNY_BEACH_API_BASE_URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        UserBean userBean = new UserBean() ;
        userBean.setPhoneNum(userLoginView.getUserName());

        try {
            //这里要加密
            userBean.setPassword(MD5Util.getMD5String(userLoginView.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String captcha = userLoginView.getCaptcha();
        Log.e("+sobCaptchaKey" , RequestInterceptor.sobCaptchaKey);

        Call<LoginBean> call =  serverApi.userLogin(RequestInterceptor.sobCaptchaKey,userBean,captcha);

        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                //Response<LoginBean> responseBody  = call.execute();
                LoginBean loginBean = response.body();
                if (loginBean != null || "".equals(loginBean)){

                Toast.makeText(mContext,loginBean.toString(),Toast.LENGTH_SHORT).show();
                }


//                Toast.makeText(mContext,loginBean.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable throwable) {

            }
        });

    }


}
