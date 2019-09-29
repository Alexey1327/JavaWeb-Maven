package ru.lanit.javaweb.i18n;

import ru.lanit.javaweb.filters.ContextFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class RunTimeTranslator {

    public static String translate(HttpServletRequest httpRequest, String nameParam) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", new Locale((String)httpRequest.getSession().getAttribute(ContextFilter.LANG_PARAMETER_NAME)));
        return bundle.getString(nameParam);
    }
}