package com.cxn.controller;

import model.User;
import model.UserForm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @program: springmvc
 * @description: 用来测试请求参数的绑定和获取
 * @author: cxn
 * @create: 2018-04-18 14:21
 * @Version v1.0
 */
@Controller
@RequestMapping("/path2")
public class RequestParamController {

    // 1 获取servlet内置对象
    // 写在方法参数列表中的参数，都会被SpringMVC自动注入，我们只管用就可以
    @RequestMapping(value="/show1")
    public String show1(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        // 使用request对象保存数据
        request.setAttribute("msg1","我是request，添加一条数据：" + request);
        request.setAttribute("msg2","我是response，添加一条数据：" + response);
        // 使用session对象保存数据
        session.setAttribute("msg3","我是session，我添加了数据：" + session);
        return "servlet-test";
    }

    // 2 获取PathVariable参数
    // 通过@PathVariable注解获取路径占位符中的变量值，并且可以映射为String或数值类型
    @RequestMapping(value="/show2/{age}/{name}")
    public ModelAndView show2(@PathVariable("age") String age, @PathVariable("name") String name){

        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg","占位符映射，age:" + age + ",name:" + name);

        return mv;
    }

    // 3 获取基本数据类型请求参数
    // http://localhost:8085/path1/show9/request-param.do
    @RequestMapping(value="/show3")
    @ResponseStatus(HttpStatus.OK)
    public void show3(
            // 通过RequestParam来指定要接收的参数，SpringMVC自动完成参数注入
            @RequestParam("name") String name,
            @RequestParam("age") Integer age,
            @RequestParam("income") Double income,
            @RequestParam("isMarried") Boolean isMarried,
            @RequestParam("interests") String[] interests
            ){
        System.out.println("测试基本数据类型绑定：～～～～～～～～～～～～～～～～～");
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        System.out.println("income:" + income);
        System.out.println("isMarried:" + isMarried);
        System.out.println("interests:" + Arrays.toString(interests));

    }

    // 4 RequestParam的更多用法
    // @RequestParam -> value:参数名，required：是否必须，默认为true，表示请求中一定要包含此参数，
    // defaultValue：默认参数值，如果设置来该值，自动将required设置为false，如果参数中没有包含该参数则使用默认值
    @RequestMapping("/show4")
    public ModelAndView show4(@RequestParam(value="name", defaultValue = "default") String name){
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg","接收普通参数,name:" + name);
        return mv;
    }

    // 5 获取请求参数并封装为POJO对象
    @RequestMapping("/show5")
    public ModelAndView show5(User user){
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg","接收普通参数，user：" + user);
        return mv;
    }

    // 6 获取cookie值
    // 传统获取cookie的方式需要用到request，然后获取cookie数组，在SpringMVC中可以使用@CoolieValue注解
    @RequestMapping("/show6")
    public ModelAndView show6(@CookieValue("JSESSIONID") String jsessionId){

        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg","接收cookie的值，jsessionId：" + jsessionId);
        return mv;
    }


    // 7 接收List集合
    // 参数的封装是创建一个参数的实例，然后调用setter方法注入属性，所以不能使用List<User>的形式，因为List是接口，无法实例化
    @RequestMapping("/show7")
    public ModelAndView show7(UserForm userForm){
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg","接收对象参数，users：" + userForm);
        return mv;
    }

    // 8 接收json格式的数据，并且把JSON字符串转化为POJO对象
    // @ResponseBody	是把返回值的Pojo对象变为JSON字符串，称为序列化
    // @RequestBody	是把接收到的JSON字符串变为Pojo对象，称为反序列化
    @RequestMapping("/show8")
    @ResponseStatus(HttpStatus.OK)
    public void show8(@RequestBody User user){

        System.out.println("id:" + user.getId());
        System.out.println("age:" + user.getAge());
        System.out.println("name:" + user.getName());

    }

    // 8.1 接收json数组
    @RequestMapping("/show9")
    @ResponseStatus(HttpStatus.OK)
    public void show9(@RequestBody List<User> users){
        for (User user : users) {
            System.out.println("id:" + user.getId());
            System.out.println("age:" + user.getAge());
            System.out.println("name:" + user.getName());
        }
    }



}
