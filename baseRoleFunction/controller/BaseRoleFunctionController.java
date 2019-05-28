package com.qhit.baseRoleFunction.controller;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;
import com.qhit.baseRoleFunction.service.IBaseRoleFunctionService;
import com.qhit.baseRoleFunction.service.impl.BaseRoleFunctionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/baseRoleFunction")
public class BaseRoleFunctionController {
    private IBaseRoleFunctionService baseRoleFunctionService=new BaseRoleFunctionServiceImpl();
    @RequestMapping("/permissions")
    public String permissions(Model model , Integer rid) throws IOException {
        List<BaseRoleFunction> leftlist =  baseRoleFunctionService.findLeftpermissions(rid);
        List<BaseRoleFunction> rightlist =  baseRoleFunctionService.findRightpermissions(rid);
        model.addAttribute("leftlist",leftlist);
        model.addAttribute("rightlist",rightlist);
        model.addAttribute("rid",rid);
        return "baseRoleFunction/permissions";
    }
    @RequestMapping("/permissionsUpdate")
    public String permissionsUpdate(HttpServletRequest request, Integer rid){
        IBaseRoleFunctionService baseRoleFunctionService = new BaseRoleFunctionServiceImpl();
        String sql = "delete from base_role_function where rid="+rid;
        baseRoleFunctionService.freeUpdate(sql);
        String[] fids = null;
        try {
            fids = request.getParameterValues("fid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fids!=null&&fids.length>0){
            for (String i:fids) {
                BaseRoleFunction baseRoleFunction = new BaseRoleFunction();
                baseRoleFunction.setRid(rid);
                baseRoleFunction.setFid(Integer.parseInt(i));
                baseRoleFunctionService.insert(baseRoleFunction);
            }
        }
        return "redirect:/baseRole/list.action";
    }
}
