package br.ufscar.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;

public abstract class BaseFilter implements Filter {

    protected FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        filterConfig = null;
    }
}