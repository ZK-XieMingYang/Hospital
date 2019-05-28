package com.qhit.medicineCode.service;

import java.util.List;
import com.qhit.medicineCode.pojo.MedicineCode;


public interface IMedicineCodeService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    MedicineCode findById(Object id);

    boolean freeUpdate(String sql);

    List<MedicineCode> search(MedicineCode medicineCode);

}