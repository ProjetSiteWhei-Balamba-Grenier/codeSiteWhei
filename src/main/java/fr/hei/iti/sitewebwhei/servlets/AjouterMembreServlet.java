package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Membre;
import fr.hei.iti.sitewebwhei.managers.MembreLibrary;
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
import java.net.URL;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/admin/AjouterMembre")
public class AjouterMembreServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialisation des parametres
        String prenom = null;
        String nom = null;
        String poste = null;
        String description = null;
        String urlImage = null;

        // Recuperation des parametres
        try {
            prenom = req.getParameter("prenom");
            nom = req.getParameter("nom");
            poste = req.getParameter("poste");
            description = req.getParameter("description");
            urlImage = req.getParameter("urlImage");
        }

        catch (NumberFormatException | DateTimeParseException ignored) {
        }

        // Creation d'un membre avec ces parametres
        Membre newMembre = new Membre(null, prenom, nom, poste, description, urlImage);

        // Ajout de ce membre a la base de donnees
        try {
            Membre createdMembre = MembreLibrary.getInstance().addMembre(newMembre);

            // Redirection vers AccueilAdmin
            resp.sendRedirect(String.format("AccueilAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("AjouterMembre");
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

        // Execution du templateEngine avec le fichier FormulaireMembre
        templateEngine.process("FormulaireMembre", context, resp.getWriter());

    }
}
