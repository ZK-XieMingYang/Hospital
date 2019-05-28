package com.qhit.baseRole.dao.impl;

import com.qhit.baseRole.dao.IBaseRoleDao;
import com.qhit.utils.BaseDao;
import org.springframework.stereotype.Service;

import java.util.List;


public class BaseRoleDaoImpl extends BaseDao implements IBaseRoleDao {

    @Override 
    public List findAll() { 
        String sql = "select * from base_role"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findById(Object id) { 
        String sql = "select * from base_role where rid='"+id+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByRname(Object rname) { 
        String sql = "select * from base_role where rname='"+rname+"'"; 
        return freeFind(sql); 
    } 




}