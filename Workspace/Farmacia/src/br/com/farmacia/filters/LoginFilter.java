package br.com.farmacia.filters;

import br.com.farmacia.bean.UsuariosBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter
(filterName = "LoginFilter", urlPatterns = {"/pages/index.html"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
           FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requisicao = (HttpServletRequest) request;
        HttpServletResponse resposta = (HttpServletResponse) response;
        HttpSession sessao = requisicao.getSession();
        UsuarioBean usuarioBean = (UsuarioBean) sessao.getAttribute("usuarioBean");
        String uriLogin = requisicao.getContextPath() + "/login.xhtml";

        if (usuarioBean != null && usuarioBean.isAutenticado()) {
            chain.doFilter(request, response);
        } else {
            resposta.sendRedirect(uriLogin);
        }
    }

}
