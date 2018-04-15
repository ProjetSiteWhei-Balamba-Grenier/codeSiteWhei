package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Evenement;
import fr.hei.iti.sitewebwhei.managers.EvenementLibrary;
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

@WebServlet("/admin/AjouterEvenement")
public class AjouterEvenementServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nom = null;
        String adresse = null;
        String horaire = null;
        String description = null;
        String url_image = null;

        try {
            nom = req.getParameter("nom");
            adresse = req.getParameter("adresse");
            horaire = req.getParameter("horaires");
            description = req.getParameter("description");
            url_image = req.getParameter("urlImage");
        }

        catch (NumberFormatException ignored) {
        }

        Evenement newEvenement = new Evenement(null, nom, adresse, horaire, description, url_image);

        try {
            EvenementLibrary.getInstance().addEvenement(newEvenement);
            resp.sendRedirect(String.format("EvenementAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("AjouterEvenement");
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

        // Execution du templateEngine avec le fichier FormulaireEvenement
        templateEngine.process("FormulaireEvenement", context, resp.getWriter());

    }
}
