package com.qhit.medicinePurchaseInfo.service.impl;

import com.qhit.common.CommonUtil;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService;

import java.util.Date;
import java.util.List;
import com.qhit.medicinePurchaseInfo.dao.IMedicinePurchaseInfoDao;
import com.qhit.medicinePurchaseInfo.dao.impl.MedicinePurchaseInfoDaoImpl;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import com.qhit.medicineReqPlan.service.IMedicineReqPlanService;
import com.qhit.medicineReqPlan.service.impl.MedicineReqPlanServiceImpl;

import javax.servlet.http.HttpSession;


public class MedicinePurchaseInfoServiceImpl  implements IMedicinePurchaseInfoService {

    IMedicinePurchaseInfoDao dao = new MedicinePurchaseInfoDaoImpl();

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
        MedicinePurchaseInfo medicinePurchaseInfo = findById(id); 
        return dao.delete(medicinePurchaseInfo); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public MedicinePurchaseInfo findById(Object id) { 
        List<MedicinePurchaseInfo> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<MedicinePurchaseInfo> search(MedicinePurchaseInfo medicinePurchaseInfo) {
            String sql = "select * from medicine_purchase_info where 1=1 "; 
            if (medicinePurchaseInfo.getMedicineCodeid()!=null && !"".equals(medicinePurchaseInfo.getMedicineCodeid())){        
                sql+=" and MEDICINE_CODEID like '%"+medicinePurchaseInfo.getMedicineCodeid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getManCode()!=null && !"".equals(medicinePurchaseInfo.getManCode())){        
                sql+=" and MAN_CODE like '%"+medicinePurchaseInfo.getManCode()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchAmt()!=null && !"".equals(medicinePurchaseInfo.getPchAmt())){        
                sql+=" and PCH_AMT like '%"+medicinePurchaseInfo.getPchAmt()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchPrice()!=null && !"".equals(medicinePurchaseInfo.getPchPrice())){        
                sql+=" and PCH_PRICE like '%"+medicinePurchaseInfo.getPchPrice()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchTotal()!=null && !"".equals(medicinePurchaseInfo.getPchTotal())){        
                sql+=" and PCH_TOTAL like '%"+medicinePurchaseInfo.getPchTotal()+"%' ";        
            } 
            if (medicinePurchaseInfo.getStatus()!=null && !"".equals(medicinePurchaseInfo.getStatus())){        
                sql+=" and STATUS like '%"+medicinePurchaseInfo.getStatus()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchUserid()!=null && !"".equals(medicinePurchaseInfo.getPchUserid())){        
                sql+=" and PCH_USERID like '%"+medicinePurchaseInfo.getPchUserid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchDate()!=null && !"".equals(medicinePurchaseInfo.getPchDate())){        
                sql+=" and PCH_DATE like '%"+medicinePurchaseInfo.getPchDate()+"%' ";        
            } 
            if (medicinePurchaseInfo.getApprvUserid()!=null && !"".equals(medicinePurchaseInfo.getApprvUserid())){        
                sql+=" and APPRV_USERID like '%"+medicinePurchaseInfo.getApprvUserid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getApprvDate()!=null && !"".equals(medicinePurchaseInfo.getApprvDate())){        
                sql+=" and APPRV_DATE like '%"+medicinePurchaseInfo.getApprvDate()+"%' ";        
            } 
            List<MedicinePurchaseInfo> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public void collect(HttpSession session) {
        String sql1="UPDATE medicine_req_plan SET STATUS=3 WHERE STATUS=2";
        String sql2="SELECT mrp.MEDICINE_CODEID,CAST(SUM(mrp.REQAMT) AS CHAR)AS sumamt\n" +
                "from medicine_req_plan mrp \n" +
                "\t\t\t\tJOIN medicine_code mc ON mrp.MEDICINE_CODEID=mc.code_id " +
                "WHERE mrp.STATUS=2 GROUP BY mrp.MEDICINE_CODEID;";
        List<MedicineReqPlan> list = dao.freeFind(sql2);
        dao.freeUpdate(sql1);
        MedicinePurchaseInfo medicinePurchaseInfo = new MedicinePurchaseInfo();
        for(MedicineReqPlan oj:list){
            medicinePurchaseInfo.setPchAmt(Integer.parseInt(oj.getSumamt()));
            medicinePurchaseInfo.setMedicineCodeid(oj.getMedicineCodeid());
            medicinePurchaseInfo.setStatus(1);
            medicinePurchaseInfo.setPchDate(CommonUtil.dateToString(new Date()));
            medicinePurchaseInfo.setPchUserid(CommonUtil.getUserId(session));
            dao.insert(medicinePurchaseInfo);
        }
    }

    @Override
    public List<MedicinePurchaseInfo> findAllByUserId(HttpSession session) {
        String sql = "SELECT * from medicine_purchase_info mpi LEFT JOIN base_manufacturer " +
                "bm ON mpi.MAN_CODE=bm.man_Code\n" +
                "\t\t\t\t\t JOIN medicine_code mc ON mpi.MEDICINE_CODEID=mc.code_id\n"
                +
                "\t\t\t\t\t LEFT JOIN base_user bu ON mpi.APPRV_USERID=bu.user_id WHERE " +
                "mpi.PCH_USERID = "+CommonUtil.getUserId(session)+"";
        List<MedicinePurchaseInfo> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<MedicinePurchaseInfo> findByApprv(HttpSession session) {
        String sql= "SELECT * from medicine_purchase_info mpi LEFT JOIN base_manufacturer bm " +
                "ON mpi.MAN_CODE=bm.man_Code\n" +
                "\t\t\t\t\t JOIN medicine_code mc ON mpi.MEDICINE_CODEID=mc.code_id\n" +
                "\t\t\t\t\t LEFT JOIN base_user bu ON mpi.PCH_USERID=bu.user_id";
        return dao.freeFind(sql);
    }


}