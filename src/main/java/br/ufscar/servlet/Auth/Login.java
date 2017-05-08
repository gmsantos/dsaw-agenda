package br.ufscar.servlet.Auth;

import br.ufscar.model.dao.AuthenticationDao;
import br.ufscar.servlet.BaseServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispachTo("/WEB-INF/pages/login.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthenticationDao dao = new AuthenticationDao();
        HttpSession session = request.getSession();
        
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        if (dao.attempt(login, password)) {
            session.setMaxInactiveInterval(120);
            session.setAttribute("authUserId", dao.getAuthUserId());
            session.setAttribute("getAuthUserName", dao.getAuthUserName());

            Cookie cookie = new Cookie("lastVisit", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
            response.addCookie(cookie);
            response.sendRedirect("/compromissos/listar");
            return;
        }

        session.setAttribute("wrongCredentials", true);
        response.sendRedirect("/login");
    }
}
