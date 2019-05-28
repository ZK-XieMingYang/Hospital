package com.qhit.baseModule.pojo;




public class BaseModule {

    private Integer mid;    //模块ID
    private String mname;    //模块名称

    @Override
    public String toString() {
        return "BaseModule{" +
                "mid=" + mid +
                ", mname='" + mname + '\'' +
                '}';
    }

    public Integer getMid() {
        return mid;
    }
 
    public void setMid(Integer mid) { 
        this.mid = mid;
    }
 
    public String getMname() { 
        return mname;
    }
 
    public void setMname(String mname) { 
        this.mname = mname;
    }
 

 }