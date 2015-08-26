/*
 * Copyright (c) 2015 SuJinde , Licensed under the Apache License, Version 2.0.
 * Email : sujinde168@foxmail.com
 */

package org.sujinde.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sujinde on 2015/8/5. 16:27
 * Email : sujinde168@foxmail.com
 */
public class MultiDayWeatherBean {

    /**
     * errNum : 0
     * errMsg : success
     * retData : {"history":[{"hightemp":"30℃","fengxiang":"无持续风向","lowtemp":"21℃","aqi":"28","fengli":"微风级","type":"阵雨","date":"2015-08-03","week":"星期一"},{"hightemp":"34℃","fengxiang":"无持续风向","lowtemp":"22℃","aqi":"100","fengli":"微风级","type":"晴","date":"2015-08-04","week":"星期二"}],"forecast":[{"hightemp":"30℃","fengxiang":"无持续风向","lowtemp":"22℃","fengli":"微风级","type":"多云","date":"2015-08-06","week":"星期四"},{"hightemp":"30℃","fengxiang":"无持续风向","lowtemp":"22℃","fengli":"微风级","type":"雷阵雨","date":"2015-08-07","week":"星期五"},{"hightemp":"30℃","fengxiang":"无持续风向","lowtemp":"22℃","fengli":"微风级","type":"雷阵雨","date":"2015-08-08","week":"星期六"},{"hightemp":"31℃","fengxiang":"无持续风向","lowtemp":"23℃","fengli":"微风级","type":"多云","date":"2015-08-09","week":"星期天"}],"today":{"hightemp":"32℃","index":[{"otherName":"","index":"","details":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","name":"感冒指数","code":"gm"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"防晒指数","code":"fs"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"穿衣指数","code":"ct"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"运动指数","code":"yd"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"洗车指数","code":"xc"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"晾晒指数","code":"ls"}],"fengxiang":"无持续风向","lowtemp":"22℃","curTemp":"29℃","aqi":"119","fengli":"微风级","type":"多云","date":"2015-08-05","week":"星期三"},"cityid":"101010100","city":"北京"}
     */
    @SerializedName("errNum")
    private int errNum;
    @SerializedName("errMsg")
    private String errMsg;
    @SerializedName("retData")
    private RetDataEntity retData;

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public class RetDataEntity {
        /**
         * history : [{"hightemp":"30℃","fengxiang":"无持续风向","lowtemp":"21℃","aqi":"28","fengli":"微风级","type":"阵雨","date":"2015-08-03","week":"星期一"},{"hightemp":"34℃","fengxiang":"无持续风向","lowtemp":"22℃","aqi":"100","fengli":"微风级","type":"晴","date":"2015-08-04","week":"星期二"}]
         * forecast : [{"hightemp":"30℃","fengxiang":"无持续风向","lowtemp":"22℃","fengli":"微风级","type":"多云","date":"2015-08-06","week":"星期四"},{"hightemp":"30℃","fengxiang":"无持续风向","lowtemp":"22℃","fengli":"微风级","type":"雷阵雨","date":"2015-08-07","week":"星期五"},{"hightemp":"30℃","fengxiang":"无持续风向","lowtemp":"22℃","fengli":"微风级","type":"雷阵雨","date":"2015-08-08","week":"星期六"},{"hightemp":"31℃","fengxiang":"无持续风向","lowtemp":"23℃","fengli":"微风级","type":"多云","date":"2015-08-09","week":"星期天"}]
         * today : {"hightemp":"32℃","index":[{"otherName":"","index":"","details":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","name":"感冒指数","code":"gm"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"防晒指数","code":"fs"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"穿衣指数","code":"ct"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"运动指数","code":"yd"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"洗车指数","code":"xc"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"晾晒指数","code":"ls"}],"fengxiang":"无持续风向","lowtemp":"22℃","curTemp":"29℃","aqi":"119","fengli":"微风级","type":"多云","date":"2015-08-05","week":"星期三"}
         * cityid : 101010100
         * city : 北京
         */
        @SerializedName("history")
        private List<HistoryEntity> history;
        @SerializedName("forecast")
        private List<ForecastEntity> forecast;
        @SerializedName("today")
        private TodayEntity today;
        @SerializedName("cityid")
        private String cityid;
        @SerializedName("city")
        private String city;

        public void setHistory(List<HistoryEntity> history) {
            this.history = history;
        }

        public void setForecast(List<ForecastEntity> forecast) {
            this.forecast = forecast;
        }

        public void setToday(TodayEntity today) {
            this.today = today;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<HistoryEntity> getHistory() {
            return history;
        }

        public List<ForecastEntity> getForecast() {
            return forecast;
        }

        public TodayEntity getToday() {
            return today;
        }

        public String getCityid() {
            return cityid;
        }

        public String getCity() {
            return city;
        }

        public class HistoryEntity {
            /**
             * hightemp : 30℃
             * fengxiang : 无持续风向
             * lowtemp : 21℃
             * aqi : 28
             * fengli : 微风级
             * type : 阵雨
             * date : 2015-08-03
             * week : 星期一
             */
            @SerializedName("hightemp")
            private String hightemp;
            @SerializedName("fengxiang")
            private String fengxiang;
            @SerializedName("lowtemp")
            private String lowtemp;
            @SerializedName("aqi")
            private String aqi;
            @SerializedName("fengli")
            private String fengli;
            @SerializedName("type")
            private String type;
            @SerializedName("date")
            private String date;
            @SerializedName("week")
            private String week;

            public void setHightemp(String hightemp) {
                this.hightemp = hightemp;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public void setLowtemp(String lowtemp) {
                this.lowtemp = lowtemp;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getHightemp() {
                return hightemp;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public String getLowtemp() {
                return lowtemp;
            }

            public String getAqi() {
                return aqi;
            }

            public String getFengli() {
                return fengli;
            }

            public String getType() {
                return type;
            }

            public String getDate() {
                return date;
            }

            public String getWeek() {
                return week;
            }
        }

        public class ForecastEntity {
            /**
             * hightemp : 30℃
             * fengxiang : 无持续风向
             * lowtemp : 22℃
             * fengli : 微风级
             * type : 多云
             * date : 2015-08-06
             * week : 星期四
             */
            @SerializedName("hightemp")
            private String hightemp;
            @SerializedName("fengxiang")
            private String fengxiang;
            @SerializedName("lowtemp")
            private String lowtemp;
            @SerializedName("fengli")
            private String fengli;
            @SerializedName("type")
            private String type;
            @SerializedName("date")
            private String date;
            @SerializedName("week")
            private String week;

            public void setHightemp(String hightemp) {
                this.hightemp = hightemp;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public void setLowtemp(String lowtemp) {
                this.lowtemp = lowtemp;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getHightemp() {
                return hightemp;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public String getLowtemp() {
                return lowtemp;
            }

            public String getFengli() {
                return fengli;
            }

            public String getType() {
                return type;
            }

            public String getDate() {
                return date;
            }

            public String getWeek() {
                return week;
            }
        }

        public class TodayEntity {
            /**
             * hightemp : 32℃
             * index : [{"otherName":"","index":"","details":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","name":"感冒指数","code":"gm"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"防晒指数","code":"fs"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"穿衣指数","code":"ct"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"运动指数","code":"yd"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"洗车指数","code":"xc"},{"otherName":"","index":"暂无","details":"指数信息暂时缺失","name":"晾晒指数","code":"ls"}]
             * fengxiang : 无持续风向
             * lowtemp : 22℃
             * curTemp : 29℃
             * aqi : 119
             * fengli : 微风级
             * type : 多云
             * date : 2015-08-05
             * week : 星期三
             */
            @SerializedName("hightemp")
            private String hightemp;
            @SerializedName("index")
            private List<IndexEntity> index;
            @SerializedName("fengxiang")
            private String fengxiang;
            @SerializedName("lowtemp")
            private String lowtemp;
            @SerializedName("curTemp")
            private String curTemp;
            @SerializedName("aqi")
            private String aqi;
            @SerializedName("fengli")
            private String fengli;
            @SerializedName("type")
            private String type;
            @SerializedName("date")
            private String date;
            @SerializedName("week")
            private String week;

            public void setHightemp(String hightemp) {
                this.hightemp = hightemp;
            }

            public void setIndex(List<IndexEntity> index) {
                this.index = index;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public void setLowtemp(String lowtemp) {
                this.lowtemp = lowtemp;
            }

            public void setCurTemp(String curTemp) {
                this.curTemp = curTemp;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getHightemp() {
                return hightemp;
            }

            public List<IndexEntity> getIndex() {
                return index;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public String getLowtemp() {
                return lowtemp;
            }

            public String getCurTemp() {
                return curTemp;
            }

            public String getAqi() {
                return aqi;
            }

            public String getFengli() {
                return fengli;
            }

            public String getType() {
                return type;
            }

            public String getDate() {
                return date;
            }

            public String getWeek() {
                return week;
            }

            public class IndexEntity {
                /**
                 * otherName :
                 * index :
                 * details : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
                 * name : 感冒指数
                 * code : gm
                 */
                @SerializedName("otherName")
                private String otherName;
                @SerializedName("index")
                private String index;
                @SerializedName("details")
                private String details;
                @SerializedName("name")
                private String name;
                @SerializedName("code")
                private String code;

                public void setOtherName(String otherName) {
                    this.otherName = otherName;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public void setDetails(String details) {
                    this.details = details;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getOtherName() {
                    return otherName;
                }

                public String getIndex() {
                    return index;
                }

                public String getDetails() {
                    return details;
                }

                public String getName() {
                    return name;
                }

                public String getCode() {
                    return code;
                }
            }
        }
    }
}
