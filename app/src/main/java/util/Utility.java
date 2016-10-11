package util;

import android.text.TextUtils;

import db.CoolWeatherDB;
import model.City;
import model.County;
import model.Province;

/**
 * Created by 月满轩尼诗 on 2016/10/11.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */

    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response) {
        if (!TextUtils.isEmpty(response)) {
            String [] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String [] array =p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //强解析出来的数据存储到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {

        if (!TextUtils.isEmpty(response)) {
            String [] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String [] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProviceId(provinceId);
                    //强解析出来的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }

        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */

    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, final String response, int cityId) {

        if (!TextUtils.isEmpty(response)) {
            String [] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c :allCounties) {
                    String [] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //将解析的数据存储到County表
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }

        return false;
    }
}

