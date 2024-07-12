package com.study;

import com.study.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationHandlerAdapter  implements  HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof  AnnotationHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((AnnotationHandler) handler).handle(request, response);    //여기서 request,response 말고 모든 메소드를 지원하려면 어떻게 해야할까..
        return new ModelAndView(viewName);
    }
}
