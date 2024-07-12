package com.study.controller;

import com.study.annotation.Controller;
import com.study.annotation.RequestMapping;
import com.study.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserListController {


    @RequestMapping(value = "/users" , method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("users" ,UserRepository.findAll()  );
        return "user/list";
    }
}
