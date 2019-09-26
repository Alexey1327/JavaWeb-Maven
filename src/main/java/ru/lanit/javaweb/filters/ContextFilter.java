package ru.lanit.javaweb.filters;

import ru.lanit.javaweb.servlets.ResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebFilter(urlPatterns={"/*"})
public class ContextFilter implements Filter {

    private final Logger logger = Logger.getLogger(String.valueOf(ContextFilter.class));

    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ResponseWrapper wrapper = new ResponseWrapper(httpResponse);
        this.logger.info("\nRequest:\n" + getRequestHeadersAsString(httpRequest));
        chain.doFilter(request, wrapper);
        this.logger.info(String.format("\nResponse status - %s, Length: %s" , httpResponse.getStatus(), wrapper.getContentLength()));
        response.getOutputStream().write(wrapper.getContent().getBytes());
    }

    private String getRequestHeadersAsString(HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            result.append("\t- ").append(paramName).append(": ").append(request.getHeader(paramName)).append("\n");
        }

        return result.toString();
    }

    @Override
    public void destroy() {}
}
