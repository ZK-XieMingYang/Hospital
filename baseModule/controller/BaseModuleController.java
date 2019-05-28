package com.qhit.baseModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.qhit.baseModule.pojo.BaseModule;
import com.qhit.baseModule.service.IBaseModuleService;
import com.qhit.baseModule.service.impl.BaseModuleServiceImpl;
import com.qhit.utils.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/baseModule")
public class BaseModuleController {
    MD5 md5 = new MD5();
    @Resource
    private IBaseModuleService baseModuleService;
    @RequestMapping("/ajaxlist")
    public void ajaxlist(HttpServletResponse response) throws IOException {
        List<BaseModule> list = baseModuleService.findAll();
        response.getWriter().write(JSONObject.toJSONString(list));
    }
    @RequestMapping("/list")
    public String list(HttpServletResponse response, Model model) throws IOException {
        List<BaseModule> list = baseModuleService.findAll();
        model.addAttribute("list",list);
        return "baseModule/list";
    }
}
