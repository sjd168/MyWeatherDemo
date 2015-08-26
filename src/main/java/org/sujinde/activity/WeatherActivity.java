/*
 * Copyright (c) 2015 SuJinde , Licensed under the Apache License, Version 2.0.
 * Email : sujinde168@foxmail.com
 */

package org.sujinde.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;

import org.sujinde.bean.MultiDayWeatherBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import caffcoo.com.myweatherdemo.R;

public class WeatherActivity extends ActionBarActivity {

    String submitCity = null;
    String cityName = "";
    String httpArg = null;
    final static String WeatherActivity = "WeatherActivity";

    private TextView txtDateWeek;
    private TextView txtCity;
    private TextView txtWeather;
    private TextView txtCurTemp;
    private TextView txtHighTemp;
    private TextView txtLowTemp;
    private EditText edtCity;
    private Button btnSubmitCity;
    private ListView lvWeather;

    MultiDayWeatherBean multiDayWeatherBean;

    public LocationClient locationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    ArrayList<HashMap<String, Object>> weatherDataArrayList = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(WeatherActivity, "onCreate()");
        setContentView(R.layout.weather);
        initialize();

        handleLocation();

        listerner();
//        inflateListView();
    }

    public void inflateListView() {
        String[] types = new String[]{"facebook", "sb", "uber", "erbi"};
        ArrayAdapter a2 = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_list_item_1);
        lvWeather.setAdapter(a2);
    }

    public void listerner() {
        txtCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCity.setVisibility(View.VISIBLE);
                btnSubmitCity.setVisibility(View.VISIBLE);
            }
        });
        btnSubmitCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCity = edtCity.getText().toString();
                new GetDataTask().execute(encodeURL(submitCity));
                Log.d(WeatherActivity, "submitCity" + submitCity);
            }
        });
    }

    public void handleLocation() {
        //        生成位置客户端
        locationClient = new LocationClient(getApplicationContext());
        //        为客户端注册监听器
        locationClient.registerLocationListener(myListener);
        //搞定，设置位置选项
        initLocation();
        //        开搞咯
        locationClient.start();
    }

    //给一个城市名，返回查询字符串
    public String encodeURL(String cityName) {
        String cityNameCode;
        String httpArg = null;
        if (!TextUtils.isEmpty(cityName)) {
            try {
                cityNameCode = URLEncoder.encode(cityName, "UTF-8");
                String httpUrl = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
                httpArg = httpUrl + "?" + "cityname=" + cityNameCode;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(WeatherActivity, "cityName is null");
        }
        return httpArg;
    }

    private void initialize() {

        txtDateWeek = (TextView) findViewById(R.id.txtDateWeek);
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtWeather = (TextView) findViewById(R.id.txtWeather);
        txtCurTemp = (TextView) findViewById(R.id.txtCurTemp);
        txtHighTemp = (TextView) findViewById(R.id.txtHighTemp);
        txtLowTemp = (TextView) findViewById(R.id.txtLowTemp);
        edtCity = (EditText) findViewById(R.id.edtCity);
        btnSubmitCity = (Button) findViewById(R.id.btnSubmitCity);

        lvWeather = (ListView) findViewById(R.id.lvWeather);
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
                multiDayWeatherBean = gson.fromJson(s, MultiDayWeatherBean.class);



                showTodayWeather(multiDayWeatherBean);

                showForecastWeather(multiDayWeatherBean);

            }
        }
    }


    public void showForecastWeather(MultiDayWeatherBean multiDayWeatherBean) {

        MultiDayWeatherBean.RetDataEntity retDataEntity = multiDayWeatherBean.getRetData();
        int sizeOfHistoryWeather = retDataEntity.getHistory().size();
        int sizeOfForecastWeather = retDataEntity.getForecast().size();

        String date;
        String week;
        String type;
        String lowTemp;
        String highTemp;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        for (int day = 0; day < sizeOfHistoryWeather; day++) {
            MultiDayWeatherBean.RetDataEntity.HistoryEntity historyEntity = multiDayWeatherBean.getRetData().getHistory().get(day);
            date = historyEntity.getDate();
            week = historyEntity.getWeek();
            type = historyEntity.getType();
            lowTemp = historyEntity.getLowtemp();
            highTemp = historyEntity.getHightemp();
            hashMap = new HashMap<>();
            hashMap.put("dayAndWeek", date + week);
            hashMap.put("type", type);
            hashMap.put("temp", lowTemp + "~" + highTemp);
            weatherDataArrayList.add(hashMap);
        }

        MultiDayWeatherBean.RetDataEntity.TodayEntity todayEntity = multiDayWeatherBean.getRetData().getToday();
        date = todayEntity.getDate();
        week = todayEntity.getWeek();
        type = todayEntity.getType();
        lowTemp = todayEntity.getLowtemp();
        highTemp = todayEntity.getHightemp();
        hashMap = new HashMap<>();
        hashMap.put("dayAndWeek", date + week);
        hashMap.put("type", type);
        hashMap.put("temp", lowTemp + "~" + highTemp);
        weatherDataArrayList.add(hashMap);

        for (int day = 0; day < sizeOfForecastWeather; day++) {
            MultiDayWeatherBean.RetDataEntity.ForecastEntity forecastEntity = multiDayWeatherBean.getRetData().getForecast().get(day);
            date = forecastEntity.getDate();
            week = forecastEntity.getWeek();
            type = forecastEntity.getType();
            lowTemp = forecastEntity.getLowtemp();
            highTemp = forecastEntity.getHightemp();
            hashMap = new HashMap<>();
            hashMap.put("dayAndWeek", date + week);
            hashMap.put("type", type);
            hashMap.put("temp", lowTemp + "~" + highTemp);
            weatherDataArrayList.add(hashMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                weatherDataArrayList,
                R.layout.weather_item,
                new String[]{"dayAndWeek", "type", "temp"},
                new int[]{R.id.txtDateWeek, R.id.txtType, R.id.txtTemp});

        lvWeather.setAdapter(simpleAdapter);

    }

    //    可用
    public void showTodayWeather(MultiDayWeatherBean multiDayWeatherBean) {
        String city = multiDayWeatherBean.getRetData().getCity();
        String curTemp = multiDayWeatherBean.getRetData().getToday().getCurTemp();
        String date = multiDayWeatherBean.getRetData().getToday().getDate();
        //        String fengli = multiDayWeatherBean.getRetData().getToday().getFengli();
        //        String fengxiang = multiDayWeatherBean.getRetData().getToday().getFengxiang();
        String highTemp = multiDayWeatherBean.getRetData().getToday().getHightemp();
        String lowTemp = multiDayWeatherBean.getRetData().getToday().getLowtemp();
        String type = multiDayWeatherBean.getRetData().getToday().getType();
        String week = multiDayWeatherBean.getRetData().getToday().getWeek();

        txtCity.setText(city);
        txtDateWeek.setText(date + "/" + week);
        txtWeather.setText(type);
        txtCurTemp.setText(curTemp);
        txtLowTemp.setText(lowTemp);
        txtHighTemp.setText(highTemp);

        //        String allStr=curTemp+city+date+fengli+fengxiang+highTemp+lowTemp+type+week;
        //        Log.d(WeatherActivity, "Today :::"+allStr);
    }

    public void handleWeather(BDLocation location) {
        cityName = location.getCity();
        cityName = cityName.substring(0, cityName.length() - 1);
        Log.d(WeatherActivity, "now subString of cityName is " + cityName);

        httpArg = encodeURL(cityName);
        new GetDataTask().execute(httpArg);
    }

    public class MyLocationListener implements BDLocationListener {

        final static String MyLocationListener = "MyLocationListener";

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            Log.d(MyLocationListener, "onReceiveLocation(BDLocation location)");
            Log.d(MyLocationListener, "可以获取城市吗萌萌哒：：" + location.getCity());

            handleWeather(location);

            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());

                //            获取城市名称
                sb.append("\n 城市：");
                sb.append(location.getCity());

                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            //        sb.append("\nlocationdescribe : ");
            //        sb.append(location.getLocationDescribe());// 位置语义化信息
            //        List<Poi> list = location.getPoiList();// POI数据
            //        if (list != null) {
            //            sb.append("\npoilist size = : ");
            //            sb.append(list.size());
            //            for (Poi p : list) {
            //                sb.append("\npoi= : ");
            //                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            //            }
            //        }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }

    private void initLocation() {
        Log.d("MyLocationUtil", "In the initLocation");
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(true);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(true);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        locationClient.setLocOption(option);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
