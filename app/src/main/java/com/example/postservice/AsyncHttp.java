package com.example.postservice;

import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ���i on 2017/02/10.
 */
public class AsyncHttp extends AsyncTask<String, Integer, Boolean> {
    HttpURLConnection urlConnection = null;
    Boolean flg = false;

    String name;
    String value;

    //�R���X�g���N�^
    public AsyncHttp(String name, double lat, double lng){
        this.name = name;
        this.value =  lat +"," + lng;
    }



    //�񓯊������͂����炩��
    @Override
    protected  Boolean doInBackground(String... contents){
        String urlinput = "http://10.250.0.93/upload/post.php";
        try{
            URL url = new URL(urlinput);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //POST�p�p�����[�^
            String postDataSample = "name=" + name + "&text=" + value;
            //POST�p�����[�^�ݒ�
            OutputStream out = urlConnection.getOutputStream();
            out.write(postDataSample.getBytes());
            out.flush();
            out.close();
            Log.d("test", postDataSample);
            //���X�|���X���󂯎��
            urlConnection.getInputStream();

            flg = true;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return flg;
    }
}
