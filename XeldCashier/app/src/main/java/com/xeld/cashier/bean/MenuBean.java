package com.xeld.cashier.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhicheng.liu on 2018/4/4
 * address :liuzhicheng@sunmi.com
 * description :
 */

public class MenuBean implements Parcelable {


    /**
     * title : Sunmi 收银演示程序
     * head : {"param1":"序号","param2":"商品名","param3":"单价"}
     * flag : true
     * list : [{"param1":"1","param2":"橙汁","param3":"¥5.50"},{"param1":"2","param2":"橙汁","param3":"¥5.50"}]
     * KVPList : [{"name":"总计 ","value":"11.00"},{"name":"优惠 ","value":"0.00"},{"name":"数量 ","value":"2"},{"name":"应收 ","value":"11.00"}]
     */

    private String title;
    private HeadBean head;
    private String flag;
    private List<ListBean> list;
    private List<KVPListBean> KVPList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<KVPListBean> getKVPList() {
        return KVPList;
    }

    public void setKVPList(List<KVPListBean> KVPList) {
        this.KVPList = KVPList;
    }

    public static class HeadBean implements Parcelable {

        /**
         * param1 : 序号
         * param2 : 商品名
         * param3 : 单价
         */

        private String param1;
        private String param2;
        private String param3;

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }

        public String getParam2() {
            return param2;
        }

        public void setParam2(String param2) {
            this.param2 = param2;
        }

        public String getParam3() {
            return param3;
        }

        public void setParam3(String param3) {
            this.param3 = param3;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.param1);
            dest.writeString(this.param2);
            dest.writeString(this.param3);
        }

        public HeadBean() {
        }

        @Override
        public String toString() {
            return "HeadBean{" +
                    "param1='" + param1 + '\'' +
                    ", param2='" + param2 + '\'' +
                    ", param3='" + param3 + '\'' +
                    '}';
        }

        protected HeadBean(Parcel in) {
            this.param1 = in.readString();
            this.param2 = in.readString();
            this.param3 = in.readString();
        }

        public static final Creator<HeadBean> CREATOR = new Creator<HeadBean>() {
            @Override
            public HeadBean createFromParcel(Parcel source) {
                return new HeadBean(source);
            }

            @Override
            public HeadBean[] newArray(int size) {
                return new HeadBean[size];
            }
        };
    }

    public static class ListBean implements Parcelable {
        /**
         * param1 : 1
         * param2 : 橙汁
         * param3 : ¥5.50
         */

        private String param1;
        private String param2;
        private String param3;
        private int type;
        private String code;
        private int net;

        public int getNet() {
            return net;
        }

        public void setNet(int net) {
            this.net = net;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }

        public String getParam2() {
            return param2;
        }

        public void setParam2(String param2) {
            this.param2 = param2;
        }

        public String getParam3() {
            return param3;
        }

        public void setParam3(String param3) {
            this.param3 = param3;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.param1);
            dest.writeString(this.param2);
            dest.writeString(this.param3);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.param1 = in.readString();
            this.param2 = in.readString();
            this.param3 = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };

        @Override
        public String toString() {
            return "ListBean{" +
                    "param1='" + param1 + '\'' +
                    ", param2='" + param2 + '\'' +
                    ", param3='" + param3 + '\'' +
                    '}';
        }
    }

    public static class KVPListBean implements Parcelable {
        /**
         * name : 总计
         * value : 11.00
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.value);
        }

        public KVPListBean() {
        }

        protected KVPListBean(Parcel in) {
            this.name = in.readString();
            this.value = in.readString();
        }

        public static final Creator<KVPListBean> CREATOR = new Creator<KVPListBean>() {
            @Override
            public KVPListBean createFromParcel(Parcel source) {
                return new KVPListBean(source);
            }

            @Override
            public KVPListBean[] newArray(int size) {
                return new KVPListBean[size];
            }
        };

        @Override
        public String toString() {
            return "KVPListBean{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.head, flags);
        dest.writeString(this.flag);
        dest.writeList(this.list);
        dest.writeList(this.KVPList);
    }

    public MenuBean() {
    }

    protected MenuBean(Parcel in) {
        this.title = in.readString();
        this.head = in.readParcelable(HeadBean.class.getClassLoader());
        this.flag = in.readString();
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
        this.KVPList = new ArrayList<KVPListBean>();
        in.readList(this.KVPList, KVPListBean.class.getClassLoader());
    }

    public static final Creator<com.xeld.cashier.bean.MenuBean> CREATOR = new Creator<com.xeld.cashier.bean.MenuBean>() {
        @Override
        public com.xeld.cashier.bean.MenuBean createFromParcel(Parcel source) {
            return new com.xeld.cashier.bean.MenuBean(source);
        }

        @Override
        public com.xeld.cashier.bean.MenuBean[] newArray(int size) {
            return new com.xeld.cashier.bean.MenuBean[size];
        }
    };

    @Override
    public String toString() {
        return "MenuBean{" +
                "title='" + title + '\'' +
                ", head=" + head +
                ", flag='" + flag + '\'' +
                ", list=" + list +
                ", KVPList=" + KVPList +
                '}';
    }
}
