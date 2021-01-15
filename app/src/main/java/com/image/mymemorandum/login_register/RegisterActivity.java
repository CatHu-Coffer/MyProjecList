package com.image.mymemorandum.login_register;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.image.mymemorandum.DemoBaseAcitivity;
import com.image.mymemorandum.R;
import com.image.mymemorandum.gen.response.User;
import com.mylibrary.ulite.SecondClickUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends DemoBaseAcitivity {

    @BindView(R.id.register_user_name)
    EditText registerUserName;
    @BindView(R.id.register_user_password)
    EditText registerUserPassword;
    @BindView(R.id.register_user_password2)
    EditText registerUserPassword2;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        iniData();
    }

    private void iniData() {
        toolbarTitle.setText("注册");
    }

    @OnClick({R.id.toolbar_back, R.id.register_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                setResult(0);
                finish();
                break;
            case R.id.register_ok:
                if (!SecondClickUtils.isFastClick()) {
                    registerUser();
                }
                break;
        }
    }

    private void registerUser() {
        String user = registerUserName.getText().toString().trim();
        String password = registerUserPassword.getText().toString().trim();
        String password2 = registerUserPassword2.getText().toString().trim();
        if (isEmptyStr(user)) {
            showToast("请输入用户名");
            return;
        }
        if (isEmptyStr(password) || isEmptyStr(password2)) {
            showToast("请输入密码");
            return;
        }
        if (!password.equals(password2)) {
            showToast("两次密码不相符,请检查");
            return;
        }
        User bean = new User();
        bean.setUserName(user);
        bean.setPassWord(password);
        bean.setTime(new Date());
        bean.setAdress("");
        boolean addUser = fruitDaoUtil.insertFruit(bean);
        if(addUser) {
            setResult(1);
            finish();
        }else showToast("注册失败,请重试!");
    }
}
