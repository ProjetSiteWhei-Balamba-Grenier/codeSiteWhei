package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Evenement;
import fr.hei.iti.sitewebwhei.managers.EvenementLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static fr.hei.iti.sitewebwhei.managers.EvenementLibrary.getInstance;

@WebServlet("/admin/ModifierEvenement")
public class ModifierEvenementServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = null;
        String nom = null;
        String adresse = null;
        String horaire = null;
        String description = null;
        String url_image = null;

        try {
            id = Integer.parseInt(req.getParameter("evenement_id"));
            nom = req.getParameter("nom");
            adresse = req.getParameter("adresse");
            horaire = req.getParameter("horaires");
            description = req.getParameter("description");
            url_image = req.getParameter("urlImage");
        }

        catch (NumberFormatException ignored) {
        }

        Evenement evenementModifie = new Evenement(id, nom, adresse, horaire, description, url_image);

        try {
            EvenementLibrary.getInstance().modifierEvenement(evenementModifie);
            resp.sendRedirect(String.format("EvenementAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierEvenement?id="+id);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer evenement_id = Integer.parseInt(req.getParameter("id"));
        Evenement evenement = EvenementLibrary.getInstance().getEvenement(evenement_id);

        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("evenement_id", evenement_id);
        context.setVariable("nom", evenement.getNom());
        context.setVariable("adresse", evenement.getAdresse());
        context.setVariable("horaires", evenement.getHoraires());
        context.setVariable("description", evenement.getDescription());
        context.setVariable("urlImage", evenement.getUrlImage());

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("FormulaireEvenement", context, resp.getWriter());
    }
}
