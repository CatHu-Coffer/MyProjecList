package com.image.mymemorandum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.image.mymemorandum.gen.response.FruitDaoUtil;
import com.image.mymemorandum.gen.response.User;
import com.image.mymemorandum.home.HomeActivity;
import com.image.mymemorandum.login_register.LoginActivity;
import com.image.mymemorandum.login_register.RegisterActivity;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends DemoBaseAcitivity {

    @BindView(R.id.login_user_edtext)
    EditText loginUserEdtext;
    @BindView(R.id.login_password_edtext)
    EditText loginPasswordEdtext;
    private Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iniData();

    }

    private void iniData() {
        List<User> list = fruitDaoUtil.queryAllFruit(User.class);
        if(list!=null && list.size()>0){
            loginUserEdtext.setText(list.get(0).getUserName());
        }
    }

    @OnClick({R.id.login, R.id.registered})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                intent.setClass(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.registered:
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            iniData();
        }
    }
}
