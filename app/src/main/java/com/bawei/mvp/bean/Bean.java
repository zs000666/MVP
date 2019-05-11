package com.bawei.mvp.bean;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * */public class Bean {
     private String phone;
     private String pwd;

    public Bean(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public Bean() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
