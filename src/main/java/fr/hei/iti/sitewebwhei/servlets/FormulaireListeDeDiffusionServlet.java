package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Etudiant;
import fr.hei.iti.sitewebwhei.managers.EtudiantLibrary;
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
import java.lang.annotation.ElementType;
import java.time.format.DateTimeParseException;

@WebServlet("/FormulaireListeDeDiffusion")
public class FormulaireListeDeDiffusionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // GET PARAMETERS
        String prenom = null;
        String nom = null;
        String telephone = null;

        try {
            prenom = req.getParameter("prenom");
            nom = req.getParameter("nom");
            telephone = req.getParameter("telephone");
        }

        catch (NumberFormatException | DateTimeParseException ignored) {
        }

        // CREATE ADRESSE
        Etudiant newEtudiant = new Etudiant(null, prenom, nom, telephone);

        try {
            Etudiant createdEtudiant = EtudiantLibrary.getInstance().addEtudiant(newEtudiant);

            // REDIRECT TO DETAIL FILM
            resp.sendRedirect(String.format("Contact"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("FormulaireListeDeDiffusion");
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

        templateEngine.process("FormulaireListeDeDiffusion", context, resp.getWriter());

    }
}