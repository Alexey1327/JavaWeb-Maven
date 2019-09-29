package ru.lanit.javaweb.servlets;

import ru.lanit.javaweb.i18n.RunTimeTranslator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public static String getSuccessMessage(HttpServletRequest req) {
        return String.format(RunTimeTranslator.translate(req, "label.print-message"), getFullName(req));
    }

    private static String getFullName(HttpServletRequest req) {

        final String firstName = (String)req.getSession().getAttribute("firstName");
        final String middleName = (String)req.getSession().getAttribute("middleName");
        final String lastName = (String)req.getSession().getAttribute("lastName");

        return firstName + " " + middleName + " " + lastName;
    }

}
