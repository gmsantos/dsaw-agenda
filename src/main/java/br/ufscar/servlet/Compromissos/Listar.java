package br.ufscar.servlet.Compromissos;

import br.ufscar.model.dao.CompromissoDao;
import br.ufscar.servlet.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/compromissos/listar")
public class Listar extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompromissoDao dao = new CompromissoDao();
        int userId = Integer.parseInt(request.getSession(false).getAttribute("authUserId").toString());
        request.setAttribute("compromissos", dao.getAllByUser(userId));

        dispachTo("/WEB-INF/pages/compromissos/listar.jsp", request, response);
    }
}
