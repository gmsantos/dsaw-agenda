package br.ufscar.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastroCompromissos", urlPatterns = {"/CadastroCompromissos"})
public class CadastroCompromissos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Compromisso compromisso = new Compromisso();
        
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
        
        compromisso.setTitulo(titulo);
        compromisso.setTipo(tipo);
        compromisso.setData(data);
        compromisso.setLocal(local);
        compromisso.setDuracao(duracao);
        compromisso.setObservacao(observacao);
        
        if (compromisso.select()) {
            request.setAttribute("output", "Compromisso já agendado");
            dispachTo("WEB-INF/pages/sample.jsp", request, response);
            return;
        } 
                
        //Validações do Formulário
        if (titulo.isEmpty() 
            || tipo.isEmpty()
            || local.isEmpty() 
            || duracao.isNaN() 
            || observacao.isEmpty()
        ) {
            request.setAttribute("output", "Todos os campos precisam ser preenchidos");
            dispachTo("WEB-INF/pages/sample.jsp", request, response);
            return;
        }
  
        if (data.getTime() <= System.currentTimeMillis()) {
            request.setAttribute("output", "Você agendou um compromisso no passado. Digite uma data futura");
            dispachTo("WEB-INF/pages/sample.jsp", request, response);
            return;
        }

        if (!compromisso.save()){
            request.setAttribute("output", "Falha ao cadastrar compromisso");
            dispachTo("WEB-INF/pages/sample.jsp", request, response);
            return;
        }

        request.setAttribute("output", "Cadastro do Compromisso realizado com sucesso");
        dispachTo("WEB-INF/pages/sample.jsp", request, response);
    }

    private void dispachTo(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

