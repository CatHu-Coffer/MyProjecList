package com.image.mymemorandum.gen.response;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 123 on 2018/1/10.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long userId;
    private String userName;
    private String passWord;
    private Date time;
    private String adress;
    @Generated(hash = 987526945)
    public User(Long userId, String userName, String passWord, Date time,
            String adress) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.time = time;
        this.adress = adress;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public Date getTime() {
        return this.time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public String getAdress() {
        return this.adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
}
