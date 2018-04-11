package fr.hei.iti.sitewebwhei.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PortailAdmin")
public class PortailAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recuperation de parametres de connexion
        String identifiantSaisie = req.getParameter("identifiant");
        String motDePasseSaisie = req.getParameter("motDePasse");

        // Test des parametres de connexion
        if(identifiantSaisie.equals("administrateur") && motDePasseSaisie.equals("egebej783Hjhj")) {
            req.getSession().setAttribute("utilisateurConnecte", identifiantSaisie);
        }

        // Redirection vers AccueilAdmin
        resp.sendRedirect("/admin/AccueilAdmin");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creation d'un context
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // Creation d'un templateResolver
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        // Ajout d'un prefix
        templateResolver.setPrefix("/WEB-INF/templates/");
        // Ajout d'un suffix
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Execution du templateEngine avec le fichier PortailAdmin
        templateEngine.process("PortailAdmin", context, resp.getWriter());
    }
}