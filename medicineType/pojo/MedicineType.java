package com.qhit.medicineType.pojo;




public class MedicineType {

    private Integer typeId;    //生产厂商ID
    private String typeName;    //类别名称
    private String remark;    //备注

    public Integer getTypeId() { 
        return typeId;
    }
 
    public void setTypeId(Integer typeId) { 
        this.typeId = typeId;
    }
 
    public String getTypeName() { 
        return typeName;
    }
 
    public void setTypeName(String typeName) { 
        this.typeName = typeName;
    }
 
    public String getRemark() { 
        return remark;
    }
 
    public void setRemark(String remark) { 
        this.remark = remark;
    }
 

 }