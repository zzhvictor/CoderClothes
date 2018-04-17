package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.first.Application.CCApplication;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 大学生小赵 on 2018/4/14.
 */

public class SetActivity extends AppCompatActivity {

    private static final int EXCUTE_OK = 1;
    private EditText userNameEt,passWordEt;
    private Button submitbtn;
    private CCApplication mCC;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case EXCUTE_OK:
                    String result = (String) msg.obj;
                    System.out.println(result);
                    switch (result){
                        case "success":
                            setResult(1);
                            SetActivity.this.finish();
                            break;
                        case "fail":
                            Toast.makeText(getApplication(),"修改失败",Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };

    private void initialization() {
        userNameEt=(EditText)findViewById(R.id.userNameEditText);
        passWordEt=(EditText)findViewById(R.id.passWordEditText);
        submitbtn = (Button)findViewById(R.id.submitBtn);
        mCC = (CCApplication) getApplicationContext();
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setactivity);
        initialization();
    }

    public void submit(View view) {

        if(!userNameEt.getText().toString().equals("")&& !passWordEt.getText().toString().equals("")){
            final String str_url = "http://192.168.80.1:8080/submitCheck?userName="+userNameEt.getText().toString()+
                    "&passWord="+passWordEt.getText().toString()+"&formUN="+mCC.getUserName();
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
                        Message msg = Message.obtain();
                        msg.what = EXCUTE_OK;
                        msg.obj = result;
                        mHandler.sendMessage(msg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }else {
            Toast.makeText(getApplication(), "用户名或密码不能为空哦", Toast.LENGTH_SHORT).show();
        }

    }
}
