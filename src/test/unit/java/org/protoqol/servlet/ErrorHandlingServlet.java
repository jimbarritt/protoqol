package org.protoqol.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ErrorHandlingServlet extends HttpServlet {

    public ErrorHandlingServlet() {
        System.out.println("Constructing Servlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET");
         response.getWriter().println("Hello!");
    }
}
