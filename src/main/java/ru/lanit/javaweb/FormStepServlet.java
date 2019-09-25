package ru.lanit.javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/form_step")
public class FormStepServlet extends HttpServlet {

    private final static String JSP_PAGE = "form_step.jsp";
    private final static String FINAL_URL = "print";

    private final static String FORM_NAME_PARAMETER = "name";
    private final static String FIRST_NAME_SESSION = "firstName";
    private final static String MIDDLE_NAME_SESSION = "middleName";
    private final static String LAST_NAME_SESSION = "lastName";

    private final static String PLACE_HOLDER_MIDDLE_NAME = "Enter your middle name";
    private final static String PLACE_HOLDER_LAST_NAME = "Enter your last name";

    private static int stepNumber = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        stepNumber++;

        String formData = req.getParameter(FORM_NAME_PARAMETER);

        switch (stepNumber) {
            case 2:
                session.setAttribute(FIRST_NAME_SESSION, formData);
                break;
            case 3:
                session.setAttribute(MIDDLE_NAME_SESSION, formData);
                break;
            case 4:
                session.setAttribute(LAST_NAME_SESSION, formData);
                break;
        }

        if (stepNumber < 4) {
            req.getRequestDispatcher(JSP_PAGE).forward(req,resp);
        } else {
            stepNumber = 1;
            resp.sendRedirect(FINAL_URL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static int getStepNumber() {
        return stepNumber;
    }

    public static String getPlaceHolder() {
        switch (stepNumber) {
            case 2:
                return PLACE_HOLDER_MIDDLE_NAME;
            case 3:
                return PLACE_HOLDER_LAST_NAME;
        }
        return "";
    }
}
