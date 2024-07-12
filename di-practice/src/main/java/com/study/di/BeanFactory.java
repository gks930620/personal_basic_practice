package com.study.di;

import com.study.annotation.Inject;
import com.study.controller.UserController;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.MalformedParametersException;
import java.util.*;

public class BeanFactory {

    //1. @붙은 클래스의 객체를 1개씩만 만들것임.   객체관리는 map으로
    //key 값은 클래스가 되고, value는   클래스의 객체
    private Map<Class<?>, Object> beans = new HashMap<>();
    private final Set<Class<?>> annotatedClazzes;


    //2. @붙은 클래스의 객체를 1개씩만 만들것임.
    //생성자에 @붙은 클래스(정보)들이 파라미터로 필요함
    //파라미터로 넘어온 클래스들에 대한 객체를 1개씩 만들고 beans에 저장하면됨. 주입관계 신경쓰면서.
    public BeanFactory(Set<Class<?>> annotatedClazzes) throws ReflectiveOperationException{
        this.annotatedClazzes=annotatedClazzes;
        for (Class<?> clazz : annotatedClazzes) {
            if (getBean(clazz) == null ) {//createInstanceAndPut에서 주입객체가 먼저 생성됐다면 조건이 false가 된다.
                createInstanceAndPut(clazz);
            }
        }
    }


    //3. DI가 있기 때문에 객체(UserController)를 생성하기전
    // 생성자를 조사해 주입이 필요한 객체(UserService)가 있는지 확인해야한다.
    // 주입이 필요한 객체가 있다면 주입객체(UserService)를 먼저 생성해야한다.
    private void createInstanceAndPut(Class<?> clazz) throws ReflectiveOperationException {
        Constructor<?> constructor = findConstructor(clazz);  //생성자를 조사한다. 생성자는 1개.(주입이 있거나 없거나)
        //파라미터가 없다면 기본 생성자일 것이다.
        if (constructor.getParameterTypes().length == 0) {
            Object instance = constructor.newInstance();
            beans.put(clazz, instance);
            return ;
        }
        //파라미터가 있다면 주입객체가 필요한 생성자.
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {  //주입객체가 여러개일수도있으니까..
            Object instanceBean = getBean(typeClass);
            if(instanceBean==null) {
                if( ! this.annotatedClazzes.contains( typeClass)){//만약 주입해야할 클래스에 @이 없다면
                    throw new RuntimeException(
                            clazz.getSimpleName()+"빈 만드려는데 " + typeClass.getSimpleName()+"객체가 없어서 에러.");
                }
                createInstanceAndPut(typeClass);   //재귀. 객체를 만들고 bean에 넣음.
                Object instanceBean2 = getBean(typeClass);   //이제는 bean에 있으니까 parameter에 추가.
                parameters.add(instanceBean2);
            } else {
                parameters.add(instanceBean);
            }
        }
        Object instance = constructor.newInstance(parameters.toArray());
        beans.put(clazz, instance);
    }


    private Constructor<?> findConstructor(Class<?> clazz){
        //@Inject가 붙은  생성자가 있는지 확인
        Set<Constructor> injectedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if (injectedConstructors.isEmpty()){  //@Inject붙은 생성자가 없다면  기본생성자만 있는거다.
            return clazz.getConstructors()[0];
        }else{ //
            return injectedConstructors.iterator().next();  //@Inject붙은 생성자도 1개라고 가정
        }
    }




    //1. client는 beanFactory에서  원하는 객체를 얻어야 함. getBean메소드
    public <T> T getBean(Class<T> type) {
        return (T) beans.get(type);
    }

}
