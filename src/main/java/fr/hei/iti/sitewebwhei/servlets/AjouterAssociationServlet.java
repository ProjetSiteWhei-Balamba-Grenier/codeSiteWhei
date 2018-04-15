package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Association;
import fr.hei.iti.sitewebwhei.managers.AssociationLibrary;
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

@WebServlet("/admin/AjouterAssociation")
public class AjouterAssociationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nom = null;
        String nomFb=null;
        String nomPresident=null;
        String mail=null;
        String description = null;
        String pole = null;
        String urlImage = null;

        try {
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

        Association newAssociation = new Association(null, nom, description, nomPresident, nomFb, mail, pole, urlImage);

        try {
            AssociationLibrary.getInstance().addAssociation(newAssociation);
            resp.sendRedirect(String.format("IntegraleAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("AjouterAssociation");
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

        // Execution du templateEngine avec le fichier FormulaireContact
        templateEngine.process("FormulaireAssociation", context, resp.getWriter());

    }

}
