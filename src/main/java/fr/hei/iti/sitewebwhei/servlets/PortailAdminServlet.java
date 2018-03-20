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

        String identifiantSaisie = req.getParameter("identifiant");
        String motDePasseSaisie = req.getParameter("motDePasse");

        if(identifiantSaisie.equals("administrateur") && motDePasseSaisie.equals("egebej783Hjhj")) {
            req.getSession().setAttribute("utilisateurConnecte", identifiantSaisie);
        }

        resp.sendRedirect("/admin/AccueilAdmin");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("PortailAdmin", context, resp.getWriter());
    }
}