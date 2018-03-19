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

        Integer adresseId = null;
        String nom = null;
        String type = null;
        String adresse = null;
        String horaires = null;
        String description = null;
        String urlImage = null;

        try {
            adresseId = Integer.parseInt(req.getParameter("adresseId"));
            nom = req.getParameter("nom");
            type = req.getParameter("type");
            adresse = req.getParameter("adresse");
            horaires = req.getParameter("horaires");
            description = req.getParameter("description");
            urlImage = "photoDeProfil.jpg";
        }

        catch (NumberFormatException ignored) {
        }

        Adresse adresseModifie = new Adresse(adresseId, nom, type, adresse, horaires, description, urlImage);

        try {
            AdresseLibrary.getInstance().modifierAdresse(adresseModifie);
            resp.sendRedirect(String.format("AdresseAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierAdresse");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer adresseId = Integer.parseInt(req.getParameter("id"));
        Adresse adresse = AdresseLibrary.getInstance().getAdresse(adresseId);

        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("adresseId", adresseId);
        context.setVariable("nom", adresse.getNom());
        context.setVariable("type", adresse.getType());
        context.setVariable("adresse", adresse.getAdresse());
        context.setVariable("horaires", adresse.getHoraires());
        context.setVariable("description", adresse.getDescription());
        context.setVariable("urlImage", adresse.getUrlImage());

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("FormulaireAdresse", context, resp.getWriter());
    }
}
