package com.qhit.medicineReqPlan.controller; 

import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.common.CommonUtil;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import com.qhit.medicineReqPlan.service.IMedicineReqPlanService; 
import com.qhit.medicineReqPlan.service.impl.MedicineReqPlanServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller 
@RequestMapping("/medicineReqPlan") 
public class MedicineReqPlanController { 

    IMedicineReqPlanService medicineReqPlanService = new MedicineReqPlanServiceImpl();; 

    @RequestMapping("/insert") 
    public String insert(MedicineReqPlan medicineReqPlan,HttpServletRequest request) {
        BaseUser sessinUser = (BaseUser) request.getSession().getAttribute("sessionUser");
        List<BaseRole> baseRoleList = sessinUser.getBaseRoleList();
        boolean flag = false;
        for(BaseRole bs:baseRoleList){
            if ("药房主任".equals(bs.getRname())){
                flag=true;
            }
        }
        if (flag){
        }else {
            medicineReqPlan.setAppUserid(sessinUser.getUserId());
            medicineReqPlan.setStatus(1);
            medicineReqPlan.setAppDate(CommonUtil.dateToString(new Date()));
        }
        medicineReqPlanService.insert(medicineReqPlan); 
        return "forward:UserList.action";
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer reqplnno, HttpServletResponse response) throws IOException { 
        medicineReqPlanService.delete(reqplnno); 
        return "forward:UserList.action";
    } 
 
    @RequestMapping("/update") 
    public String update(MedicineReqPlan medicineReqPlan) { 
        medicineReqPlanService.update(medicineReqPlan); 
        return "forward:UserList.action";
    }
    @RequestMapping("/apprvUpdata")
    public String apprvUpdata(HttpServletRequest request,HttpSession session) {
        IMedicineReqPlanService medicineReqPlanService = new MedicineReqPlanServiceImpl();
        String[] reqplnnos = request.getParameterValues("reqplnno");
        MedicineReqPlan medicineReqPlan = new MedicineReqPlan();
        for (String reqplnno:reqplnnos){
            medicineReqPlan.setReqplnno(Integer.parseInt(reqplnno));
            medicineReqPlan.setStatus(2);
            medicineReqPlan.setApprvDate(CommonUtil.dateToString(new Date()));
            medicineReqPlan.setApprvUserid(CommonUtil.getUserId(session));
            medicineReqPlanService.updateSelective(medicineReqPlan);
        }
        return "forward:apprvList.action";
    }

    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicineReqPlan medicineReqPlan) { 
        medicineReqPlanService.updateSelective(medicineReqPlan); 
        return "forward:UserList.action";
    } 
 
    @RequestMapping("/load") 
    public String load(Integer reqplnno, Model model) { 
        MedicineReqPlan medicineReqPlan = medicineReqPlanService.findById(reqplnno); 
        model.addAttribute("medicineReqPlan",medicineReqPlan); 
        return "medicineReqPlan/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<MedicineReqPlan> list = medicineReqPlanService.findAll(); 
        model.addAttribute("list",list); 
        return "medicineReqPlan/list"; 
    }
    @RequestMapping("/apprvList")
    public String apprvList(Model model) throws IOException {
        List<MedicineReqPlan> list = medicineReqPlanService.ApprvfindAll();
        model.addAttribute("list",list);
        return "medicineReqPlan/apprvList";
    }
    @RequestMapping("/UserList")
    public String UserList(Model model, HttpSession session) throws IOException {
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
        List<MedicineReqPlan> list = medicineReqPlanService.findAllByUserId(sessionUser.getUserId());
        model.addAttribute("list",list);
        return "medicineReqPlan/list";
    }
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicineReqPlan> list = medicineReqPlanService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicineReqPlan medicineReqPlan,Model model) { 
        List<MedicineReqPlan> list = medicineReqPlanService.search(medicineReqPlan); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicineReqPlan); 
        return "medicineReqPlan/list"; 
    } 
 
} 
