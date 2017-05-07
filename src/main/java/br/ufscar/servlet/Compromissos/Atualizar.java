package br.ufscar.servlet.Compromissos;

import br.ufscar.model.Compromisso;
import br.ufscar.model.dao.CompromissoDao;
import br.ufscar.servlet.BaseServlet;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/compromissos/atualizar")
public class Atualizar extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("compromisso", new CompromissoDao().getOne(id));
        } catch (RuntimeException e) {
            error("Não foi possível encontrar esse compromisso", request, response);
            return;
        }

        dispachTo("/WEB-INF/pages/compromissos/atualizar.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Compromisso compromisso = new Compromisso();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String tipo = request.getParameter("tipo");
        String local = request.getParameter("local");
        Double duracao = Double.parseDouble(request.getParameter("duracao"));
        String observacao = request.getParameter("observacao");
        Date data;

        try {
            data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(
                request.getParameter("data")
            );
        } catch (ParseException ex) {
            ex.printStackTrace();
            return;
        }
        
        compromisso.setId(id);
        compromisso.setTitulo(titulo);
        compromisso.setTipo(tipo);
        compromisso.setData(data);
        compromisso.setLocal(local);
        compromisso.setDuracao(duracao);
        compromisso.setObservacao(observacao);
        
        if (!compromisso.isValid()) {
            error("Todos os campos precisam ser preenchidos", request, response);
            return;
        }

        if (data.getTime() <= System.currentTimeMillis()) {
            error("Você agendou um compromisso no passado. Digite uma data futura", request, response);
            return;
        }

        CompromissoDao dao = new CompromissoDao();

        if (!dao.update(compromisso)){
            error("Falha ao atualizar compromisso", request, response);
            return;
        }

        success("Atualização do Compromisso realizado com sucesso", request, response);
    }
}

