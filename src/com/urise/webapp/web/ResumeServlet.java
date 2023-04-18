package com.urise.webapp.web;


import com.urise.webapp.Config;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//        Object name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello from servlet.." : "Hello " + name + "!");
//        response.sendRedirect("New.html");
        Writer writer = response.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                     "<html lang=\"en\">\n" +
                     "<head>\n" +
                     "    <meta charset=\"UTF-8\">\n" +
                     "    <title>ResumesTable</title>\n" +
                     "</head>\n" +
                     "<body>\n" +
                     "<section>\n" +
                     "<table border=\"3\">\n" +
                     "<tr>\n" +
                     "        <td>UUID</td>\n" +
                     "        <td>Full_name</td>\n" +
                     "    </tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                    "        <td>" + resume.getUuid() + "</td>\n" +
                    "        <td>" + resume.getFullName() + "</td>\n" +
                    "    </tr>\n");
        }
        writer.write("</section>\n" +
                      "</table>\n" +
                      "</body>\n" +
                      "</html>\n");
        writer.close();

    }
}

