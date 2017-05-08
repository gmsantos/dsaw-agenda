package br.ufscar.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import java.io.IOException;

@WebFilter(filterName="filterCompromissos")
public class Compromissos extends BaseFilter {

    public void doFilter(ServletRequest request,ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //
        chain.doFilter(request, response);
    }
}