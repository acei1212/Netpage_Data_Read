package com.example.student.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v)
    {
        new Thread(){
            @Override
            public void run()
            {
                try {
                    URL url = new URL("http://rate.bot.com.tw/xrt?Lang=zh-TW");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String str;
                    while ((str=br.readLine()) != null)
                    {
                        sb.append(str);
                    }
                    String result = sb.toString();
                    int loc1 = result.indexOf("0.2716");
                    Log.d("LOC1", "loc1:" + loc1);
                    int loc2 = result.indexOf("日圓 (JPY)");
                    Log.d("LOC2", "loc2:" + loc2);
                    int loc3 = result.indexOf("本行現金賣出", loc2);
                    Log.d("LOC3", "loc3:" + loc3);

                    int locJPY = loc3 + 56;
                    String jpy = result.substring(locJPY, locJPY + 6);
                    Log.d("LOC", "JPY:" + jpy);
                    br.close();
                    isr.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();


    }
}
