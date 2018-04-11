package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Adresse;
import fr.hei.iti.sitewebwhei.managers.AdresseLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeParseException;

@WebServlet("/admin/AjouterAdresse")
public class AjouterAdresseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialisation des parametres
        String nom = null;
        String type = null;
        String adresse = null;
        String horaires = null;
        String description = null;
        String urlImage = null;

        // Recuperation des parametres
        try {
            nom = req.getParameter("nom");
            type = req.getParameter("type");
            adresse = req.getParameter("adresse");
            horaires = req.getParameter("horaires");
            description = req.getParameter("description");
            urlImage = req.getParameter("urlImage");
        }

        catch (NumberFormatException | DateTimeParseException ignored) {
        }

        // Creation d'une adresse avec ces parametres
        Adresse newAdresse = new Adresse(null, nom, type, adresse, horaires, description, urlImage);

        // Ajout de cette adresse a la base de donnees
        try {
            Adresse createdAdresse = AdresseLibrary.getInstance().addAdresse(newAdresse);

            // Redirection vers AdresseAdmin
            resp.sendRedirect(String.format("AdresseAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("AjouterAdresse");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creation d'un context
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("currentPage", "ajouter");

        // Creation d'un templateResolver
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        // Ajout d'un prefix
        templateResolver.setPrefix("/WEB-INF/templates/");
        // Ajout d'un suffix
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Execution du templateEngine avec le fichier FormulaireAdresse
        templateEngine.process("FormulaireAdresse", context, resp.getWriter());

    }
}
