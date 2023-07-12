package com.xeld.cashier.utils.update;

public class UpdateBean {


    /**
     * data : {"minimumVersion":"V1.0","latestCode":5,"address":"http://113.57.119.203:8888/app-debug1.apk","latestVersion":"V6.1","minimumCode":1}
     * code : 200
     * message : null
     * success : true
     */

    private DataBean data;
    private int code;
    private String message;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        private String id;
        private String gmtCreated;
        private String gmtModified;
        private String isDeleted;
        private String type;
        private String latestVersion;
        private String minimumVersion;
        private String address;
        private int latestCode;
        private String minimumCode;
        private String remark;
        private String size;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGmtCreated() {
            return gmtCreated;
        }

        public void setGmtCreated(String gmtCreated) {
            this.gmtCreated = gmtCreated;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLatestVersion() {
            return latestVersion;
        }

        public void setLatestVersion(String latestVersion) {
            this.latestVersion = latestVersion;
        }

        public String getMinimumVersion() {
            return minimumVersion;
        }

        public void setMinimumVersion(String minimumVersion) {
            this.minimumVersion = minimumVersion;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getLatestCode() {
            return latestCode;
        }

        public void setLatestCode(int latestCode) {
            this.latestCode = latestCode;
        }

        public String getMinimumCode() {
            return minimumCode;
        }

        public void setMinimumCode(String minimumCode) {
            this.minimumCode = minimumCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }
}
