package com.qhit.doctorMenu.service.impl;

import com.qhit.doctorMenu.service.IDoctorMenuService;
import java.util.List;
import com.qhit.doctorMenu.dao.IDoctorMenuDao;
import com.qhit.doctorMenu.dao.impl.DoctorMenuDaoImpl;
import com.qhit.doctorMenu.pojo.DoctorMenu;



public class DoctorMenuServiceImpl  implements IDoctorMenuService {

    IDoctorMenuDao dao = new DoctorMenuDaoImpl();

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
        DoctorMenu doctorMenu = findById(id); 
        return dao.delete(doctorMenu); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public DoctorMenu findById(Object id) { 
        List<DoctorMenu> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<DoctorMenu> search(DoctorMenu doctorMenu) {
            String sql = "select * from doctor_menu where 1=1 "; 
            if (doctorMenu.getMenuName()!=null && !"".equals(doctorMenu.getMenuName())){        
                sql+=" and menu_name like '%"+doctorMenu.getMenuName()+"%' ";        
            } 
            if (doctorMenu.getUserId()!=null && !"".equals(doctorMenu.getUserId())){        
                sql+=" and user_id like '%"+doctorMenu.getUserId()+"%' ";        
            } 
            if (doctorMenu.getDescription()!=null && !"".equals(doctorMenu.getDescription())){        
                sql+=" and description like '%"+doctorMenu.getDescription()+"%' ";        
            } 
            if (doctorMenu.getType()!=null && !"".equals(doctorMenu.getType())){        
                sql+=" and type like '%"+doctorMenu.getType()+"%' ";        
            } 
            List<DoctorMenu> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public boolean findByMenuName(String menuName) {
        if (menuName!=null&&menuName!=""){
            String sql = "SELECT * from doctor_menu where menu_name='"+menuName+"'";
            List<DoctorMenu> list = dao.freeFind(sql);
            if (list!=null&&list.size()>0){
                return true;
            }
        }
        return false;
    }


}