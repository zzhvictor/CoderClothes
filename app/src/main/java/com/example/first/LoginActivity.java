package com.example.first;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.first.Application.CCApplication;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 大学生小赵 on 2018/4/12.
 */

public class LoginActivity extends AppCompatActivity {

    private static final int EXCUTE_OK = 1;
    private EditText et_userName;
    private EditText et_passWord;
    private CheckBox cb_js,cb_ea,cb_vint,cb_off;
    private Button btn_login;
    private Button btn_register;
    private CCApplication mCC;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case EXCUTE_OK:
                    JSONObject obj = (JSONObject) msg.obj;
                    String status = obj.getString("status");
                    switch (status){
                        case "ok":
                            mCC = (CCApplication) getApplicationContext();
                            mCC.setUserId(obj.getIntValue("userId"));
                            mCC.setUserName(obj.getString("userName"));
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            break;
                        case "error":
                            Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initialization();
    }

    private void initialization() {
        et_userName = (EditText)findViewById(R.id.userNameEditText);
        et_passWord = (EditText)findViewById(R.id.passWordEditText);
        btn_login = (Button)findViewById(R.id.loginBtn);
        btn_register = (Button)findViewById(R.id.registerBtn);
        cb_js = (CheckBox)findViewById(R.id.JScheckbox);
        cb_ea=(CheckBox)findViewById(R.id.EAcheckbox);
        cb_vint=(CheckBox)findViewById(R.id.Vintcheckbox);
        cb_off=(CheckBox)findViewById(R.id.Offcheckbox);

    }

    public void login(View view){


        final String str_url = "http://192.168.80.1:8080/loginCheck?userName="+et_userName.getText().toString()+
                "&passWord="+et_passWord.getText().toString();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(str_url);
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setConnectTimeout(5000);
                    urlConn.setRequestMethod("GET");
                    InputStream ins = urlConn.getInputStream();
                    byte[] bytes = new byte[1024];
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int len = 0;
                    while ((len = ins.read(bytes))!=-1){
                        baos.write(bytes,0,len);
                    }
                    String result = baos.toString();
                    baos.close();
                    JSONObject json= (JSONObject) JSONObject.parse(result);
                    Message msg = Message.obtain();
                    msg.what = EXCUTE_OK;
                    msg.obj = json;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void register(View view){

        if((!et_userName.getText().toString().equals("")&& !et_passWord.getText().toString().equals(""))&& (cb_js.isChecked()==true || cb_ea.isChecked()==true || cb_vint.isChecked()==true || cb_off.isChecked()==true)){
            final String str_url = "http://192.168.80.1:8080/registerCheck?userName="+et_userName.getText().toString()+
                    "&passWord="+et_passWord.getText().toString()+"&jsInterest="+cb_js.isChecked()+"&eaInterest="+cb_ea.isChecked()+"&vintInterest="+cb_vint.isChecked()+"&offInterest="+cb_off.isChecked();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        URL url = new URL(str_url);
                        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                        urlConn.setConnectTimeout(5000);
                        urlConn.setRequestMethod("GET");
                        InputStream ins = urlConn.getInputStream();
                        byte[] bytes = new byte[1024];
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int len = 0;
                        while ((len = ins.read(bytes))!=-1){
                            baos.write(bytes,0,len);
                        }
                        final String result = baos.toString();
                        /**
                         * 在UI线程里运行吐司
                         */
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }else{
            Toast.makeText(getApplication(),"请填写完整的用户信息哦",Toast.LENGTH_SHORT).show();
        }


    }
}
