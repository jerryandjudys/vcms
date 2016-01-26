package com.mandala.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lin on 16-1-26.
 */
public class ProcessTimeFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(ProcessTimeFilter.class);
    /**
     * 请求执行开始时间
     */
    public static final String START_TIME = "_start_time";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        long time = System.currentTimeMillis();
        request.setAttribute(START_TIME, time);
        filterChain.doFilter(request, servletResponse);
        time = System.currentTimeMillis() - time;
        logger.debug("process in {} ms: {}", time, request.getRequestURI());
    }

    @Override
    public void destroy() {

    }
}
