package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.managers.MoyenDeContactLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/SupprimerContact")
public class SupprimerContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialisation du parametre
        Integer moyenDeContactId = null;

        // Recuperation du parametre
        try {
            moyenDeContactId = Integer.parseInt(req.getParameter("id"));
        }

        catch (NumberFormatException ignored) {
        }

        // Suppression du moyen de contact grace a l'id
        try {
            MoyenDeContactLibrary.getInstance().deleteMoyenDeContact(moyenDeContactId);
            // Redirection vers ContactAdmin
            resp.sendRedirect("ContactAdmin");
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierContact?id="+moyenDeContactId);
        }
    }
}
