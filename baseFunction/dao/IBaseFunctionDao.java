package com.qhit.baseFunction.dao;

import com.qhit.utils.BaseDao;
import java.util.List;



public interface IBaseFunctionDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    List findByFname(Object fname);

    List findByMid(Object mid);

    List findByUrl(Object url);

}