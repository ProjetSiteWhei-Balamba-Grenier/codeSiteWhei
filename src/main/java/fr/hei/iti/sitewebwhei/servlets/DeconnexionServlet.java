package fr.hei.iti.sitewebwhei.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/Deconnexion")
public class DeconnexionServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Suppression de la variable utilisateurConnecte
        req.getSession().removeAttribute("utilisateurConnecte");
        // Redirection vers le portail de connexion
        resp.sendRedirect("/PortailAdmin");
    }
}
