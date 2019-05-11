package com.bawei.mvp.mvp;

import android.content.Context;

import com.bawei.mvp.bean.Bean;
import com.bawei.mvp.net.NetCallback;

import java.util.Map;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * 用户契约 M V P 统一管理
 * */public interface IContract {
     //用户V 接口
    public interface IView{
        //注册成功
         void registSuccess(String result);
         //注册失败
         void registFail();
         //登录成功
         void loginSuccess(String result);
         //登录失败
         void loginFail();
     }
     //用户M 接口
     public interface IModel{
        //和网络交互 让参数通用一些 怎么办呢？ 我需要有个键有个值 MAP
       void doHttpPost(String url, Map<String,String>param, NetCallback callback);
     }
     //用户P 接口
    public interface IPresenter{
        //绑定 view
         void attach(IView view);
         //解绑 view
         void detach();
         //注册业务逻辑
         void regist(Bean user);
         //登录业务逻辑
         void login(Bean user);
         /**
          * 把电话和密码 封装成Bean
          *
          * @param phone
          * @param pwd
          * @return
          */
         Bean inputToUser(String phone,String pwd);
         /**
          * 跳转到主界面
          */
         void toMain(Context context);
     }
}
