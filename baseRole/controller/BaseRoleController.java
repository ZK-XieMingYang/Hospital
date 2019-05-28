package com.qhit.baseRole.controller;

import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRole.service.IBaseRoleService;
import com.qhit.baseRole.service.impl.BaseRoleServiceImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/baseRole")
public class BaseRoleController {
    private IBaseRoleService iBaseRoleService =new BaseRoleServiceImpl() ;
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<BaseRole> list = iBaseRoleService.findAll();
        request.setAttribute("list",list);
        return "baseRole/list";
    }
    @RequestMapping("/insert")
    public String list(BaseRole baseRole){
        iBaseRoleService.insert(baseRole);
        return "forward:list.action";
    }
    @RequestMapping("/del")
    public String del(BaseRole baseRole,HttpServletRequest request){
        boolean qx = (boolean) request.getAttribute("qx");
        if (!qx){
            return "error:authority.action";
        }
        iBaseRoleService.delete(baseRole.getRid());
        return "forward:list.action";
    }
    @RequestMapping("/load")
    public String load(BaseRole baseRole, Model model){
        System.out.println(baseRole.getRid());
        BaseRole baserole = iBaseRoleService.findById(baseRole.getRid());
        model.addAttribute("baserole",baserole);
        return "baseRole/edit";
    }
    @RequestMapping("/update")
    public String updata(BaseRole baseRole){
        iBaseRoleService.update(baseRole);
        return "forward:list.action";
    }
    @RequestMapping("/distribute")
    public String distribute(Model model , Integer userId) throws IOException {
        List<BaseRole> leftlist =  iBaseRoleService.findLeftdistribute(userId);
        List<BaseRole> rightlist =  iBaseRoleService.findRightdistribute(userId);
        model.addAttribute("leftlist",leftlist);
        model.addAttribute("rightlist",rightlist);
        model.addAttribute("userId",userId);
         return "baseRole/distribute";
    }
    }
