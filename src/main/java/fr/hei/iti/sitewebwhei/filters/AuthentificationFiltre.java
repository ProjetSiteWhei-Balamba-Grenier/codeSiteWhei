package fr.hei.iti.sitewebwhei.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthentificationFiltre  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // Recuperation de la variable utilisateurConnecte
        String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");
        // Si l'utilisateur n'est pas connecte, il est rediriger vers le portail de connexion
        if(identifiant == null || "".equals(identifiant)) {
            System.out.println("Il faut être connecté pour accéder à cette page !");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/PortailAdmin");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
