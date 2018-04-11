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

        // GET PARAMETERS
        String nom = null;
        String type = null;
        String adresse = null;
        String horaires = null;
        String description = null;
        String urlImage = null;

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

        // CREATE ADRESSE
        Adresse newAdresse = new Adresse(null, nom, type, adresse, horaires, description, urlImage);

        try {
            Adresse createdAdresse = AdresseLibrary.getInstance().addAdresse(newAdresse);

            // REDIRECT TO DETAIL FILM
            resp.sendRedirect(String.format("AdresseAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("AjouterAdresse");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("currentPage", "ajouter");

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("FormulaireAdresse", context, resp.getWriter());

    }
}
