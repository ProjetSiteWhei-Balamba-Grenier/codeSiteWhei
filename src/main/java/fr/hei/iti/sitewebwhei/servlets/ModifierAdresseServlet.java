package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Adresse;
import fr.hei.iti.sitewebwhei.managers.AdresseLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/ModifierAdresse")
public class ModifierAdresseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialisation des parametres

        Integer adresseId = null;
        String nom = null;
        String type = null;
        String adresse = null;
        String horaires = null;
        String description = null;
        String urlImage = null;

        // Recuperation des parametres

        try {
            adresseId = Integer.parseInt(req.getParameter("adresseId"));
            nom = req.getParameter("nom");
            type = req.getParameter("type");
            adresse = req.getParameter("adresse");
            horaires = req.getParameter("horaires");
            description = req.getParameter("description");
            urlImage = req.getParameter("urlImage");
        }

        catch (NumberFormatException ignored) {
        }

        // Creation d'un adresse avec ces parametres

        Adresse adresseModifie = new Adresse(adresseId, nom, type, adresse, horaires, description, urlImage);

        // Ajout de cette adresse a la base de donnees
        try {
            AdresseLibrary.getInstance().modifierAdresse(adresseModifie);
            // Redirection vers AdresseAdmin
            resp.sendRedirect(String.format("AdresseAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierAdresse?id="+adresseId);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recuperation de l'id de l'adresse
        Integer adresseId = Integer.parseInt(req.getParameter("id"));
        // Recuperation de l'adresse grace a l'id
        Adresse adresse = AdresseLibrary.getInstance().getAdresse(adresseId);

        // Creation d'un context
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // Ajout des parametres de l'adresse au context
        context.setVariable("adresseId", adresseId);
        context.setVariable("nom", adresse.getNom());
        context.setVariable("type", adresse.getType());
        context.setVariable("adresse", adresse.getAdresse());
        context.setVariable("horaires", adresse.getHoraires());
        context.setVariable("description", adresse.getDescription());
        context.setVariable("urlImage", adresse.getUrlImage());

        // Creation d'un templateResolver
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        // Ajout d'un prefix
        templateResolver.setPrefix("/WEB-INF/templates/");
        // Ajout d'un suffix
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Execution du templateEngine avec le fichier AccueilAdmin
        templateEngine.process("FormulaireAdresse", context, resp.getWriter());
    }
}
