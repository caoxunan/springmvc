package com.cxn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: springmvc
 * @description: 测试拦截器的使用
 * @author: cxn
 * @create: 2018-04-18 18:31
 * @Version v1.0
 */
@Controller
public class InterceptorController {

    // http://localhost:8085/test.do
    @RequestMapping("/test")
    public ModelAndView test() throws Exception {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg","拦截器以全部都放行了。。");
        System.out.println("********Controller执行了********");
        return mv;
    }

}
