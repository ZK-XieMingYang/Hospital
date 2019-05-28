package com.qhit.doctorMenu.controller; 

import com.alibaba.fastjson.JSONObject;
import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.doctorMenu.service.IDoctorMenuService; 
import com.qhit.doctorMenu.service.impl.DoctorMenuServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import com.alibaba.fastjson.JSON; 
import java.io.IOException; 
import java.util.List;

import static javafx.scene.input.KeyCode.Y;


@Controller 
@RequestMapping("/doctorMenu") 
public class DoctorMenuController { 

    IDoctorMenuService doctorMenuService = new DoctorMenuServiceImpl();; 

    @RequestMapping("/insert") 
    public String insert(DoctorMenu doctorMenu) {
        doctorMenuService.insert(doctorMenu); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer menuId, HttpServletResponse response) throws IOException { 
        doctorMenuService.delete(menuId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(DoctorMenu doctorMenu) { 
        doctorMenuService.update(doctorMenu); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(DoctorMenu doctorMenu) { 
        doctorMenuService.updateSelective(doctorMenu); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer menuId, Model model) { 
        DoctorMenu doctorMenu = doctorMenuService.findById(menuId); 
        model.addAttribute("doctorMenu",doctorMenu); 
        return "doctorMenu/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<DoctorMenu> list = doctorMenuService.findAll(); 
        model.addAttribute("list",list); 
        return "doctorMenu/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response,String menuName) throws IOException {
        boolean flag = doctorMenuService.findByMenuName(menuName);
        response.getWriter().write(flag?"Y":"N");
    } 
 
    @RequestMapping("/search") 
    public String search(DoctorMenu doctorMenu,Model model) { 
        List<DoctorMenu> list = doctorMenuService.search(doctorMenu); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",doctorMenu); 
        return "doctorMenu/list"; 
    } 
 
} 
