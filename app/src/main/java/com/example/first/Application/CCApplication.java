package com.example.first.Application;

import android.app.Application;

/**
 * Created by 大学生小赵 on 2018/4/17.
 */

public class CCApplication extends Application {
    private String userName;
    private int userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
