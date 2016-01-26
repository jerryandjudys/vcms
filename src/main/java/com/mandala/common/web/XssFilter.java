package com.mandala.common.web;

import com.mandala.core.web.util.URLHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lin on 16-1-26.
 */
public class XssFilter implements Filter {
    private String filterChar;
    private String replaceChar;
    private String splitChar;
    private String excludeUrls;
    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterChar = filterConfig.getInitParameter("FilterChar");
        this.replaceChar = filterConfig.getInitParameter("ReplaceChar");
        this.splitChar = filterConfig.getInitParameter("SplitChar");
        this.excludeUrls = filterConfig.getInitParameter("excludeUrls");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (isExcludeUrl(req)) {
            chain.doFilter(req, resp);
        } else {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) req, filterChar, replaceChar, splitChar), resp);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    private boolean isExcludeUrl(ServletRequest request) {
        boolean exclude = false;
        if (StringUtils.isNotBlank(excludeUrls)) {
            String[] excludeUrl = excludeUrls.split(splitChar);
            if (excludeUrl != null && excludeUrl.length > 0) {
                for (String url : excludeUrl) {
                    if (URLHelper.getURI((HttpServletRequest) request).startsWith(url)) {
                        exclude = true;
                    }
                }
            }
        }
        return exclude;
    }
}
