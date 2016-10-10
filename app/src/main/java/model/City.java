package model;

/*
 * Created by 月满轩尼诗 on 2016/10/10.
 */

public class City {
    private int id,proviceId;
    private String cityName,cityCode;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getProviceId() {
        return  proviceId;
    }
    public void setProviceId(int proviceId) {
        this.proviceId = proviceId;
    }
}
