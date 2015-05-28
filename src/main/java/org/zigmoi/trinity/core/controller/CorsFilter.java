package org.zigmoi.trinity.core.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {

   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
       HttpServletResponse response = (HttpServletResponse) res;
       response.setHeader("Access-Control-Allow-Origin", "*");
       response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
       response.setHeader("Access-Control-Max-Age", "3600");
       response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Accept,Content-Type,timeout,X-Username,X-Password,X-Auth-Token");
       response.setHeader("Access-Control-Expose-Headers", "X-Auth-Token");
       chain.doFilter(req, res);
   }

   public void init(FilterConfig filterConfig) {}

   public void destroy() {}

}
