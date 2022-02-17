package com.colin.model;

import com.colin.bean.LoginBean;
import com.colin.bean.UserBean;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Date:2022-02-16
 * Time:17:58
 * author:colin
 */
public interface ServerApi {

    @POST("uc/user/login/{captcha}")
    Call<LoginBean> userLogin(@Header ("l_c_i") String headerKey,@Body UserBean userBean, @Path("captcha")String captcha);

}
