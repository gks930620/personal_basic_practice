package com.study;

import com.study.controller.Controller;
import com.study.controller.HandlerKey;
import com.study.controller.RequestMethod;
import com.study.view.JSPViewResolver;
import com.study.view.ModelAndView;
import com.study.view.View;
import com.study.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@WebServlet("/")  //서버키고 servlet등록이야 되는데 이거 없으면 어떤 요청을 이 서블릿이 처리하는지 알수없지.
public class DispatcherServlet  extends HttpServlet {
    private List<HandlerMapping> handlerMappings;

    private List<HandlerAdapter> handlerAdapters;
    private List<ViewResolver> viewResolvers;
    private Logger logger= LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public void init() throws ServletException {
        RequestMappingHandlerMapping rmhm=new RequestMappingHandlerMapping();
        rmhm.init();   // 처음엔 RequestMappingHandler 직접했지만 사실 @에 있는것들을 등록해야되니까..

        AnnotationHandlerMapping ahm=new AnnotationHandlerMapping("com.study");
        ahm.initialize();
        handlerMappings= List.of(rmhm,ahm);

        handlerAdapters=List.of(new SimpleControllerHandlerAdapter() , new AnnotationHandlerAdapter() );
        viewResolvers= Collections.singletonList(new JSPViewResolver());

        //필드에 있는거를 beanfactory를 통해 관리.  ==>  필드에는 beanfactory만 선언
        // 근데 저정도는 있어도 되는거 아니냐.. beanFactory에 있는거는
        // 우리가 만든 @Controller  @Repository 객체들만 있어야 하는거 아니냐



    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

            String requestURI=req.getRequestURI();
            RequestMethod requestMethod=RequestMethod.valueOf( req.getMethod());

            //handlerKey도 그때 그때 만드는게 아니라 미리 조사해야지. 즉 대부분의 사전준비는 init 메소드에서 하고
            // service에서는 비교만 해야되네.
            Object handler= handlerMappings.stream().filter(hm -> hm.findHandler(new HandlerKey( requestMethod,requestURI  ))  !=null )
                    .map(hm -> hm.findHandler(new HandlerKey( requestMethod,requestURI  ))).findFirst()
                    .orElseThrow( () -> new ServletException("No handler for RequestMethod,uri :  " + requestMethod + " "+requestURI));
            //String viewName= handler.handleRequest(req,resp);

            HandlerAdapter handlerAdapter = handlerAdapters.stream().filter(ha -> ha.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for Handler "));

            ModelAndView mav= handlerAdapter.handle(req,resp,handler);  //결국엔 handler의 handleRequest가 실행됨, String뿐만아니라 어떤형태의 return값도 처리가능


            for(ViewResolver viewResolver : viewResolvers){
                View view =viewResolver.resolveView(mav.getViewName());
                view.render(mav.getModel(),req,resp);
            }

            //왜 NullPointerException 위치가 2개라고 알려주는거지?

        }catch (Exception e){
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
