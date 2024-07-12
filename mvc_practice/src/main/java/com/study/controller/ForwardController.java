package com.study.controller;

import com.study.annotation.Controller;
import com.study.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ForwardController  {



   @RequestMapping(value = "/user/form" , method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "user/form";
    }
}
