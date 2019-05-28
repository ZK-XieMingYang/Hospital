package com.qhit.baseRoleFunction.dao;

import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;
import com.qhit.utils.BaseDao;
import java.util.List;



public interface IBaseRoleFunctionDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List findByRid(Object rid);

    List findByFid(Object fid);

}