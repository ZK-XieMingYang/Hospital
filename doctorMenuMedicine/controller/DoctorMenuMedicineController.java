package com.qhit.doctorMenuMedicine.controller; 

import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.doctorMenu.service.IDoctorMenuService;
import com.qhit.doctorMenu.service.impl.DoctorMenuServiceImpl;
import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine;
import com.qhit.doctorMenuMedicine.service.IDoctorMenuMedicineService; 
import com.qhit.doctorMenuMedicine.service.impl.DoctorMenuMedicineServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import com.alibaba.fastjson.JSON; 
import java.io.IOException; 
import java.util.List; 


@Controller 
@RequestMapping("/doctorMenuMedicine") 
public class DoctorMenuMedicineController { 

    IDoctorMenuMedicineService doctorMenuMedicineService = new DoctorMenuMedicineServiceImpl();; 
    IDoctorMenuService iDoctorMenuService = new DoctorMenuServiceImpl();
    @RequestMapping("/insert") 
    public String insert(DoctorMenuMedicine doctorMenuMedicine) { 
        doctorMenuMedicineService.insert(doctorMenuMedicine); 
        return "forward:MenuIdList.action";
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer mdId, HttpServletResponse response) throws IOException { 
        doctorMenuMedicineService.delete(mdId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(DoctorMenuMedicine doctorMenuMedicine) { 
        doctorMenuMedicineService.update(doctorMenuMedicine); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(DoctorMenuMedicine doctorMenuMedicine) { 
        doctorMenuMedicineService.updateSelective(doctorMenuMedicine); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer mdId, Model model) { 
        DoctorMenuMedicine doctorMenuMedicine = doctorMenuMedicineService.findById(mdId); 
        model.addAttribute("doctorMenuMedicine",doctorMenuMedicine); 
        return "doctorMenuMedicine/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<DoctorMenuMedicine> list = doctorMenuMedicineService.findAll(); 
        model.addAttribute("list",list); 
        return "doctorMenuMedicine/list"; 
    }
    @RequestMapping("/MenuIdList")
    public String MenuIdList(Model model,int menuId) throws IOException {
        List<DoctorMenuMedicine> list = doctorMenuMedicineService.findAllByMenuId(menuId);
        DoctorMenu doctorMenu = iDoctorMenuService.findById(menuId);
        model.addAttribute("doctorMenu",doctorMenu);
        model.addAttribute("list",list);
        return "doctorMenuMedicine/list";
    }
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<DoctorMenuMedicine> list = doctorMenuMedicineService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(DoctorMenuMedicine doctorMenuMedicine,Model model) { 
        List<DoctorMenuMedicine> list = doctorMenuMedicineService.search(doctorMenuMedicine); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",doctorMenuMedicine); 
        return "doctorMenuMedicine/list"; 
    } 
 
} 
