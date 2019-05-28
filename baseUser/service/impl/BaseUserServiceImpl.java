package com.qhit.baseUser.service.impl;

import com.qhit.baseUser.service.IBaseUserService;
import java.util.List;
import com.qhit.baseUser.dao.IBaseUserDao;
import com.qhit.baseUser.dao.impl.BaseUserDaoImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.utils.MD5;



public class BaseUserServiceImpl  implements IBaseUserService {

    IBaseUserDao dao = new BaseUserDaoImpl();

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
        BaseUser baseUser = findById(id); 
        return dao.delete(baseUser); 
    } 


    @Override 
    public List findAll() {
        String sql = "SELECT * from base_user bu left JOIN base_dept bt ON bt.dept_id=bu.dept_id;";
        return dao.freeFind(sql);
    } 


    @Override 
    public BaseUser findById(Object id) { 
        List<BaseUser> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List search(BaseUser baseUser) {
        String sql= "SELECT * from base_user WHERE 1=1 ";
        if (!"".equals(baseUser.getCname())&&baseUser.getCname()!=null){
            sql+="and cname like '%"+baseUser.getCname()+"%' ";
        }
        if (!"".equals(baseUser.getUserName())&&baseUser.getUserName()!=null){
            sql+="and user_name like '%"+baseUser.getUserName()+"%' ";
        }
        if (!"".equals(baseUser.getSex())&&baseUser.getSex()!=null){
            sql+="and sex like '%"+baseUser.getSex()+"%'";
        }
        List<BaseUser> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public BaseUser login(BaseUser baseUser) {
        MD5 md5 = new MD5();
        String password = md5.getMD5ofStr(baseUser.getPassword());
        String sql = "SELECT * from base_user bu LEFT JOIN base_user_role bur ON bu.user_id=bur.uid\n" +
                "\t\t\t   LEFT JOIN base_role br ON bur.rid=br.rid\n" +
                "\t\t\t   LEFT JOIN base_role_function brf ON brf.rid=br.rid\n" +
                "\t\t\t   LEFT JOIN base_function bf ON bf.fid=brf.fid\n" +
                "\t\t\t   WHERE bu.user_name='"+baseUser.getUserName()+"' AND " +
                "bu.password='"+password+"'";
        List<BaseUser> list = dao.freeFind(sql);
        if (list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean findByIdAndPassword(BaseUser baseUser) {
        String sql = "SELECT * from base_user bu LEFT JOIN base_user_role bur ON bu.user_id=bur.uid\n" +
                "\t\t\t   LEFT JOIN base_role br ON bur.rid=br.rid\n" +
                "\t\t\t   LEFT JOIN base_role_function brf ON brf.rid=br.rid\n" +
                "\t\t\t   LEFT JOIN base_function bf ON bf.fid=brf.fid\n" +
                "\t\t\t   WHERE bu.user_name='"+baseUser.getUserName()+"' AND " +
                "bu.password='"+baseUser.getPassword()+"'";
        List<BaseUser> list = dao.freeFind(sql);
        if (list!=null&&list.size()>0){
            return true;
        }
        return false;
    }


}