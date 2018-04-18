package com.cxn.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: springmvc
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-18 18:21
 * @Version v1.0
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("**********我是拦截器1的前置处理方法。");
        // 这里返回true，代表放行，如果是false，流程中断，不再执行后续的Controller中的方法了
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("**********我是拦截器1的后处理方法。我看到了View：" + modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("**********我是拦截器1的完成后处理方法。");
    }

}
