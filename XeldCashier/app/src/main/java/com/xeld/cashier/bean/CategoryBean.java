package com.xeld.cashier.bean;

import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;
import java.util.List;


public class CategoryBean extends CommonResultBean {


    /**
     * data : [{"categoryId":106,"shopId":1,"parentId":0,"categoryName":"花生酥","icon":null,"pic":"2020/07/95fdef6d467e45fab4e3df8989f6d348.jpg","seq":8,"status":1,"recTime":"2020-07-01 19:35:49","grade":0,"updateTime":"2020-08-29 14:20:18","brandIds":null,"attributeIds":null,"brands":null,"prodProps":null,"products":null,"categories":null},{"categoryId":107,"shopId":1,"parentId":0,"categoryName":"牛轧饼","icon":null,"pic":"2020/07/e5e97adb3fb941db8faffcce8dc17f48.jpg","seq":5,"status":1,"recTime":"2020-07-01 19:36:03","grade":0,"updateTime":"2020-07-23 17:27:46","brandIds":null,"attributeIds":null,"brands":null,"prodProps":null,"products":null,"categories":null},{"categoryId":114,"shopId":1,"parentId":0,"categoryName":"店铺招牌","icon":null,"pic":"2020/07/ab3cdb32047346b1b585e9e11732c3c0.jpg","seq":1,"status":1,"recTime":"2020-07-06 09:33:33","grade":0,"updateTime":"2020-07-23 17:28:40","brandIds":null,"attributeIds":null,"brands":null,"prodProps":null,"products":null,"categories":null},{"categoryId":115,"shopId":1,"parentId":0,"categoryName":"新品推荐","icon":null,"pic":"2020/07/1fb966a4665b42f0a77f86fecc630792.jpg","seq":2,"status":1,"recTime":"2020-07-06 09:34:13","grade":0,"updateTime":"2020-07-28 14:24:27","brandIds":null,"attributeIds":null,"brands":null,"prodProps":null,"products":null,"categories":null},{"categoryId":118,"shopId":1,"parentId":0,"categoryName":"蛋黄酥","icon":null,"pic":"2020/07/d152f72438d9497a84c1f0691667d63d.png","seq":5,"status":1,"recTime":"2020-07-08 20:32:48","grade":0,"updateTime":"2020-08-29 14:20:33","brandIds":null,"attributeIds":null,"brands":null,"prodProps":null,"products":null,"categories":null},{"categoryId":131,"shopId":1,"parentId":0,"categoryName":"牛轧奶芙","icon":null,"pic":"2020/07/18b2dfabfc904bbfb245757539c401e3.jpg","seq":6,"status":1,"recTime":"2020-07-23 17:27:24","grade":0,"updateTime":null,"brandIds":null,"attributeIds":null,"brands":null,"prodProps":null,"products":null,"categories":null},{"categoryId":159,"shopId":1,"parentId":0,"categoryName":"活动专区","icon":null,"pic":null,"seq":3,"status":1,"recTime":"2020-08-13 09:31:50","grade":0,"updateTime":"2020-08-14 14:20:40","brandIds":null,"attributeIds":null,"brands":null,"prodProps":null,"products":null,"categories":null},{"categoryId":556,"shopId":1,"parentId":0,"categoryName":"休闲零食","icon":null,"pic":null,"seq":5,"status":1,"recTime":"2020-09-23 17:36:58","grade":0,"updateTime":null,"brandIds":null,"attributeIds":null,"brands":null,"prodProps":null,"products":null,"categories":null}]
     * resultMsg : null
     */

    private List<CategoryBean.DataBean> data;

    public List<CategoryBean.DataBean> getData() {
        return data;
    }

    public void setData(List<CategoryBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * categoryId : 106
         * shopId : 1
         * parentId : 0
         * categoryName : 花生酥
         * icon : null
         * pic : 2020/07/95fdef6d467e45fab4e3df8989f6d348.jpg
         * seq : 8
         * status : 1
         * recTime : 2020-07-01 19:35:49
         * grade : 0
         * updateTime : 2020-08-29 14:20:18
         * brandIds : null
         * attributeIds : null
         * brands : null
         * prodProps : null
         * products : null
         * categories : null
         */


        private int categoryId;
        private int shopId;
        private int parentId;
        private String categoryName;
        private Object icon;
        private String pic;
        private int seq;
        private int status;
        private String recTime;
        private int grade;
        private String updateTime;
        private Object brandIds;
        private Object attributeIds;
        private Object brands;
        private Object prodProps;
        private Object products;
        private Object categories;

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRecTime() {
            return recTime;
        }

        public void setRecTime(String recTime) {
            this.recTime = recTime;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getBrandIds() {
            return brandIds;
        }

        public void setBrandIds(Object brandIds) {
            this.brandIds = brandIds;
        }

        public Object getAttributeIds() {
            return attributeIds;
        }

        public void setAttributeIds(Object attributeIds) {
            this.attributeIds = attributeIds;
        }

        public Object getBrands() {
            return brands;
        }

        public void setBrands(Object brands) {
            this.brands = brands;
        }

        public Object getProdProps() {
            return prodProps;
        }

        public void setProdProps(Object prodProps) {
            this.prodProps = prodProps;
        }

        public Object getProducts() {
            return products;
        }

        public void setProducts(Object products) {
            this.products = products;
        }

        public Object getCategories() {
            return categories;
        }

        public void setCategories(Object categories) {
            this.categories = categories;
        }
    }
}
