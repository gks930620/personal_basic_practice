package com.study;

import com.study.controller.Controller;
import com.study.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter  implements  HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName= ((Controller) handler).handleRequest(request,response);
        return new ModelAndView(viewName);
    }
}
