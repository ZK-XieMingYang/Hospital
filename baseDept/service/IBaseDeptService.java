package com.qhit.baseDept.service;

import java.util.List;
import com.qhit.baseDept.pojo.BaseDept;


public interface IBaseDeptService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseDept findById(Object id);

    boolean freeUpdate(String sql);

    List<BaseDept> search(BaseDept baseDept);

}