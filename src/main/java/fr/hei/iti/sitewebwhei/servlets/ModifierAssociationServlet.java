package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Association;
import fr.hei.iti.sitewebwhei.managers.AssociationLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/ModifierAssociation")
public class ModifierAssociationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer association_id = null;
        String nom = null;
        String nomFb=null;
        String nomPresident=null;
        String mail=null;
        String description = null;
        String pole = null;
        String urlImage = null;

        try {
            association_id = Integer.parseInt(req.getParameter("association_id"));
            nom = req.getParameter("nom");
            nomFb = req.getParameter("nomFb");
            nomPresident = req.getParameter("nomPresident");
            mail = req.getParameter("mail");
            description = req.getParameter("description");
            pole = req.getParameter("pole");
            urlImage = req.getParameter("urlImage");
        }

        catch (NumberFormatException ignored) {
        }

        Association associationModifie = new Association(association_id, nom, description, nomPresident, nomFb, mail, pole, urlImage);

        try {
            AssociationLibrary.getInstance().modifierAssociation(associationModifie);
            resp.sendRedirect(String.format("IntegraleAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierAssociation?id="+association_id);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer associationId = Integer.parseInt(req.getParameter("id"));
        Association association = AssociationLibrary.getInstance().getAssociation(associationId);

        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("association_id", associationId);
        context.setVariable("nom", association.getNom());
        context.setVariable("mail", association.getMail());
        context.setVariable("nomFb", association.getNomFb());
        context.setVariable("nomPresident", association.getNomPresident());
        context.setVariable("description", association.getDescription());
        context.setVariable("pole", association.getPole());
        context.setVariable("urlImage", association.getUrlImage());

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("FormulaireAssociation", context, resp.getWriter());
    }
}
