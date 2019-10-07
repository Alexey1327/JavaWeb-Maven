package ru.lanit.javaweb.servlets;

import ru.lanit.javaweb.i18n.RunTimeTranslator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/form_step")
public class FormStepServlet extends HttpServlet {

    private final static String JSP_PAGE = "form_step.jsp";
    private final static String FINAL_URL = "print";

    private final static String FORM_NAME_PARAMETER = "name";
    private final static String FIRST_NAME_SESSION = "firstName";
    private final static String MIDDLE_NAME_SESSION = "middleName";
    private final static String LAST_NAME_SESSION = "lastName";

    private final static String STEP_SESSION = "step";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int stepNumber = incStepNumber(req);

        switch (stepNumber) {
            case 1:
                saveFirstName(req);
                break;
            case 2:
                saveMiddleName(req);
                break;
            case 3:
                saveLastName(req);
                break;
        }

        if (stepNumber == 3) {
            resetStepNumber(req);
            resp.sendRedirect(FINAL_URL);
            return;
        }

        req.getRequestDispatcher(JSP_PAGE).forward(req,resp);
    }

    private void saveFirstName(HttpServletRequest req) {
        req.getSession().setAttribute(FIRST_NAME_SESSION, req.getParameter(FORM_NAME_PARAMETER));
    }

    private void saveMiddleName(HttpServletRequest req) {
        req.getSession().setAttribute(MIDDLE_NAME_SESSION, req.getParameter(FORM_NAME_PARAMETER));
    }

    private void saveLastName(HttpServletRequest req) {
        req.getSession().setAttribute(LAST_NAME_SESSION, req.getParameter(FORM_NAME_PARAMETER));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static int getNextStepNumber(HttpServletRequest req) {
        Integer step = (Integer)req.getSession().getAttribute(STEP_SESSION);
        if (step == null){
            return 1;
        } else {
            return step + 1;
        }
    }

    private static int incStepNumber(HttpServletRequest req) {
        int nextStep = getNextStepNumber(req);
        req.getSession().setAttribute(STEP_SESSION, nextStep);
        return nextStep;
    }

    private static void resetStepNumber(HttpServletRequest req) {
        req.getSession().setAttribute(STEP_SESSION, 0);
    }

    public static String getPlaceHolder(HttpServletRequest req) {

        switch (getNextStepNumber(req)) {
            case 2:
                return RunTimeTranslator.translate(req, "label.form-middle-name");
            case 3:
                return RunTimeTranslator.translate(req, "label.form-last-name");
        }
        return "";
    }
}
