package com.qhit.baseModule.dao;

import com.qhit.utils.BaseDao;
import java.util.List;



public interface IBaseModuleDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    List findByMname(Object mname);

}