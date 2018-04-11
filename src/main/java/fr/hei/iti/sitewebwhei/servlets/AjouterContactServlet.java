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
import javax.servlet.http.Part;
import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

@WebServlet("/admin/AjouterContact")
public class AjouterContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialisation des parametres
        String nom = null;
        String precision = null;
        String urlPrecision = null;
        String description = null;
        String urlImage = null;

        // Recuperation des parametres
        try {
            nom = req.getParameter("nom");
            precision = req.getParameter("precision");
            urlPrecision = req.getParameter("urlPrecision");
            description = req.getParameter("description");
            urlImage = "https://s3.eu-west-3.amazonaws.com/projet-site-whei/photoDeProfil.jpg";
        }

        catch (NumberFormatException | DateTimeParseException ignored) {
        }

        // Creation d'un moyen de contact avec ces parametres
        MoyenDeContact newMoyenDeContact = new MoyenDeContact(null, nom, precision, urlPrecision, description, urlImage);

        // Ajout de ce moyen de contact a la base de donnees
        try {
            MoyenDeContact createdMoyenDeContact = MoyenDeContactLibrary.getInstance().addMoyenDeContact(newMoyenDeContact);

            // Redirection vers ContactAdmin
            resp.sendRedirect(String.format("ContactAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("AjouterContact");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creation d'un context
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // Creation d'un templateResolver
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        // Ajout d'un prefix
        templateResolver.setPrefix("/WEB-INF/templates/");
        // Ajout d'un suffix
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Execution du templateEngine avec le fichier FormulaireContact
        templateEngine.process("FormulaireContact", context, resp.getWriter());

    }

}
