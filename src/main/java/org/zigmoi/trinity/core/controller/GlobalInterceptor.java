package org.zigmoi.trinity.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by Ashim on 5/27/2015.
 */
public class GlobalInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Global Interceptor, Request received at: " +System.currentTimeMillis() + " ,for url: " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
       // System.out.println("Inside post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
        System.out.println("Global Interceptor, Response sent at: " + System.currentTimeMillis() + " ,for url: " + request.getRequestURI());
    }
}