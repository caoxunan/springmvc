package com.cxn.controller;

import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springmvc
 * @description: 使用注解的方式定义controller
 * @author: cxn
 * @create: 2018-04-18 10:32
 * @Version v1.0
 */
@Controller
@RequestMapping("/path1")
public class AnnoController {

    // localhost:8085/path1/show1.do
    @RequestMapping("/show1")
    public ModelAndView show1(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg","My SpringMVC 注解程序");

        return mv;
    }

    // localhost:8085/path1/show2.do    POST请求
    @RequestMapping(value="/show2", method = RequestMethod.POST)
    public ModelAndView show2(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg","My SpringMVC 注解程序，show2 POST请求");

        return mv;
    }
    // localhost:8085/path1/show3/123.do    GET请求
    @RequestMapping(value="/show3/{id}", method = RequestMethod.GET)
    public ModelAndView show3(@PathVariable("id") String id){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg","My SpringMVC 注解程序，id的值为："+id);

        return mv;
    }

    // localhost:8085/path1/query/123.do?userId=12&age=30,    GET请求
    @RequestMapping(
            value="/query/{id}",
            method = {RequestMethod.GET,RequestMethod.POST},
            // 请求参数限定，必须包含userId参数，不能包含name参数，必须包含age参数且值不为20, 不满足要求直接404
            params = {"userId","!name","age!=20"}
            )
    public ModelAndView query(@PathVariable("id") String id, @RequestParam("userId") String userId, @RequestParam("age") Integer age){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg","My SpringMVC 注解程序，id的值为："+id);
        System.out.println("userId:"+ userId);
        System.out.println("age:" + age);
        return mv;
    }
    // http://localhost:8085/path1/resolve.do
    @RequestMapping(value=("resolve"))
    public ModelAndView resolve(){
        ModelAndView mv = new ModelAndView("user-list");
        List<User> users = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setAge(20+i);
            user.setName("王五"+i);
            user.setUserName("wangwu_"+i);
            users.add(user);
        }
        mv.addObject("users",users);
        return mv;
    }

    // 返回json视图 http://localhost:8085/path1/suba.do
    @RequestMapping(value="/suba")
    // 没有返回modelAndView，而是通过@ResponseBody注解声明，返回的是JSON视图
    @ResponseBody
    public List<User> suba(){

        List<User> users = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setAge(20+i);
            user.setName("李四"+i);
            user.setUserName("lisi_"+i);
            users.add(user);
        }
        return users;
    }

    // http://localhost:8085/path1/show9/1234.do
    // 允许直接返回一个String数据，作为视图名称，不需要数据模型
    @RequestMapping(value="/show9/{viewName}")
    public String content(@PathVariable("viewName") String viewName){
        // 如果只是访问页面，就不需要创建ModelAndView对象，直接返回一个字符串
        // 这个字符串就会作为视图的名称，从而访问到对应页面
        return viewName;
    }

    // 演示：重定向
    @RequestMapping(value="/show10")
    public String show10(){
        // 返回值以redirect：开头，就是重定向，但是后面必须跟上url路径而非视图名称
        return "redirect:/path1/show9.do";
    }

    // 演示：转发
    @RequestMapping(value="/show11")
    public String show11(){
        // 返回值以forward：开头，就是转发，但是后面必须跟上url路径而非视图名称
        return "forward:/path1/show9.do";
    }

    // 接收请求，不返回任何数据，只是返回成功的状态码
    @RequestMapping(value="/show12")
    @ResponseStatus(HttpStatus.OK)
    // 如果写void，代表不返回模型和视图，那么就需要加ResponseStatus，返回一个响应的状态码
    public void show12(){
        System.out.println("收到请求，进行处理，什么也不返回～");
    }

}
