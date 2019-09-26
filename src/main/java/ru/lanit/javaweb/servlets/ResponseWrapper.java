package ru.lanit.javaweb.servlets;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class ResponseWrapper extends HttpServletResponseWrapper
{
    private ByteArrayOutputStream contentBuffer;
    private PrintWriter writer;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void setHeader(String name, String value) {
        if (!name.equals("Content-Type")) {
            super.setHeader(name, value);
        }
    }

    public void addHeader(String name, String value) {
        if (!name.equals("Content-Type")) {
            super.addHeader(name, value);
        }
    }

    public String getContentType() {
        return super.getContentType();
    }

    @Override
    public PrintWriter getWriter() {
        if (writer == null) {
            writer = new PrintWriter(getContentBuffer());
        }

        return writer;
    }

    private ByteArrayOutputStream getContentBuffer() {
        if (contentBuffer == null) {
            contentBuffer = new ByteArrayOutputStream();
        }

        return contentBuffer;
    }

    public String getContent() {
        getWriter().flush();

        return getContentBuffer().toString();
    }

    public int getContentLength() {
        return getContent().length();
    }
}
