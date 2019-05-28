package com.qhit.baseUserRole.controller;

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/baseUserRole")
public class BaseUserRoleController {
    @RequestMapping("/distributeUpdate")
    public String distributeUpdate(HttpServletRequest request, Integer userId){
        IBaseUserRoleService baseUserRoleService = new BaseUserRoleServiceImpl();
        String sql = "delete from base_user_role where uid="+userId;
        baseUserRoleService.freeUpdate(sql);
        String[] rids = null;
        try {
            rids = request.getParameterValues("rid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rids!=null&&rids.length>0){
            for (String i:rids) {
                BaseUserRole baseUserRole = new BaseUserRole();
                baseUserRole.setUid(userId);
                baseUserRole.setRid(Integer.parseInt(i));
                baseUserRoleService.insert(baseUserRole);
            }
        }
        return "redirect:/baseUser/list.action";
    }
}
