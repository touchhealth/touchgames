
package br.com.touchtec.games.web.servlet;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class ContextPathFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        // EXERCICIO
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // nada
    }

    @Override
    public void destroy() {
        // nada
    }

}
