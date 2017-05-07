package br.ufscar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

    protected void error(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("output", message);
        request.setAttribute("status", "danger");
        dispachTo("/WEB-INF/pages/status.jsp", request, response);
    }

    protected void success(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("output", message);
        request.setAttribute("status", "success");
        dispachTo("/WEB-INF/pages/status.jsp", request, response);
    }

    private void dispachTo(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

