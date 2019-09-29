package ru.lanit.javaweb.filters;

import ru.lanit.javaweb.i18n.RunTimeTranslator;
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

    public static final String LANG_PARAMETER_NAME = "language";

    private static final String LANG_DEFAULT = "ru";

    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getParameter(LANG_PARAMETER_NAME) != null) {
            this.logger.info("Set lang: " + httpRequest.getParameter(LANG_PARAMETER_NAME));
            httpRequest.getSession().setAttribute(LANG_PARAMETER_NAME, httpRequest.getParameter(LANG_PARAMETER_NAME));
        } else if (httpRequest.getSession().getAttribute(LANG_PARAMETER_NAME) == null) {
            this.logger.info("Set default lang!");
            httpRequest.getSession().setAttribute(LANG_PARAMETER_NAME, LANG_DEFAULT);
        }

        ResponseWrapper wrapper = new ResponseWrapper(httpResponse);

        this.logger.info(RunTimeTranslator.translate(httpRequest, "log.request-headers") + getRequestHeadersAsString(httpRequest));
        chain.doFilter(request, wrapper);
        this.logger.info(String.format( RunTimeTranslator.translate(httpRequest, "log.response-info"), httpResponse.getStatus(), wrapper.getContentLength()));
        response.getOutputStream().write(wrapper.getContent().getBytes());
    }

    private String getRequestHeadersAsString(HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            result.append("\n\t- ").append(paramName).append(": ").append(request.getHeader(paramName));
        }

        return result.toString();
    }

    @Override
    public void destroy() {}
}
