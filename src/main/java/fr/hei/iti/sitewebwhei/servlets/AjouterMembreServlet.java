package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Membre;
import fr.hei.iti.sitewebwhei.managers.MembreLibrary;
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
import java.net.URL;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/admin/AjouterMembre")
public class AjouterMembreServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        URL location = AjouterMembreServlet.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        String monChemin = location.getFile();


        // GET PARAMETERS
        String prenom = null;
        String nom = null;
        String poste = null;
        String description = null;
        String urlImage = null;

        try {
            prenom = req.getParameter("prenom");
            nom = req.getParameter("nom");
            poste = req.getParameter("poste");
            description = req.getParameter("description");
            urlImage = monChemin;
        }

        catch (NumberFormatException | DateTimeParseException ignored) {
        }

        // CREATE FILM
        Membre newMembre = new Membre(null, prenom, nom, poste, description, urlImage);

        try {
            Membre createdMembre = MembreLibrary.getInstance().addMembre(newMembre);

            // REDIRECT TO DETAIL FILM
            resp.sendRedirect(String.format("AccueilAdmin"));
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("FormulaireMembre");
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

        templateEngine.process("FormulaireMembre", context, resp.getWriter());

    }
}
