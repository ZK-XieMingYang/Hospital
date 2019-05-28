package com.qhit.baseRole.pojo;


import org.springframework.context.annotation.Description;



public class BaseRole {

    private Integer rid;    //角色ID
    private String rname;    //角色名称
    public Integer getRid() { 
        return rid;
    }
 
    public void setRid(Integer rid) { 
        this.rid = rid;
    }
 
    public String getRname() { 
        return rname;
    }
 
    public void setRname(String rname) { 
        this.rname = rname;
    }
 

 }