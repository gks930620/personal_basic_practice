package com.study.view;

import static com.study.view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JSPViewResolver  implements  ViewResolver{

    @Override
    public View resolveView(String viewName) {
        if(viewName.startsWith(DEFAULT_REDIRECT_PREFIX)){
            return new RedirectView(viewName);
        }
        return new JspView("/"+viewName+".jsp");
    }
}
