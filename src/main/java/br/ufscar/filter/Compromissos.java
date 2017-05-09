package br.ufscar.filter;

import br.ufscar.model.dao.CompromissoDao;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import java.io.IOException;

@WebFilter(filterName="filterCompromissos")
public class Compromissos extends BaseFilter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int userId = Integer.parseInt(session.getAttribute("authUserId").toString());
            int compromissoId = Integer.parseInt(idParam);

            CompromissoDao dao = new CompromissoDao();

            if (!dao.ownedByUser(compromissoId, userId)) {
                request.setAttribute("output", "Você não tem permissão para fazer isso :(");
                request.setAttribute("status", "danger");
                request.getRequestDispatcher("/WEB-INF/pages/status.jsp").forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}