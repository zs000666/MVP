package com.bawei.mvp.mvp;

import android.content.Context;
import android.content.Intent;

import com.bawei.mvp.bean.Bean;
import com.bawei.mvp.net.NetCallback;
import com.bawei.mvp.ui.MainActivity;

import java.util.HashMap;
import java.util.Map;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * */public class Presenter implements IContract.IPresenter{
     private IContract.IModel model;
     private IContract.IView view;
    @Override
    public void attach(IContract.IView view) {
       this.view=view;
       model=new Model();
    }

    @Override
    public void detach() {
         if (view!=null){
             view=null;
         }
         if (model!=null){
             model=null;
         }
    }

    @Override
    public void regist(Bean user) {
        Map<String,String>param=new HashMap<>();
        param.put("phone",user.getPhone());
        param.put("pwd",user.getPwd());
        String url="http://172.17.8.100/small/user/v1/register";
        model.doHttpPost(url, param, new NetCallback() {
            @Override
            public void onSuccess(String result) {
                view.registSuccess(result);
            }

            @Override
            public void onFail(String msg) {
                view.registSuccess(msg);
            }
        });
    }

    @Override
    public void login(Bean user) {
          Map<String,String>param=new HashMap<>();
          param.put("phone",user.getPhone());
          param.put("pwd",user.getPwd());
          String urls="http://172.17.8.100/small/user/v1/login";
          model.doHttpPost(urls, param, new NetCallback() {
              @Override
              public void onSuccess(String result) {
                  view.loginSuccess(result);
              }

              @Override
              public void onFail(String msg) {
                  view.loginFail();
              }
          });
    }

    @Override
    public Bean inputToUser(String name, String pwd) {
        Bean user=new Bean(name,pwd);
        return user;
    }

    @Override
    public void toMain(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
