package com.qhit.common;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseUser.pojo.BaseUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Component
@Aspect
public class AuthorityInterceptor {
    @Before(value = "execution(* com.qhit.*.controller.*.*(..))")
    public void before(JoinPoint jp){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        BaseUser baseUser = (BaseUser) request.getSession().getAttribute("sessionUser");
        String uri = request.getRequestURI();
        if (baseUser!=null){
            List<BaseFunction> baseFunctionList = baseUser.getBaseFunctionList();
            boolean flag = false;
            for(BaseFunction function:baseFunctionList){
                if (function.getUrl()!=null&&uri.indexOf(function.getUrl())!=-1){
                    flag = true;
                }
            }
            request.setAttribute("qx",flag);
        }
    }
}
