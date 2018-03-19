package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.MoyenDeContact;
import fr.hei.iti.sitewebwhei.managers.MoyenDeContactLibrary;
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

@WebServlet("/admin/AjouterContact")
public class AjouterContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // GET PARAMETERS
        String nom = null;
        String precision = null;
        String urlPrecision = null;
        String description = null;
        String urlImage = null;

        try {
            nom = req.getParameter("nom");
            precision = req.getParameter("precision");
            urlPrecision = req.getParameter("urlPrecision");
            description = req.getParameter("description");
            urlImage = "photoDeProfil.jpg";
        }

        catch (NumberFormatException | DateTimeParseException ignored) {
        }

        // CREATE FILM
        MoyenDeContact newMoyenDeContact = new MoyenDeContact(null, nom, precision, urlPrecision, description, urlImage);

        try {
            MoyenDeContact createdMoyenDeContact = MoyenDeContactLibrary.getInstance().addMoyenDeContact(newMoyenDeContact);

            // REDIRECT TO DETAIL FILM
            resp.sendRedirect(String.format("ContactAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("FormulaireContact");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("FormulaireContact", context, resp.getWriter());

    }
}
