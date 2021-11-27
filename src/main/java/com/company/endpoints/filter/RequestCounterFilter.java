package com.company.endpoints.filter;

import com.company.endpoints.service.RequestCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestCounterFilter implements Filter {
    private RequestCounterService requestCounterService;
    @Autowired
    public RequestCounterFilter(RequestCounterService requestCounterService) {
        this.requestCounterService = requestCounterService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req; // use it to get http method: req.getMethod()
        String path= request.getServletPath();
        System.out.println("path: "+path);
        if(path.equals("/api/stats")) requestCounterService.increase(request.getMethod());
        //action before request
        filterChain.doFilter(req, res);
        //action after request
        System.out.println("RequestCounterFilter execution stops");
    }
}
