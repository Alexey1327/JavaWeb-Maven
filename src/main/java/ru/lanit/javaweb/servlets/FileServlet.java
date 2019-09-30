package ru.lanit.javaweb.servlets;

import ru.lanit.javaweb.filters.ContextFilter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@WebServlet(name = "FileServlet", urlPatterns = {"/images/*"})
public class FileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filename = request.getRequestURI().replace(request.getContextPath(),"");

        if (request.getSession().getAttribute(ContextFilter.LANG_PARAMETER_NAME) == null) {
            filename = filename.replace("images/","images_" + ContextFilter.LANG_DEFAULT + "/");
        } else {
            filename = filename.replace("images/","images_" + request.getSession().getAttribute(ContextFilter.LANG_PARAMETER_NAME) + "/");
        }

        try (InputStream fileStream = getClass().getClassLoader().getResourceAsStream(filename)) {

            if (fileStream == null) {
                response.setContentType("text/plain");
                response.getWriter().write("File not found");
            } else {
                String mimeType = Files.probeContentType(new File(filename).toPath());
                ServletOutputStream output = response.getOutputStream();
                response.setContentType(mimeType);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
