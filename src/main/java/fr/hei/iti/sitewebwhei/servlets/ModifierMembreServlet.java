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

        // Initialisation des parametres
        Integer membreId = null;
        String prenom = null;
        String nom = null;
        String poste = null;
        String description = null;
        String urlImage = null;

        // Recuperation des parametres
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

        // Creation d'un membre avec ces parametres
        Membre membreModifie = new Membre(membreId, prenom, nom, poste, description, urlImage);

        // Ajout de ce membre a la base de donnees
        try {
            MembreLibrary.getInstance().modifierMembre(membreModifie);
            // Redirection vers AccueilAdmin
            resp.sendRedirect(String.format("AccueilAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierMembre?id="+membreId);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recuperation de l'id du membre
        Integer membreId = Integer.parseInt(req.getParameter("id"));
        // Recuperation du membre grace a l'id
        Membre membre = MembreLibrary.getInstance().getMembre(membreId);

        // Creation d'un context
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // Ajout des parametres du memebre au context
        context.setVariable("membreId", membreId);
        context.setVariable("prenom", membre.getPrenom());
        context.setVariable("nom", membre.getNom());
        context.setVariable("poste", membre.getPoste());
        context.setVariable("description", membre.getDescription());
        context.setVariable("urlImage", membre.getUrlImage());

        // Creation d'un templateResolver
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        // Ajout d'un prefix
        templateResolver.setPrefix("/WEB-INF/templates/");
        // Ajout d'un suffix
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Execution du templateEngine avec le fichier FormulaireMembre
        templateEngine.process("FormulaireMembre", context, resp.getWriter());
    }
}