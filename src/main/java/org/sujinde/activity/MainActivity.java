/*
 * Copyright (c) 2015 SuJinde , Licensed under the Apache License, Version 2.0.
 * Email : sujinde168@foxmail.com
 */

package org.sujinde.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import caffcoo.com.myweatherdemo.R;

public class MainActivity extends Activity {

//    TextView helloworld;
//    String jsonResult;

    //    String s3=null;
    final static String Weather = "Weather";
    String httpUrl = "http://apis.baidu.com/apistore/weatherservice/weather?citypin=shenzhen";
    private TextView city;
    private TextView date;
    private TextView htmp;
    private TextView temp;
    private TextView ltmp;
    private TextView weather;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        initialize();

//        helloworld=(TextView)findViewById(R.id.helloworld);

        new GetDataTask().execute(httpUrl);
//        city.setText(s3);
//        helloworld.setText(jsonResult);

    }

    private void initialize() {

        city = (TextView) findViewById(R.id.city);
        date = (TextView) findViewById(R.id.date);
        htmp = (TextView) findViewById(R.id.h_tmp);
        temp = (TextView) findViewById(R.id.temp);
        ltmp = (TextView) findViewById(R.id.l_tmp);
        weather = (TextView) findViewById(R.id.weather);
    }

    class GetDataTask extends AsyncTask<String, Void, String> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(String... params) {
            String result = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("apikey", "516091aa2cc0e62c0303a1723b0f1912");
                connection.connect();
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                if (null != (line = bufferedReader.readLine())) {
                    stringBuilder.append(line);
                    stringBuilder.append("\r\n");
                }
                bufferedReader.close();
                result = stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != connection)
                    connection.disconnect();
            }
//            jsonResult=result;
            return result;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p/>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param s The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(Weather, s);

            Gson gson = new Gson();
//            singleDayWeatherBean = gson.fromJson(s, SingleDayWeatherBean.class);
//            multiDayWeatherBeanOld=gson.fromJson(s,MultiDayWeatherBeanOld.class);

//            Log.d(Weather,multiDayWeatherBeanOld.toString());
//            weather.setText(singleDayWeatherBean.toString());

//            helloworld.setText(singleDayWeatherBean.toString());
//            showWeatherToUI();
//            s3=getData(s);
        }
    }

//    public void showWeatherToUI() {
//        city.setText(singleDayWeatherBean.getRetData().getCity());
//        date.setText(singleDayWeatherBean.getRetData().getDate());
//        htmp.setText(singleDayWeatherBean.getRetData().getH_tmp() + "°C");
//        temp.setText(singleDayWeatherBean.getRetData().getTemp() + "°C");
//        ltmp.setText(singleDayWeatherBean.getRetData().getL_tmp() + "°C");
//        weather.setText(singleDayWeatherBean.getRetData().getWeather());
//    }

//    public String getData(String s)
//    {
//        String s1=s;
//        return s1;
//    }

}
