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

@WebServlet("/ModifierContact")
public class ModifierContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer moyenDeContactId = null;
        String nom = null;
        String precision = null;
        String description = null;
        String urlImage = null;

        try {
            moyenDeContactId = Integer.parseInt(req.getParameter("moyenDeContactId"));
            nom = req.getParameter("nom");
            precision = req.getParameter("precision");
            description = req.getParameter("description");
            urlImage = "photoDeProfil.jpg";
        }

        catch (NumberFormatException ignored) {
        }

        MoyenDeContact moyenDeContactModifie = new MoyenDeContact(moyenDeContactId, nom, precision, description, urlImage);

        try {
            MoyenDeContactLibrary.getInstance().modifierMoyenDeContact(moyenDeContactModifie);
            resp.sendRedirect(String.format("ContactAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierContact");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer moyenDeContactId = Integer.parseInt(req.getParameter("id"));
        MoyenDeContact moyenDeContact = MoyenDeContactLibrary.getInstance().getMoyenDeContact(moyenDeContactId);

        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("moyenDeContactId", moyenDeContactId);
        context.setVariable("nom", moyenDeContact.getNom());
        context.setVariable("precision", moyenDeContact.getPrecision());
        context.setVariable("description", moyenDeContact.getDescription());
        context.setVariable("urlImage", moyenDeContact.getUrlImage());

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("FormulaireContact", context, resp.getWriter());
    }
}
