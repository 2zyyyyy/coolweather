package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.City;
import model.County;
import model.Province;

/*
 * Created by 月满轩尼诗 on 2016/10/10.
 */

public class CoolWeatherDB {
    /**
     * 数据库名
     */

    public static final String DB_NAME = "cool_weather";

    /**
     * 数据库版本
     */

    public static final int VERSION = 1;

    private static  CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;

    /**
     * 将构造方法私有化
     */

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null ,VERSION);//4个参数
        db =dbHelper.getWritableDatabase();
    }

    /**
     * 获取CoolWeatherDB的实例
     */

    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB ==null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    /**
     * 将Province实例存储到数据库
     */

    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            //组合数据
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            //插入数据
            db.insert("Province", null, values);//三个参数
        }
    }

    /**
     * 从数据库读取全国所有的省份信息
     */

    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    /**
     * 将City实例存储到数据库
     */

    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityName());
            values.put("province_id", city.getProviceId());
            db.insert("City", null, values);
        }
    }

    /**
     * 从数据库读取某省份下所有的城市信息
     */

    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[] {String.valueOf(provinceId)}, null, null , null);
        if (cursor.moveToNext()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProviceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
                if (cursor != null) {
                    cursor.close();
                }
        return list;
    }

    /**
     * 将County实例存储到数据库
     */

    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getId());
            db.insert("County", null, values);
        }
    }

    /**
     * 从数据库读取某城市下所有县信息
     */

    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id = ?", new String[] {String.valueOf(cityId)}, null, null ,null);
        if (cursor.moveToNext()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }
        if (cursor != null){
            cursor.close();
        }
        return list;
    }
}
