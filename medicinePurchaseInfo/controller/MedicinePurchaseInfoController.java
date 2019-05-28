package com.qhit.medicinePurchaseInfo.controller; 

import com.qhit.common.CommonUtil;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService; 
import com.qhit.medicinePurchaseInfo.service.impl.MedicinePurchaseInfoServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller 
@RequestMapping("/medicinePurchaseInfo") 
public class MedicinePurchaseInfoController { 

    IMedicinePurchaseInfoService medicinePurchaseInfoService = new MedicinePurchaseInfoServiceImpl();; 

    @RequestMapping("/insert") 
    public String insert(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.insert(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer pchId, HttpServletResponse response) throws IOException { 
        medicinePurchaseInfoService.delete(pchId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.update(medicinePurchaseInfo); 
        return "forward:list.action"; 
    }
    @RequestMapping("/apprvUpdata")
    public String apprvUpdata(HttpServletRequest request,HttpSession session) {
        IMedicinePurchaseInfoService iMedicinePurchaseInfoService = new MedicinePurchaseInfoServiceImpl();
        String[] pchIds = request.getParameterValues("pchId");
        MedicinePurchaseInfo medicinePurchaseInfo = new MedicinePurchaseInfo();
        for (String pchId:pchIds){
            medicinePurchaseInfo.setPchId(Integer.parseInt(pchId));
            medicinePurchaseInfo.setStatus(2);
            medicinePurchaseInfo.setApprvDate(CommonUtil.dateToString(new Date()));
            medicinePurchaseInfo.setApprvUserid(CommonUtil.getUserId(session));
            iMedicinePurchaseInfoService.updateSelective(medicinePurchaseInfo);
        }
        return "forward:apprvList.action";
    }
    @RequestMapping("/collect")
    public String collect(HttpSession session) {
        medicinePurchaseInfoService.collect(session);
        return "forward:userlist.action";
    }
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.updateSelective(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer pchId, Model model) { 
        MedicinePurchaseInfo medicinePurchaseInfo = medicinePurchaseInfoService.findById(pchId); 
        model.addAttribute("medicinePurchaseInfo",medicinePurchaseInfo); 
        return "medicinePurchaseInfo/edit"; 
    } 
 
    @RequestMapping("/list")
    public String list(Model model) throws IOException { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll();
        model.addAttribute("list",list);
        return "medicinePurchaseInfo/list"; 
    }
    @RequestMapping("/userList")
    public String userList(Model model,HttpSession session) throws IOException {
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAllByUserId(session);
        model.addAttribute("list",list);
        return "medicinePurchaseInfo/list";
    }
    @RequestMapping("/apprvList")
    public String apprvList(Model model,HttpSession session) throws IOException {
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findByApprv(session);
        model.addAttribute("list",list);
        return "medicinePurchaseInfo/apprvList";
    }
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicinePurchaseInfo medicinePurchaseInfo,Model model) { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.search(medicinePurchaseInfo); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicinePurchaseInfo); 
        return "medicinePurchaseInfo/list"; 
    } 
 
} 
