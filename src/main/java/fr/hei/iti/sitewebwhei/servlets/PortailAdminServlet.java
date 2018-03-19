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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/PortailAdmin")
public class PortailAdminServlet extends HttpServlet {

    private Map<String, String> utilisateursAutorises;

    @Override
    public void init() throws ServletException {
        utilisateursAutorises = new HashMap<>();
        utilisateursAutorises.put("administrateur", "ajjddy8974uhuh");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String identifiantSaisie = req.getParameter("identifiant");
        String motDePasseSaisie = req.getParameter("motDePasse");

        if(utilisateursAutorises.containsKey(identifiantSaisie)) {
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