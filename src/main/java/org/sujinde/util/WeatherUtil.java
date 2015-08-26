/*
 * Copyright (c) 2015 SuJinde , Licensed under the Apache License, Version 2.0.
 * Email : sujinde168@foxmail.com
 */

package org.sujinde.util;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.sujinde.bean.MultiDayWeatherBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by sujinde on 2015/8/6. 10:49
 * Email : sujinde168@foxmail.com
 */
public class WeatherUtil {

    private String cityName = null;
    private final String WeatherUtil="WeatherUtil";

    public MultiDayWeatherBean getMultiDayWeatherBean() {
        return multiDayWeatherBean;
    }

    private MultiDayWeatherBean multiDayWeatherBean;
    private final static String httpUrl = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
    private String httpArg;


    public WeatherUtil(String cityName) {
        this.cityName = cityName;
        httpArg = combineURL(encodeCityName(cityName));
        Log.d(WeatherUtil,"httpArg::"+httpArg);

        if (!TextUtils.isEmpty(httpArg)) {
            new GetDataTask().execute(httpArg);
        }

    }

    public String combineURL(String cityCode) {
        return httpUrl + "?" + "cityname=" + cityCode;
    }

    public String encodeCityName(String cityName) {
        String cityCode = null;
        try {
            cityCode = URLEncoder.encode(cityName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityCode;
    }


    class GetDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(8000);
                connection.setConnectTimeout(8000);
                connection.setRequestProperty("apikey", "516091aa2cc0e62c0303a1723b0f1912");
                connection.connect();
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder builder = new StringBuilder();
                String line;
                while (null != (line = reader.readLine())) {
                    builder.append(line);
                    builder.append("\r\n");
                }
                reader.close();
                result = builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != connection)
                    connection.disconnect();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)) {
                Gson gson = new Gson();
                Log.d(WeatherUtil,s);
                multiDayWeatherBean = gson.fromJson(s, MultiDayWeatherBean.class);
                if (null!=multiDayWeatherBean)
                {
                    Log.d(WeatherUtil, "multiDayWeatherBean::" + multiDayWeatherBean.toString());
//                    Log.d(WeatherUtil, "type" + multiDayWeatherBean.getErrMsg()+multiDayWeatherBean.getRetData()
//                    .getForecast().get(1).getLowtemp());
                }else
                {
                    Log.d(WeatherUtil,"multiDayWeatherBean is null !");
                }
            }
        }
    }

}
