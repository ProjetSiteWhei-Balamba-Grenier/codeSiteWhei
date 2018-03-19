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

        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Etudiant> listOfEtudiant = EtudiantLibrary.getInstance().listEtudiant();
        context.setVariable("etudiantList", listOfEtudiant);

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("ListeDeDiffusionAdmin", context, resp.getWriter());
    }
}
