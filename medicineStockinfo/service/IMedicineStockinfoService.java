package com.qhit.medicineStockinfo.service;

import java.util.List;
import com.qhit.medicineStockinfo.pojo.MedicineStockinfo;


public interface IMedicineStockinfoService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    MedicineStockinfo findById(Object id);

    boolean freeUpdate(String sql);

    List<MedicineStockinfo> search(MedicineStockinfo medicineStockinfo);

}