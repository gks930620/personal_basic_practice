package com.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationHandler {
    private final Class<?> clazz;
    private final Method targetMethod;

    public AnnotationHandler(Class<?> clazz, Method targetMethod) {
        this.clazz=clazz;
        this.targetMethod=targetMethod;
    }

    public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        //지금이야 기본생성자로만 생성한 @Controller가 되도록 했지만, 어떤 타입이 오든 상관없게 할려면 constructors() 메소드로 stream 돌리기
        Object handler=declaredConstructor.newInstance();   //DI가 없으니까 직접 생성하지만, DI를 한다면 객체 한개를 그대로 사용하겠지

        return (String)targetMethod.invoke(handler,request,response);   //여기서 실제 RequestMapping 메소드가 실행됨
    }
}
