package com.study;

import com.study.controller.Controller;
import com.study.controller.HandlerKey;

public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}
