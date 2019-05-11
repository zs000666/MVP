package com.bawei.mvp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.mvp.R;
import com.bawei.mvp.bean.Bean;
import com.bawei.mvp.mvp.IContract;
import com.bawei.mvp.mvp.Presenter;

public class MainActivity extends AppCompatActivity implements IContract.IView {

    private EditText pwd;
    private EditText phone;
    private CheckBox check;
    private Presenter presenter;
    private Button resgin;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        phone = findViewById(R.id.et_username);
        pwd = findViewById(R.id.et_password);
        check = findViewById(R.id.cb_check);
        resgin = findViewById(R.id.resgin);
        login = findViewById(R.id.login);
        //数据
        presenter = new Presenter();
        presenter.attach(this);


        resgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"13",Toast.LENGTH_LONG).show();
                Bean user = presenter.inputToUser(phone.getText().toString().trim(), pwd.getText().toString().trim());
                Toast.makeText(MainActivity.this,user.toString(),Toast.LENGTH_LONG).show();
                presenter.regist(user);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bean bean = presenter.inputToUser(phone.getText().toString(), pwd.getText().toString());
                presenter.login(bean);
            }
        });
    }

    @Override
    public void registSuccess(String result) {
        Toast.makeText(this,result,Toast.LENGTH_LONG).show();
    }

    @Override
    public void registFail() {

    }

    @Override
    public void loginSuccess(String result) {
        Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        presenter.toMain(this);
    }

    @Override
    public void loginFail() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

}
