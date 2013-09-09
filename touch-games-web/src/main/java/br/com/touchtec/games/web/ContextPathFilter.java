
package br.com.touchtec.games.web;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * Filtro que adiciona o contextPath na request sob a variável "app". <br>
 * Precisa ser um filter mesmo e não um interceptor do vraptor. Desta forma, podemos expor a varivael "app" para as
 * páginas de segurança, que não passam pelo vraptor.
 * 
 * @author bbviana
 */
public class ContextPathFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        httpRequest.setAttribute("app", httpRequest.getContextPath());

        chain.doFilter(request, response);
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
