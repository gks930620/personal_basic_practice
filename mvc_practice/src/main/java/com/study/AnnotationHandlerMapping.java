package com.study;

import com.study.annotation.Controller;
import com.study.annotation.RequestMapping;
import com.study.controller.HandlerKey;
import com.study.controller.RequestMethod;
import javassist.tools.reflect.Reflection;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping  implements  HandlerMapping{

    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers=new HashMap<>();

    public AnnotationHandlerMapping(Object ... basePackage) {
        this.basePackage=basePackage;
    }


    public void initialize(){
        Reflections reflections=new Reflections(basePackage);
        Set<Class<?>> clazzWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        clazzWithControllerAnnotation.forEach(clazz ->{
                    Arrays.stream(clazz.getDeclaredMethods()).forEach(
                            declaredMethod -> {
                                RequestMapping requestMappingMethod = declaredMethod.getDeclaredAnnotation(RequestMapping.class);
                                //@RequestMapping 이 붙어있는 메소드

                                Arrays.stream(getRequestMethods(requestMappingMethod ))
                                        .forEach(requestMethod ->{
                                            HandlerKey handlerKey=new HandlerKey(requestMethod,requestMappingMethod.value());
                                            AnnotationHandler annotationHandler = new AnnotationHandler(clazz,declaredMethod);
                                            //new 할게 아니라 미리  map에다 저쟁하놓고 제공해줘야지 @Controller에 대한 객체도 저장해놓고 제공해줘야지
                                            handlers.put(handlerKey,annotationHandler);
                                        } );

                            }
                    );
                });

    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMappingMethod) {
        return  requestMappingMethod.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }


}
