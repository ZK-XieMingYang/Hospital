package com.qhit.baseRole.service.impl;

import com.qhit.baseRole.service.IBaseRoleService;
import java.util.List;
import com.qhit.baseRole.dao.IBaseRoleDao;
import com.qhit.baseRole.dao.impl.BaseRoleDaoImpl;
import com.qhit.baseRole.pojo.BaseRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BaseRoleServiceImpl  implements IBaseRoleService {
    IBaseRoleDao dao =new BaseRoleDaoImpl() ;

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
        BaseRole baseRole = findById(id); 
        return dao.delete(baseRole); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseRole findById(Object id) { 
        List<BaseRole> list = dao.findById(id); 
        return  list.get(0); 
    }
    @Override
    public List<BaseRole> findLeftdistribute(Integer userId) {
        String sql = "SELECT * from base_role r WHERE r.rid NOT IN( \n" +
                "SELECT bur.rid from base_user_role bur JOIN base_role " +
                "br ON bur.rid = br.rid WHERE bur.uid="+userId+")";
        return dao.freeFind(sql);
    }

    @Override
    public List<BaseRole> findRightdistribute(Integer userId) {
        String sql = "SELECT * from base_role r WHERE r.rid IN( \n" +
                "SELECT bur.rid from base_user_role bur JOIN base_role br ON bur.rid = br.rid WHERE bur.uid="+userId+")";
        return dao.freeFind(sql);
    }

}