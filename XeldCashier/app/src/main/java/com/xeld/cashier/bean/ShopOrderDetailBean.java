package com.xeld.cashier.bean;

import com.google.gson.annotations.SerializedName;
import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;
import java.util.List;

public class ShopOrderDetailBean extends CommonResultBean {


    /**
     * data : {"records":[{"orderId":909,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,花生酥原味160g袋装","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356142387180736512","total":31.8,"actualTotal":31.8,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:28:36","updateTime":"2021-02-01 15:28:36","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":969,"shopId":1,"orderNumber":"1356142387180736512","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:28:36","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":970,"shopId":1,"orderNumber":"1356142387180736512","prodId":864,"skuId":470,"prodCount":1,"prodName":"花生酥原味160g袋装","skuName":"","pic":"2020/09/59062f7d4e4748ff851bc7f8415db274.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:28:36","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":908,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356141748392431616","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:26:03","updateTime":"2021-02-01 15:26:03","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":967,"shopId":1,"orderNumber":"1356141748392431616","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:26:03","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":968,"shopId":1,"orderNumber":"1356141748392431616","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:26:03","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":907,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356141627520978944","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:25:35","updateTime":"2021-02-01 15:25:35","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":965,"shopId":1,"orderNumber":"1356141627520978944","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:25:35","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":966,"shopId":1,"orderNumber":"1356141627520978944","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:25:35","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":906,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356140976959262720","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:22:59","updateTime":"2021-02-01 15:22:59","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":963,"shopId":1,"orderNumber":"1356140976959262720","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:22:59","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":964,"shopId":1,"orderNumber":"1356140976959262720","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:22:59","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":905,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356140716715282432","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:21:57","updateTime":"2021-02-01 15:21:57","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":961,"shopId":1,"orderNumber":"1356140716715282432","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:21:57","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":962,"shopId":1,"orderNumber":"1356140716715282432","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:21:57","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":904,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356136984288038912","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:07:39","updateTime":"2021-02-01 15:07:39","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":959,"shopId":1,"orderNumber":"1356136984288038912","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:07:20","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":960,"shopId":1,"orderNumber":"1356136984288038912","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:07:39","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":903,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356137015766290432","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:07:15","updateTime":"2021-02-01 15:07:15","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":957,"shopId":1,"orderNumber":"1356137015766290432","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:07:15","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":958,"shopId":1,"orderNumber":"1356137015766290432","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:07:15","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":902,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356136983025553408","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:07:09","updateTime":"2021-02-01 15:07:09","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":955,"shopId":1,"orderNumber":"1356136983025553408","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:07:09","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":956,"shopId":1,"orderNumber":"1356136983025553408","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:07:09","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":901,"shopId":1,"prodName":"小二啷当网红巴旦木奶枣120g/袋*3 共360g","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356133570351075328","total":29.9,"actualTotal":29.9,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":1,"createTime":"2021-02-01 14:53:34","updateTime":"2021-02-01 14:53:34","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":954,"shopId":1,"orderNumber":"1356133570351075328","prodId":1456,"skuId":1003,"prodCount":1,"prodName":"小二啷当网红巴旦木奶枣120g/袋*3 共360g","skuName":null,"pic":"2020/12/68349e957bc84e879f83e8aef1974eef.png","price":29.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":29.9,"recTime":"2021-02-01 14:53:34","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":900,"shopId":1,"prodName":"小二啷当网红巴旦木奶枣120g/袋*3 共360g","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1354274326039367680","total":59.8,"actualTotal":59.8,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-01-27 11:45:35","updateTime":"2021-01-27 11:45:35","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":953,"shopId":1,"orderNumber":"1354274326039367680","prodId":1456,"skuId":1003,"prodCount":2,"prodName":"小二啷当网红巴旦木奶枣120g/袋*3 共360g","skuName":null,"pic":"2020/12/68349e957bc84e879f83e8aef1974eef.png","price":29.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":59.8,"recTime":"2021-01-27 11:45:35","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"}],"total":157,"size":10,"current":1,"searchCount":true,"pages":16}
     * resultMsg : null
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * records : [{"orderId":909,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,花生酥原味160g袋装","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356142387180736512","total":31.8,"actualTotal":31.8,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:28:36","updateTime":"2021-02-01 15:28:36","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":969,"shopId":1,"orderNumber":"1356142387180736512","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:28:36","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":970,"shopId":1,"orderNumber":"1356142387180736512","prodId":864,"skuId":470,"prodCount":1,"prodName":"花生酥原味160g袋装","skuName":"","pic":"2020/09/59062f7d4e4748ff851bc7f8415db274.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:28:36","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":908,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356141748392431616","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:26:03","updateTime":"2021-02-01 15:26:03","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":967,"shopId":1,"orderNumber":"1356141748392431616","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:26:03","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":968,"shopId":1,"orderNumber":"1356141748392431616","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:26:03","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":907,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356141627520978944","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:25:35","updateTime":"2021-02-01 15:25:35","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":965,"shopId":1,"orderNumber":"1356141627520978944","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:25:35","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":966,"shopId":1,"orderNumber":"1356141627520978944","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:25:35","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":906,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356140976959262720","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:22:59","updateTime":"2021-02-01 15:22:59","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":963,"shopId":1,"orderNumber":"1356140976959262720","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:22:59","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":964,"shopId":1,"orderNumber":"1356140976959262720","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:22:59","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":905,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356140716715282432","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:21:57","updateTime":"2021-02-01 15:21:57","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":961,"shopId":1,"orderNumber":"1356140716715282432","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:21:57","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":962,"shopId":1,"orderNumber":"1356140716715282432","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:21:57","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":904,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356136984288038912","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:07:39","updateTime":"2021-02-01 15:07:39","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":959,"shopId":1,"orderNumber":"1356136984288038912","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:07:20","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":960,"shopId":1,"orderNumber":"1356136984288038912","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:07:39","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":903,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356137015766290432","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:07:15","updateTime":"2021-02-01 15:07:15","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":957,"shopId":1,"orderNumber":"1356137015766290432","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:07:15","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":958,"shopId":1,"orderNumber":"1356137015766290432","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:07:15","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":902,"shopId":1,"prodName":"法式脆片奶香味 90g/袋,凤梨酥 200/袋","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356136983025553408","total":29.7,"actualTotal":29.7,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-02-01 15:07:09","updateTime":"2021-02-01 15:07:09","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":955,"shopId":1,"orderNumber":"1356136983025553408","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:07:09","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":956,"shopId":1,"orderNumber":"1356136983025553408","prodId":1419,"skuId":966,"prodCount":1,"prodName":"凤梨酥 200/袋","skuName":null,"pic":"2020/09/e0d9a2f96e6646749e10bcb78d186bc4.jpg","price":13.8,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":13.8,"recTime":"2021-02-01 15:07:09","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":901,"shopId":1,"prodName":"小二啷当网红巴旦木奶枣120g/袋*3 共360g","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1356133570351075328","total":29.9,"actualTotal":29.9,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":1,"createTime":"2021-02-01 14:53:34","updateTime":"2021-02-01 14:53:34","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":954,"shopId":1,"orderNumber":"1356133570351075328","prodId":1456,"skuId":1003,"prodCount":1,"prodName":"小二啷当网红巴旦木奶枣120g/袋*3 共360g","skuName":null,"pic":"2020/12/68349e957bc84e879f83e8aef1974eef.png","price":29.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":29.9,"recTime":"2021-02-01 14:53:34","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"},{"orderId":900,"shopId":1,"prodName":"小二啷当网红巴旦木奶枣120g/袋*3 共360g","userId":"70086cb7a8504f6b854b4ade9f9088a3","orderNumber":"1354274326039367680","total":59.8,"actualTotal":59.8,"payType":null,"remarks":null,"status":1,"refundState":0,"orderState":0,"dvyType":null,"dvyId":null,"dvyFlowId":"","freightAmount":0,"addrOrderId":null,"productNums":2,"createTime":"2021-01-27 11:45:35","updateTime":"2021-01-27 11:45:35","payTime":null,"dvyTime":null,"finallyTime":null,"cancelTime":null,"isPayed":0,"deleteStatus":0,"refundSts":0,"reduceAmount":null,"shopName":null,"recordId":null,"shopRecordId":null,"deliveryTime":null,"pickupTime":null,"orderItems":[{"orderItemId":953,"shopId":1,"orderNumber":"1354274326039367680","prodId":1456,"skuId":1003,"prodCount":2,"prodName":"小二啷当网红巴旦木奶枣120g/袋*3 共360g","skuName":null,"pic":"2020/12/68349e957bc84e879f83e8aef1974eef.png","price":29.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":59.8,"recTime":"2021-01-27 11:45:35","commSts":0,"distributionCardNo":null,"basketDate":null}],"userAddrOrder":null,"shopDetail":null,"tjr":null,"refundReason":null,"isSelfRais":0,"refundImg":null,"refundExplain":null,"phone":null,"deleteType":0,"poiSeq":0,"distributionFlag":0,"distributionType":0,"deliveryPrice":0,"street":null,"ptglid":null,"ptstatus":null,"cashier":1,"originalOrderId":null,"receiver":null,"cashierName":"admin"}]
         * total : 157
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 16
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean implements Serializable {
            /**
             * orderId : 909
             * shopId : 1
             * prodName : 法式脆片奶香味 90g/袋,花生酥原味160g袋装
             * userId : 70086cb7a8504f6b854b4ade9f9088a3
             * orderNumber : 1356142387180736512
             * total : 31.8
             * actualTotal : 31.8
             * payType : null
             * remarks : null
             * status : 1
             * refundState : 0
             * orderState : 0
             * dvyType : null
             * dvyId : null
             * dvyFlowId :
             * freightAmount : 0.0
             * addrOrderId : null
             * productNums : 2
             * createTime : 2021-02-01 15:28:36
             * updateTime : 2021-02-01 15:28:36
             * payTime : null
             * dvyTime : null
             * finallyTime : null
             * cancelTime : null
             * isPayed : 0
             * deleteStatus : 0
             * refundSts : 0
             * reduceAmount : null
             * shopName : null
             * recordId : null
             * shopRecordId : null
             * deliveryTime : null
             * pickupTime : null
             * orderItems : [{"orderItemId":969,"shopId":1,"orderNumber":"1356142387180736512","prodId":1418,"skuId":965,"prodCount":1,"prodName":"法式脆片奶香味 90g/袋","skuName":null,"pic":"2020/09/a65998c917c746e6a95afb571d28df75.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:28:36","commSts":0,"distributionCardNo":null,"basketDate":null},{"orderItemId":970,"shopId":1,"orderNumber":"1356142387180736512","prodId":864,"skuId":470,"prodCount":1,"prodName":"花生酥原味160g袋装","skuName":"","pic":"2020/09/59062f7d4e4748ff851bc7f8415db274.jpg","price":15.9,"userId":"70086cb7a8504f6b854b4ade9f9088a3","productTotalAmount":15.9,"recTime":"2021-02-01 15:28:36","commSts":0,"distributionCardNo":null,"basketDate":null}]
             * userAddrOrder : null
             * shopDetail : null
             * tjr : null
             * refundReason : null
             * isSelfRais : 0
             * refundImg : null
             * refundExplain : null
             * phone : null
             * deleteType : 0
             * poiSeq : 0
             * distributionFlag : 0
             * distributionType : 0
             * deliveryPrice : 0.0
             * street : null
             * ptglid : null
             * ptstatus : null
             * cashier : 1
             * originalOrderId : null
             * receiver : null
             * cashierName : admin
             */

            private int orderId;
            private int shopId;
            private String prodName;
            private String userId;
            private String orderNumber;
            private double total;
            private double actualTotal;
            private int payType;
            private Object remarks;
            private int status;
            private int refundState;
            private int orderState;
            private Object dvyType;
            private Object dvyId;
            private String dvyFlowId;
            private double freightAmount;
            private Object addrOrderId;
            private int productNums;
            private String createTime;
            private String updateTime;
            private Object payTime;
            private Object dvyTime;
            private Object finallyTime;
            private Object cancelTime;
            private int isPayed;
            private int deleteStatus;
            private int refundSts;
            private Object reduceAmount;
            private Object shopName;
            private Object recordId;
            private Object shopRecordId;
            private Object deliveryTime;
            private Object pickupTime;
            private Object userAddrOrder;
            private Object shopDetail;
            private Object tjr;
            private Object refundReason;
            private int isSelfRais;
            private Object refundImg;
            private Object refundExplain;
            private String phone;
            private String nikeName;
            private String userPhone;

            private int deleteType;
            private int poiSeq;
            private int distributionFlag;
            private int distributionType;
            private double deliveryPrice;
            private Object street;
            private Object ptglid;
            private Object ptstatus;
            private int cashier;
            private Object originalOrderId;
            private Object receiver;
            private String cashierName;
            private List<OrderItemsBean> orderItems;
            private int mixedPayment;

            private String userMobile; //会员手机号码

            public String getUserMobile() {
                return userMobile;
            }

            public void setUserMobile(String userMobile) {
                this.userMobile = userMobile;
            }

            public int getMixedPayment() {
                return mixedPayment;
            }

            public void setMixedPayment(int mixedPayment) {
                this.mixedPayment = mixedPayment;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getProdName() {
                return prodName;
            }

            public void setProdName(String prodName) {
                this.prodName = prodName;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }

            public double getTotal() {
                return total;
            }

            public void setTotal(double total) {
                this.total = total;
            }

            public double getActualTotal() {
                return actualTotal;
            }

            public void setActualTotal(double actualTotal) {
                this.actualTotal = actualTotal;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getRefundState() {
                return refundState;
            }

            public void setRefundState(int refundState) {
                this.refundState = refundState;
            }

            public int getOrderState() {
                return orderState;
            }

            public void setOrderState(int orderState) {
                this.orderState = orderState;
            }

            public Object getDvyType() {
                return dvyType;
            }

            public void setDvyType(Object dvyType) {
                this.dvyType = dvyType;
            }

            public Object getDvyId() {
                return dvyId;
            }

            public void setDvyId(Object dvyId) {
                this.dvyId = dvyId;
            }

            public String getDvyFlowId() {
                return dvyFlowId;
            }

            public void setDvyFlowId(String dvyFlowId) {
                this.dvyFlowId = dvyFlowId;
            }

            public double getFreightAmount() {
                return freightAmount;
            }

            public void setFreightAmount(double freightAmount) {
                this.freightAmount = freightAmount;
            }

            public Object getAddrOrderId() {
                return addrOrderId;
            }

            public void setAddrOrderId(Object addrOrderId) {
                this.addrOrderId = addrOrderId;
            }

            public int getProductNums() {
                return productNums;
            }

            public void setProductNums(int productNums) {
                this.productNums = productNums;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public Object getDvyTime() {
                return dvyTime;
            }

            public void setDvyTime(Object dvyTime) {
                this.dvyTime = dvyTime;
            }

            public Object getFinallyTime() {
                return finallyTime;
            }

            public void setFinallyTime(Object finallyTime) {
                this.finallyTime = finallyTime;
            }

            public Object getCancelTime() {
                return cancelTime;
            }

            public void setCancelTime(Object cancelTime) {
                this.cancelTime = cancelTime;
            }

            public int getIsPayed() {
                return isPayed;
            }

            public void setIsPayed(int isPayed) {
                this.isPayed = isPayed;
            }

            public int getDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(int deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public int getRefundSts() {
                return refundSts;
            }

            public void setRefundSts(int refundSts) {
                this.refundSts = refundSts;
            }

            public Object getReduceAmount() {
                return reduceAmount;
            }

            public void setReduceAmount(Object reduceAmount) {
                this.reduceAmount = reduceAmount;
            }

            public Object getShopName() {
                return shopName;
            }

            public void setShopName(Object shopName) {
                this.shopName = shopName;
            }

            public Object getRecordId() {
                return recordId;
            }

            public void setRecordId(Object recordId) {
                this.recordId = recordId;
            }

            public Object getShopRecordId() {
                return shopRecordId;
            }

            public void setShopRecordId(Object shopRecordId) {
                this.shopRecordId = shopRecordId;
            }

            public Object getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(Object deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public Object getPickupTime() {
                return pickupTime;
            }

            public void setPickupTime(Object pickupTime) {
                this.pickupTime = pickupTime;
            }

            public Object getUserAddrOrder() {
                return userAddrOrder;
            }

            public void setUserAddrOrder(Object userAddrOrder) {
                this.userAddrOrder = userAddrOrder;
            }

            public Object getShopDetail() {
                return shopDetail;
            }

            public void setShopDetail(Object shopDetail) {
                this.shopDetail = shopDetail;
            }

            public Object getTjr() {
                return tjr;
            }

            public void setTjr(Object tjr) {
                this.tjr = tjr;
            }

            public Object getRefundReason() {
                return refundReason;
            }

            public void setRefundReason(Object refundReason) {
                this.refundReason = refundReason;
            }

            public int getIsSelfRais() {
                return isSelfRais;
            }

            public void setIsSelfRais(int isSelfRais) {
                this.isSelfRais = isSelfRais;
            }

            public Object getRefundImg() {
                return refundImg;
            }

            public void setRefundImg(Object refundImg) {
                this.refundImg = refundImg;
            }

            public Object getRefundExplain() {
                return refundExplain;
            }

            public void setRefundExplain(Object refundExplain) {
                this.refundExplain = refundExplain;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getDeleteType() {
                return deleteType;
            }

            public void setDeleteType(int deleteType) {
                this.deleteType = deleteType;
            }

            public int getPoiSeq() {
                return poiSeq;
            }

            public void setPoiSeq(int poiSeq) {
                this.poiSeq = poiSeq;
            }

            public int getDistributionFlag() {
                return distributionFlag;
            }

            public void setDistributionFlag(int distributionFlag) {
                this.distributionFlag = distributionFlag;
            }

            public int getDistributionType() {
                return distributionType;
            }

            public void setDistributionType(int distributionType) {
                this.distributionType = distributionType;
            }

            public double getDeliveryPrice() {
                return deliveryPrice;
            }

            public void setDeliveryPrice(double deliveryPrice) {
                this.deliveryPrice = deliveryPrice;
            }

            public Object getStreet() {
                return street;
            }

            public void setStreet(Object street) {
                this.street = street;
            }

            public Object getPtglid() {
                return ptglid;
            }

            public void setPtglid(Object ptglid) {
                this.ptglid = ptglid;
            }

            public Object getPtstatus() {
                return ptstatus;
            }

            public void setPtstatus(Object ptstatus) {
                this.ptstatus = ptstatus;
            }

            public int getCashier() {
                return cashier;
            }

            public void setCashier(int cashier) {
                this.cashier = cashier;
            }

            public Object getOriginalOrderId() {
                return originalOrderId;
            }

            public void setOriginalOrderId(Object originalOrderId) {
                this.originalOrderId = originalOrderId;
            }

            public Object getReceiver() {
                return receiver;
            }

            public void setReceiver(Object receiver) {
                this.receiver = receiver;
            }

            public String getCashierName() {
                return cashierName;
            }

            public void setCashierName(String cashierName) {
                this.cashierName = cashierName;
            }

            public List<OrderItemsBean> getOrderItems() {
                return orderItems;
            }

            public void setOrderItems(List<OrderItemsBean> orderItems) {
                this.orderItems = orderItems;
            }

            public String getNikeName() {
                return nikeName;
            }

            public void setNikeName(String nikeName) {
                this.nikeName = nikeName;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public static class OrderItemsBean implements Serializable {
                /**
                 * orderItemId : 969
                 * shopId : 1
                 * orderNumber : 1356142387180736512
                 * prodId : 1418
                 * skuId : 965
                 * prodCount : 1
                 * prodName : 法式脆片奶香味 90g/袋
                 * skuName : null
                 * pic : 2020/09/a65998c917c746e6a95afb571d28df75.jpg
                 * price : 15.9
                 * userId : 70086cb7a8504f6b854b4ade9f9088a3
                 * productTotalAmount : 15.9
                 * recTime : 2021-02-01 15:28:36
                 * commSts : 0
                 * distributionCardNo : null
                 * basketDate : null
                 */

                private int orderItemId;
                private int shopId;
                private String orderNumber;
                private int prodId;
                private int skuId;
                private int prodCount;
                private String prodName;
                private Object skuName;
                private String pic;
                private double price;
                private String userId;
                private double productTotalAmount;
                private String recTime;
                private int commSts;
                private Object distributionCardNo;
                private Object basketDate;
                private int vipDiscount;

                public int getVipDiscount() {
                    return vipDiscount;
                }

                public void setVipDiscount(int vipDiscount) {
                    this.vipDiscount = vipDiscount;
                }

                public int getOrderItemId() {
                    return orderItemId;
                }

                public void setOrderItemId(int orderItemId) {
                    this.orderItemId = orderItemId;
                }

                public int getShopId() {
                    return shopId;
                }

                public void setShopId(int shopId) {
                    this.shopId = shopId;
                }

                public String getOrderNumber() {
                    return orderNumber;
                }

                public void setOrderNumber(String orderNumber) {
                    this.orderNumber = orderNumber;
                }

                public int getProdId() {
                    return prodId;
                }

                public void setProdId(int prodId) {
                    this.prodId = prodId;
                }

                public int getSkuId() {
                    return skuId;
                }

                public void setSkuId(int skuId) {
                    this.skuId = skuId;
                }

                public int getProdCount() {
                    return prodCount;
                }

                public void setProdCount(int prodCount) {
                    this.prodCount = prodCount;
                }

                public String getProdName() {
                    return prodName;
                }

                public void setProdName(String prodName) {
                    this.prodName = prodName;
                }

                public Object getSkuName() {
                    return skuName;
                }

                public void setSkuName(Object skuName) {
                    this.skuName = skuName;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public double getProductTotalAmount() {
                    return productTotalAmount;
                }

                public void setProductTotalAmount(double productTotalAmount) {
                    this.productTotalAmount = productTotalAmount;
                }

                public String getRecTime() {
                    return recTime;
                }

                public void setRecTime(String recTime) {
                    this.recTime = recTime;
                }

                public int getCommSts() {
                    return commSts;
                }

                public void setCommSts(int commSts) {
                    this.commSts = commSts;
                }

                public Object getDistributionCardNo() {
                    return distributionCardNo;
                }

                public void setDistributionCardNo(Object distributionCardNo) {
                    this.distributionCardNo = distributionCardNo;
                }

                public Object getBasketDate() {
                    return basketDate;
                }

                public void setBasketDate(Object basketDate) {
                    this.basketDate = basketDate;
                }

                @Override
                public String toString() {
                    return "OrderItemsBean{" +
                            "orderItemId=" + orderItemId +
                            ", shopId=" + shopId +
                            ", orderNumber='" + orderNumber + '\'' +
                            ", prodId=" + prodId +
                            ", skuId=" + skuId +
                            ", prodCount=" + prodCount +
                            ", prodName='" + prodName + '\'' +
                            ", skuName=" + skuName +
                            ", pic='" + pic + '\'' +
                            ", price=" + price +
                            ", userId='" + userId + '\'' +
                            ", productTotalAmount=" + productTotalAmount +
                            ", recTime='" + recTime + '\'' +
                            ", commSts=" + commSts +
                            ", distributionCardNo=" + distributionCardNo +
                            ", basketDate=" + basketDate +
                            '}';
                }
            }
        }
    }
}
