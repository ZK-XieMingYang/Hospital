package com.qhit.baseDept.dao;

import com.qhit.utils.BaseDao;
import java.util.List;



public interface IBaseDeptDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List findByDeptName(Object deptName);

}