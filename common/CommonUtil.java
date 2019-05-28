package com.qhit.common;

import com.qhit.baseUser.pojo.BaseUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtil {
    public static String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    public  static Integer getUserId(HttpSession session){
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
        return sessionUser.getUserId();
    }
}
