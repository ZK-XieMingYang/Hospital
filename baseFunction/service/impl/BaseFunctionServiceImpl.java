package com.qhit.baseFunction.service.impl;

import com.qhit.baseFunction.service.IBaseFunctionService;
import java.util.List;
import com.qhit.baseFunction.dao.IBaseFunctionDao;
import com.qhit.baseFunction.dao.impl.BaseFunctionDaoImpl;
import com.qhit.baseFunction.pojo.BaseFunction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BaseFunctionServiceImpl  implements IBaseFunctionService {
    @Resource
    IBaseFunctionDao dao;

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
        BaseFunction baseFunction = findById(id); 
        return dao.delete(baseFunction); 
    } 


    @Override 
    public List findAll() { 
        String sql="SELECT * from base_function bf left JOIN base_module bm ON bf.mid = bm.mid";
        return dao.freeFind(sql);
    } 


    @Override 
    public BaseFunction findById(Object id) { 
        List<BaseFunction> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List<BaseFunction> findAllAjaxList(Integer mid,Integer userId) {
        String sql = "SELECT DISTINCT bf.* from base_function bf JOIN base_role_function brf ON" +
                " bf.fid=brf.fid\n" +
                "\t\t\t       JOIN base_role br ON br.rid=brf.rid\n" +
                "\t\t\t       JOIN base_user_role bur ON br.rid=bur.rid\n" +
                "\t\t\t       JOIN base_user bu ON bu.user_id=bur.uid\n" +
                "\t\t\t       JOIN base_module bm ON bf.mid=bm.mid AND bu.user_id="+userId+" AND bm.mid="+mid+";";
        return dao.freeFind(sql);
    }

    @Override
    public List search(BaseFunction baseFunction) {
        String sql= "SELECT * from base_function bf JOIN base_module bm ON bf.mid = bm.mid WHERE 1=1 ";
        if (!"".equals(baseFunction.getFname())&&baseFunction.getFname()!=null){
            sql+="and bf.fname LIKE '%"+baseFunction.getFname()+"%' ";
        }
        if (!"".equals(baseFunction.getBaseModule().getMname())&&baseFunction.getBaseModule().getMname()!=null){
            sql+="and bm.mname LIKE '%"+baseFunction.getBaseModule().getMname()+"%' ";
        }
        return dao.freeFind(sql);
    }


}