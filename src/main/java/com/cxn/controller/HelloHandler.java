package com.cxn.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: springmvc
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-17 17:23
 * @Version v1.0
 */
public class HelloHandler implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // create a model and view
        ModelAndView mv = new ModelAndView();
        // set view name
        mv.setViewName("hello");
        // add model data,in springMVC Model is a container like Map, save data by key/value
        mv.addObject("msg","This is my first SpringMVC program!");

        return mv;
    }
}
