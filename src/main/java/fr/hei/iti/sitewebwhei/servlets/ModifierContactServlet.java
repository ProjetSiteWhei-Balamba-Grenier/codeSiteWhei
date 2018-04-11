package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.MoyenDeContact;
import fr.hei.iti.sitewebwhei.managers.MoyenDeContactLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/ModifierContact")
public class ModifierContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialisation des parametres
        Integer moyenDeContactId = null;
        String nom = null;
        String precision = null;
        String urlPrecision = null;
        String description = null;
        String urlImage = null;

        // Recuperation des parametres
        try {
            moyenDeContactId = Integer.parseInt(req.getParameter("moyenDeContactId"));
            nom = req.getParameter("nom");
            precision = req.getParameter("precision");
            urlPrecision = req.getParameter("urlPrecision");
            description = req.getParameter("description");
            urlImage = "https://s3.eu-west-3.amazonaws.com/projet-site-whei/photoDeProfil.jpg";
        }

        catch (NumberFormatException ignored) {
        }

        // Creation d'un MoyenDeContact avec ces parametres
        MoyenDeContact moyenDeContactModifie = new MoyenDeContact(moyenDeContactId, nom, precision, urlPrecision, description, urlImage);

        // Ajout de ce moyen de contact a la base de donnees
        try {
            MoyenDeContactLibrary.getInstance().modifierMoyenDeContact(moyenDeContactModifie);
            // Redirection vers ContactAdmin
            resp.sendRedirect(String.format("ContactAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierContact?id="+moyenDeContactId);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recuperation de l'id du moyen de contact
        Integer moyenDeContactId = Integer.parseInt(req.getParameter("id"));
        // Recuperation du moyen de contact grace a l'id
        MoyenDeContact moyenDeContact = MoyenDeContactLibrary.getInstance().getMoyenDeContact(moyenDeContactId);

        // Creation d'un context
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // Ajout des parametres du moyen de contact au context
        context.setVariable("moyenDeContactId", moyenDeContactId);
        context.setVariable("nom", moyenDeContact.getNom());
        context.setVariable("precision", moyenDeContact.getPrecision());
        context.setVariable("urlPrecision", moyenDeContact.getUrlPrecision());
        context.setVariable("description", moyenDeContact.getDescription());
        context.setVariable("urlImage", moyenDeContact.getUrlImage());

        // Creation d'un templateResolver
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        // Ajout d'un prefix
        templateResolver.setPrefix("/WEB-INF/templates/");
        // Ajout d'un suffix
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Execution du templateEngine avec le fichier AccueilAdmin
        templateEngine.process("FormulaireContact", context, resp.getWriter());
    }
}
