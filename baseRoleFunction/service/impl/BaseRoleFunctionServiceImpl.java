package com.qhit.baseRoleFunction.service.impl;

import com.qhit.baseRoleFunction.service.IBaseRoleFunctionService;
import java.util.List;
import com.qhit.baseRoleFunction.dao.IBaseRoleFunctionDao;
import com.qhit.baseRoleFunction.dao.impl.BaseRoleFunctionDaoImpl;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;



public class BaseRoleFunctionServiceImpl  implements IBaseRoleFunctionService {

    IBaseRoleFunctionDao dao = new BaseRoleFunctionDaoImpl();

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 


    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 


    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 


    @Override 
    public boolean delete(Object id) { 
        BaseRoleFunction baseRoleFunction = findById(id); 
        return dao.delete(baseRoleFunction); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseRoleFunction findById(Object id) { 
        List<BaseRoleFunction> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }

    @Override
    public List<BaseRoleFunction> findLeftpermissions(Integer rid) {
        String sql="SELECT * from base_function b WHERE b.fid NOT IN(\n" +
                "SELECT bf.fid FROM base_function bf JOIN base_role_function brf ON brf.fid=bf.fid WHERE " +
                "brf.rid="+rid+");";
        return dao.freeFind(sql);
    }

    @Override
    public List<BaseRoleFunction> findRightpermissions(Integer rid) {
        String sql="SELECT * from base_function b WHERE b.fid IN(\n" +
                "SELECT bf.fid FROM base_function bf JOIN base_role_function brf ON brf.fid=bf.fid WHERE " +
                "brf.rid="+rid+");";
        return dao.freeFind(sql);
    }
}