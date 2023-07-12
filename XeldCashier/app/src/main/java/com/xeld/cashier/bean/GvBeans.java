package com.xeld.cashier.bean;



/**
 * Created by highsixty on 2018/3/13.
 * mail  gaolulin@sunmi.com
 */
public class GvBeans {

    private Long id;

    private int imgId;
    private String imgUrl;
    private String name;
    private String price;
    private String code;
    private int mode;
    private int logo;
    private int number;
    private String unit;

    public GvBeans(int imgId, String name, String price, String code, int mode) {
        this.imgId = imgId;
        this.name = name;
        this.price = price;
        this.code = code;
        this.mode = mode;
    }

    public GvBeans(String imgUrl, String name, String price, String code, int number, String unit, int mode) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
        this.code = code;
        this.mode = mode;
        this.number = number;
        this.unit = unit;
    }

    public GvBeans(int imgId, String name, String price, String code, int number, String unit, int mode) {
        this.imgId = imgId;
        this.name = name;
        this.price = price;
        this.code = code;
        this.mode = mode;
        this.number = number;
        this.unit = unit;
    }

    public GvBeans(Long id, int imgId, String imgUrl, String name, String price,
                   String code, int mode, int logo, int number, String unit) {
        this.id = id;
        this.imgId = imgId;
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
        this.code = code;
        this.mode = mode;
        this.logo = logo;
        this.number = number;
        this.unit = unit;
    }

    public GvBeans() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getImgId() {
        return this.imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getMode() {
        return this.mode;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }
    public int getLogo() {
        return this.logo;
    }
    public void setLogo(int logo) {
        this.logo = logo;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

 



}
