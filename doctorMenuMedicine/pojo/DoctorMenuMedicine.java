package com.qhit.doctorMenuMedicine.pojo;


import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.medicineCode.pojo.MedicineCode;
import com.sun.org.glassfish.gmbal.Description;



public class DoctorMenuMedicine {

    private Integer mdId;    //主键
    private Integer menuId;    //套餐
    private Integer codeId;    //药品
    private Integer amt;    //数量
    @Description("bean")
    private MedicineCode medicineCode;
    @Description("bean")
    private DoctorMenu doctorMenu;

    public MedicineCode getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(MedicineCode medicineCode) {
        this.medicineCode = medicineCode;
    }

    public DoctorMenu getDoctorMenu() {
        return doctorMenu;
    }

    public void setDoctorMenu(DoctorMenu doctorMenu) {
        this.doctorMenu = doctorMenu;
    }

    public Integer getMdId() {
        return mdId;
    }
 
    public void setMdId(Integer mdId) { 
        this.mdId = mdId;
    }
 
    public Integer getMenuId() { 
        return menuId;
    }
 
    public void setMenuId(Integer menuId) { 
        this.menuId = menuId;
    }
 
    public Integer getCodeId() { 
        return codeId;
    }
 
    public void setCodeId(Integer codeId) { 
        this.codeId = codeId;
    }
 
    public Integer getAmt() { 
        return amt;
    }
 
    public void setAmt(Integer amt) { 
        this.amt = amt;
    }
 

 }