package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Membre;
import fr.hei.iti.sitewebwhei.managers.MembreLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/ModifierMembre")
public class ModifierMembreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer membreId = null;
        String prenom = null;
        String nom = null;
        String poste = null;
        String description = null;
        String urlImage = null;

        try {
            membreId = Integer.parseInt(req.getParameter("membreId"));
            prenom = req.getParameter("prenom");
            nom = req.getParameter("nom");
            poste = req.getParameter("poste");
            description = req.getParameter("description");
            urlImage = "https://s3.eu-west-3.amazonaws.com/projet-site-whei/photoDeProfil.jpg";
        }

        catch (NumberFormatException ignored) {
        }

        Membre membreModifie = new Membre(membreId, prenom, nom, poste, description, urlImage);

        try {
            MembreLibrary.getInstance().modifierMembre(membreModifie);
            resp.sendRedirect(String.format("AccueilAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierMembre?id="+membreId);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer membreId = Integer.parseInt(req.getParameter("id"));
        Membre membre = MembreLibrary.getInstance().getMembre(membreId);

        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("membreId", membreId);
        context.setVariable("prenom", membre.getPrenom());
        context.setVariable("nom", membre.getNom());
        context.setVariable("poste", membre.getPoste());
        context.setVariable("description", membre.getDescription());
        context.setVariable("urlImage", membre.getUrlImage());

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("FormulaireMembre", context, resp.getWriter());
    }
}