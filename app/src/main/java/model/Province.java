package model;

/*
 * Created by 月满轩尼诗 on 2016/10/10.
 */

public class Province {
    private int id;
    private String provinceName,provinceCode;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

}