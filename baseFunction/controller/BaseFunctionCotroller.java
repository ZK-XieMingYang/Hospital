package com.qhit.baseFunction.controller;

import com.alibaba.fastjson.JSONObject;
import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseFunction.service.IBaseFunctionService;
import com.qhit.baseFunction.service.impl.BaseFunctionServiceImpl;
import com.qhit.baseModule.pojo.BaseModule;
import com.qhit.utils.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/baseFunction")
public class BaseFunctionCotroller {
    MD5 md5 = new MD5();
    @Resource
    private IBaseFunctionService baseFunctionService;

    @RequestMapping("/list")
    public String list(Model model){
        List<BaseFunction> list = baseFunctionService.findAll();
        model.addAttribute("list",list);
        return "baseFunction/list";
    }
    @RequestMapping("/ajaxlist")
    public void ajaxlist(HttpServletResponse response,Integer mid,Integer userId) throws IOException {
        if (mid==null){
            mid=1;
        }
        List<BaseFunction> list = baseFunctionService.findAllAjaxList(mid,userId);
        response.getWriter().write(JSONObject.toJSONString(list));
    }
    @RequestMapping("/insert")
    public String list(BaseFunction baseFunction){
        baseFunctionService.insert(baseFunction);
        return "forward:list.action";
    }
    @RequestMapping("/search")
    public String search(String fname,String mname,Model model){
        BaseFunction baseFunction = new BaseFunction();
        BaseModule baseModule = new BaseModule();
        baseModule.setMname(mname);
        baseFunction.setFname(fname);
        baseFunction.setBaseModule(baseModule);
        List list = baseFunctionService.search(baseFunction);
        model.addAttribute("searchObject",baseFunction);
        model.addAttribute("list",list);
        return "baseFunction/list";
    }
    @RequestMapping("/load")
    public String load(BaseFunction baseFunction, Model model){
        BaseFunction baseFunctionDdit = baseFunctionService.findById(baseFunction.getFid());
        model.addAttribute("baseFunctionDdit",baseFunctionDdit);
        return "baseFunction/edit";
    }
    @RequestMapping("/update")
    public String updata(BaseFunction baseFunction){
        baseFunctionService.update(baseFunction);
        return "forward:list.action";
    }
    @RequestMapping("/del")
    public String del(BaseFunction baseFunction){
        baseFunctionService.delete(baseFunction.getFid());
        return "forward:list.action";
    }
}
