package com.bawei.mvp.net;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * */public interface NetCallback {
     //响应成功
    void onSuccess(String result);
    //失败
    void onFail(String msg);
}
