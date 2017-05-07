package br.ufscar.servlet.Compromissos;

import br.ufscar.model.dao.CompromissoDao;
import br.ufscar.servlet.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/compromissos/excluir")
public class Excluir extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = new CompromissoDao().delete(
            Integer.parseInt(request.getParameter("id"))
        );

        if (result) {
            success("Compromisso excluido", request, response);
            return;
        }

        error("Problema ao excluir esse compromisso", request, response);
    }
}

