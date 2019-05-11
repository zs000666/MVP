package com.bawei.mvp.mvp;

import com.bawei.mvp.net.HttpUtils;
import com.bawei.mvp.net.NetCallback;

import java.util.Map;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * */public class Model implements IContract.IModel{
    private HttpUtils httpUtils;
    public Model(){
        httpUtils= HttpUtils.utils();
    }

    @Override
    public void doHttpPost(String url, Map<String, String> param, NetCallback callback) {
        httpUtils.doHttpUtils(url,param,callback);
    }
}
