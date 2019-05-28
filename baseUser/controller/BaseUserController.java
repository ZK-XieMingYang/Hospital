package com.qhit.baseUser.controller;

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.utils.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/baseUser")
public class BaseUserController {
    MD5 md5=new MD5();
    private IBaseUserService userService = new BaseUserServiceImpl();
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List list = userService.findAll();
        request.setAttribute("list",list);
        return "baseUser/list";
    }
    @RequestMapping("/insert")
    public String insert(BaseUser baseUser){
       baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        userService.insert(baseUser);
        return "forward:list.action";
    }
    @RequestMapping("/load")
    public String load(BaseUser baseUser, Model model){
        BaseUser user = userService.findById(baseUser.getUserId());
        model.addAttribute("baseUser",user);
        return "baseUser/edit";
    }
    @RequestMapping("/update")
    public String updata(BaseUser baseUser){
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        userService.update(baseUser);
        return "forward:list.action";
    }
    @RequestMapping("/del")
    public String del(BaseUser baseUser){
        userService.delete(baseUser.getUserId());
        return "forward:list.action";
    }
    @RequestMapping("/search")
    public String search(BaseUser baseUser,Model model){
        List list = userService.search(baseUser);
        model.addAttribute("searchObject",baseUser);
        model.addAttribute("list",list);
        return "baseUser/list";
    }
    @RequestMapping("/login")
    public String login(BaseUser baseUser,HttpServletRequest request){
        baseUser = userService.login(baseUser);
        if (baseUser!=null){
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser",baseUser);
            return "index/home";
        }else {
            request.setAttribute("error","您输入的用户名或密码有误!");
            return "baseUser/login";
        }
    }
    @RequestMapping("/logout")
    public String logout(BaseUser baseUser,HttpServletRequest request){
        request.getSession().removeAttribute("sessionUser");
        return "redirect:/jsp/baseUser/login.jsp";
    }
    @RequestMapping("/checkPassword")
    public void checkPassword(BaseUser baseUser, HttpServletResponse response) throws IOException {
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        boolean flag = userService.findByIdAndPassword(baseUser);
        response.getWriter().write(flag?"Y":"N");
    }
    @RequestMapping("/updatePassword")
    public void updatePassword(BaseUser baseUser, HttpServletResponse response) throws IOException {
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        boolean flag = userService.updateSelective(baseUser);
        response.getWriter().write(flag?"Y":"N");
    }
}
