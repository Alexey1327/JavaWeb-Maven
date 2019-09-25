package ru.lanit.javaweb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * /print
 * выводит на экран распечаку из сессии, сервлет объявлен через web.xml
 */
public class PrintServlet extends HttpServlet {

    private final static String JSP_PAGE = "print.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP_PAGE).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static String getFullName(HttpServletRequest req) {
        return req.getSession().getAttribute("firstName")
                + " " + req.getSession().getAttribute("middleName")
                + " " + req.getSession().getAttribute("lastName");
    }

}
