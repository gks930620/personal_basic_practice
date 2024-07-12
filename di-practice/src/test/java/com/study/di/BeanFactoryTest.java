package com.study.di;

import com.study.annotation.Controller;
import com.study.annotation.Service;
import com.study.controller.UserController;
import com.study.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() throws ReflectiveOperationException {
        //reflection을 통해  @이 붙은 클래스 clazz들을 얻고 beanfactory 생성자로 전달.
        reflections = new Reflections("com.study");
        Set<Class<?>> preInstantiactedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
        beanFactory = new BeanFactory(preInstantiactedClazz);
    }



    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));  //@Controller가붙은 모든 클래스 + Service가 붙은 모든 클래스  com.study 스캔
        }
        return beans;
    }


    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);
        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}


