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
import java.util.List;

@WebServlet("/admin/AdresseAdmin")
public class AdresseAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creation d'un context
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // Recuperation de la liste des adresses
        List<Adresse> listOfAdresse = AdresseLibrary.getInstance().listAdresse();
        // Ajout de cette liste au context
        context.setVariable("adresseList", listOfAdresse);

        // Creation d'un templateResolver
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        // Ajout d'un prefix
        templateResolver.setPrefix("/WEB-INF/templates/");
        // Ajout d'un suffix
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Execution du templateEngine avec le fichier AdresseAdmin
        templateEngine.process("AdresseAdmin", context, resp.getWriter());
    }
}
