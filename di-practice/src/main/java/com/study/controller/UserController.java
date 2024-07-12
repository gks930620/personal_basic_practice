package com.study.controller;

import com.study.annotation.Controller;
import com.study.annotation.Inject;
import com.study.service.UserService;

@Controller
public class UserController {
    private  final UserService userService;


    @Inject
    public UserController(UserService userService){
        this.userService=userService;
    }
    //@Inject가 제대로 동작한다면 userService는 null이 아닐 것이다.
    public UserService getUserService() {
        return userService;
    }
}
