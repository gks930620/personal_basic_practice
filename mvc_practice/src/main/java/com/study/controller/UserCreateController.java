package com.study.controller;

import com.study.annotation.Controller;
import com.study.annotation.RequestMapping;
import com.study.model.User;
import com.study.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserCreateController {

    @RequestMapping(value = "/users" , method = RequestMethod.POST)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserRepository.save(new User(request.getParameter("userId") , request.getParameter("name")));
        return "redirect:/users";
    }
}
