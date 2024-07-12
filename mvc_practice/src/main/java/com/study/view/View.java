package com.study.view;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View { //jsp view or redirect
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
