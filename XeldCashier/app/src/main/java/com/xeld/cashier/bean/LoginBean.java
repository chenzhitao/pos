package com.xeld.cashier.bean;



import com.xeld.cashier.easyhttp.CommonResultBean;
import com.xeld.cashier.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class LoginBean extends CommonResultBean {
    /**
     * data : {"createTime":null,"updatedTime":null,"startTime":null,"endTime":null,"page":1,"pageSize":10,"id":8,"userName":"shizhen","realName":"AAA",
     * "password":null,"policeId":null,"departmentId":4,"url":"","phone":"","email":null,"description":null,"ip":"119.96.48.175","roleId":2,"roleIds":[2],
     * "roleName":"管理员","funcIds":[127000,127001,100000,100001,100002,102000,102001,102002,102003,102004,103000,103001,103002,103003,103004,104000,104001,104002,104003,104004,105000,105001,
     * 105002,105003,105004,106000,106001,106002,106003,106004,106005,107000,107001,107002,107003,107004,109002,110000,110001,111000,111001,112000,112001,112002,113000,113001,113002,113003,114000,
     * 114001,114002,114003,114004,114005,115000,115001,115002,115003,115004,115005,116000,116001,116002,116003,116004,116005,117000,117001,118000,118001,119000,119001,120000,120001,120002,120003,
     * 120004,121000,121001,121002,121003,121004,122000,122001,122002,130001,200000,200001,300001,300002,400000,400001,500000,500001,500002,500003,500004,600000,600001,600002,600003,600004,700000,
     * 700001,700002,700003,700004],"roles":[{"createTime":null,"updatedTime":null,"startTime":null,"endTime":null,"page":1,"pageSize":10,"id":2,"name":"管理员","funcIds":null,"remark":null}],
     * "departmentName":null,"condition":null,"createUserId":1,
     * "accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlhjYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsImlwIj
     * oiSFNHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0iLCJ1c2VyVHlwZSI6IitwOWY0SStCenk2cnU2Ulo0M3VGOFE9PSIsImV4cCI6MTU5MDU3OTE2MSwib
     * mJmIjoxNTkwNTc3MzYxfQ.IrzzyW029xr_aoEDUUPV2sjuRHKZGlpjxN4nUqZpePA","refreshToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnB
     * RdWYrUlhjYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsImlwIjoiSFNHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0i
     * fQ.q_sgfHhdTWdOa9OI5QqVmDN4CABmAgtz6HBchrdruYA","jsonToken":{"accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlhj
     * YUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsImlwIjoiSFNHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0iLCJ1c2VyVH
     * lwZSI6IitwOWY0SStCenk2cnU2Ulo0M3VGOFE9PSIsImV4cCI6MTU5MDU3OTE2MSwibmJmIjoxNTkwNTc3MzYxfQ.IrzzyW029xr_aoEDUUPV2sjuRHKZGlpjxN4nUqZpePA",
     * "refreshToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlhjYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsIm
     * lwIjoiSFNHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0ifQ.q_sgfHhdTWdOa9OI5QqVmDN4CABmAgtz6HBchrdruYA"},
     * "areaIds":[1,12,6],"areaModelList":null,"departmentList":null,"collectCount":null,"ownedAddressIds":null,"source":null,"loginType":null,"status":null,"permit":1,
     * "privacyFlag":null,"quota":null,"loginStatus":null,"ownedAddressNames":null,"faces":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createTime : null
         * updatedTime : null
         * startTime : null
         * endTime : null
         * page : 1
         * pageSize : 10
         * id : 8
         * userName : shizhen
         * realName : AAA
         * password : null
         * policeId : null
         * departmentId : 4
         * url :
         * phone :
         * email : null
         * description : null
         * ip : 119.96.48.175
         * roleId : 2
         * roleIds : [2]
         * roleName : 管理员
         * funcIds : [127000,127001,100000,100001,100002,102000,102001,102002,102003,102004,103000,103001,103002,103003,103004,104000,104001,104002,104003,104004,105000,
         * 105001,105002,105003,105004,106000,106001,106002,106003,106004,106005,107000,107001,107002,107003,107004,109002,110000,110001,111000,111001,112000,112001,112002,
         * 113000,113001,113002,113003,114000,114001,114002,114003,114004,114005,115000,115001,115002,115003,115004,115005,116000,116001,116002,116003,116004,116005,117000,
         * 117001,118000,118001,119000,119001,120000,120001,120002,120003,120004,121000,121001,121002,121003,121004,122000,122001,122002,130001,200000,200001,300001,300002,
         * 400000,400001,500000,500001,500002,500003,500004,600000,600001,600002,600003,600004,700000,700001,700002,700003,700004]
         * roles : [{"createTime":null,"updatedTime":null,"startTime":null,"endTime":null,"page":1,"pageSize":10,"id":2,"name":"管理员","funcIds":null,"remark":null}]
         * departmentName : null
         * condition : null
         * createUserId : 1
         * accessToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlhjYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsImlwIjoiSF
         * NHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0iLCJ1c2VyVHlwZSI6IitwOWY0SStCenk2cnU2Ulo0M3VGOFE9PSIsImV4cCI6MTU5MDU3OTE2MSwibmJmIjoxNTkw
         * NTc3MzYxfQ.IrzzyW029xr_aoEDUUPV2sjuRHKZGlpjxN4nUqZpePA
         * refreshToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlhjYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsImlwIjoiSF
         * NHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0ifQ.q_sgfHhdTWdOa9OI5QqVmDN4CABmAgtz6HBchrdruYA
         * jsonToken : {"accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlhjYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9
         * PSIsImlwIjoiSFNHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0iLCJ1c2VyVHlwZSI6IitwOWY0SStCenk2cnU2Ulo0M3VGOFE9PSIsImV4cCI6MTU5MDU3OTE2MS
         * wibmJmIjoxNTkwNTc3MzYxfQ.IrzzyW029xr_aoEDUUPV2sjuRHKZGlpjxN4nUqZpePA","refreshToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlh
         * jYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsImlwIjoiSFNHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0ifQ.q_sgfHhdTWdOa9O
         * I5QqVmDN4CABmAgtz6HBchrdruYA"}
         * areaIds : [1,12,6]
         * areaModelList : null
         * departmentList : null
         * collectCount : null
         * ownedAddressIds : null
         * source : null
         * loginType : null
         * status : null
         * permit : 1
         * privacyFlag : null
         * quota : null
         * loginStatus : null
         * ownedAddressNames : null
         * faces : null
         */

        private String createTime;
        private String updatedTime;
        private String startTime;
        private String endTime;
        private int page;
        private int pageSize;
        private int id;
        private String userName;
        private String realName;
        private String password;
        private String policeId;
        private int departmentId;
        private String url;
        private String phone;
        private String email;
        private String description;
        private String ip;
        private int roleId;
        private String roleName;
        private String departmentName;
        private String condition;
        private int createUserId;
        private String accessToken;
        private String refreshToken;
        private JsonTokenBean jsonToken;
        private String areaModelList;
        private String departmentList;
        private String collectCount;
        private String ownedAddressIds;
        private String source;
        private String loginType;
        private String status;
        private int permit;
        private String privacyFlag;
        private String quota;
        private String loginStatus;
        private String ownedAddressNames;
        private String faces;
        private List<Integer> roleIds;
        private List<Integer> funcIds;
        private List<RolesBean> roles;
        private List<Integer> areaIds;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRealName() {
            return CommonUtils.ifStringEmpty(realName);
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPoliceId() {
            return policeId;
        }

        public void setPoliceId(String policeId) {
            this.policeId = policeId;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public JsonTokenBean getJsonToken() {
            return jsonToken;
        }

        public void setJsonToken(JsonTokenBean jsonToken) {
            this.jsonToken = jsonToken;
        }

        public String getAreaModelList() {
            return areaModelList;
        }

        public void setAreaModelList(String areaModelList) {
            this.areaModelList = areaModelList;
        }

        public String getDepartmentList() {
            return departmentList;
        }

        public void setDepartmentList(String departmentList) {
            this.departmentList = departmentList;
        }

        public String getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(String collectCount) {
            this.collectCount = collectCount;
        }

        public String getOwnedAddressIds() {
            return CommonUtils.ifStringEmpty(ownedAddressIds);
        }

        public void setOwnedAddressIds(String ownedAddressIds) {
            this.ownedAddressIds = ownedAddressIds;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getPermit() {
            return permit;
        }

        public void setPermit(int permit) {
            this.permit = permit;
        }

        public String getPrivacyFlag() {
            return privacyFlag;
        }

        public void setPrivacyFlag(String privacyFlag) {
            this.privacyFlag = privacyFlag;
        }

        public String getQuota() {
            return quota;
        }

        public void setQuota(String quota) {
            this.quota = quota;
        }

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
            this.loginStatus = loginStatus;
        }

        public String getOwnedAddressNames() {
            return ownedAddressNames;
        }

        public void setOwnedAddressNames(String ownedAddressNames) {
            this.ownedAddressNames = ownedAddressNames;
        }

        public String getFaces() {
            return faces;
        }

        public void setFaces(String faces) {
            this.faces = faces;
        }

        public List<Integer> getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(List<Integer> roleIds) {
            this.roleIds = roleIds;
        }

        public List<Integer> getFuncIds() {
            return funcIds;
        }

        public void setFuncIds(List<Integer> funcIds) {
            this.funcIds = funcIds;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public List<Integer> getAreaIds() {
            if (areaIds == null) {
                areaIds = new ArrayList<>();
            }
            return areaIds;
        }

        public void setAreaIds(List<Integer> areaIds) {
            this.areaIds = areaIds;
        }

        public static class JsonTokenBean {
            /**
             * accessToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlhjYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsImlwIjoi
             * SFNHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0iLCJ1c2VyVHlwZSI6IitwOWY0SStCenk2cnU2Ulo0M3VGOFE9PSIsImV4cCI6MTU5MDU3OTE2MSwibmJmIjo
             * xNTkwNTc3MzYxfQ.IrzzyW029xr_aoEDUUPV2sjuRHKZGlpjxN4nUqZpePA
             * refreshToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJGR2F4SUQ1dk5YWnBRdWYrUlhjYUh3PT0iLCJ1c2VyTmFtZSI6IldyS2crMlJnWmExc2VuY3dxS1I2NFE9PSIsImlwIj
             * oiSFNHUDZDQkM0UHVjYXdpNUk2WkNsdz09Iiwid3kiOiJHRDNEZUY3eE1sSVFiSkx4ZE5yRk1RPT0ifQ.q_sgfHhdTWdOa9OI5QqVmDN4CABmAgtz6HBchrdruYA
             */

            private String accessToken;
            private String refreshToken;

            public String getAccessToken() {
                return accessToken;
            }

            public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
            }

            public String getRefreshToken() {
                return refreshToken;
            }

            public void setRefreshToken(String refreshToken) {
                this.refreshToken = refreshToken;
            }
        }

        public static class RolesBean {
            /**
             * createTime : null
             * updatedTime : null
             * startTime : null
             * endTime : null
             * page : 1
             * pageSize : 10
             * id : 2
             * name : 管理员
             * funcIds : null
             * remark : null
             */

            private String createTime;
            private String updatedTime;
            private String startTime;
            private String endTime;
            private int page;
            private int pageSize;
            private int id;
            private String name;
            private String funcIds;
            private String remark;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdatedTime() {
                return updatedTime;
            }

            public void setUpdatedTime(String updatedTime) {
                this.updatedTime = updatedTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFuncIds() {
                return funcIds;
            }

            public void setFuncIds(String funcIds) {
                this.funcIds = funcIds;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
