package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.entities.Etudiant;
import fr.hei.iti.sitewebwhei.managers.EtudiantLibrary;
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

@WebServlet("/admin/ListeDeDiffusionAdmin")
public class ListeDeDiffusionAdminServlet extends HttpServlet{

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creation d'un context
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // Recuperation de la liste des etudiants
        List<Etudiant> listOfEtudiant = EtudiantLibrary.getInstance().listEtudiant();
        // Ajout de cette liste au context
        context.setVariable("etudiantList", listOfEtudiant);

        // Creation d'un templateResolver
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        // Ajout d'un prefix
        templateResolver.setPrefix("/WEB-INF/templates/");
        // Ajout d'un suffix
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Execution du templateEngine avec le fichier ListeDeDiffusionAmdin
        templateEngine.process("ListeDeDiffusionAdmin", context, resp.getWriter());
    }
}
