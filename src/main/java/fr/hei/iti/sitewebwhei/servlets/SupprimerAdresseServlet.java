package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.managers.AdresseLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/SupprimerAdresse")
public class SupprimerAdresseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialisation du parametre
        Integer adresseId = null;

        // Recuperation du parametre
        try {
            adresseId = Integer.parseInt(req.getParameter("id"));
        }

        catch (NumberFormatException ignored) {
        }

        // Suppression de l'adresse grace a l'id
        try {
            AdresseLibrary.getInstance().deleteAdresse(adresseId);
            // Redirection vers AdresseAdmin
            resp.sendRedirect("AdresseAdmin");
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierAdresse?id="+adresseId);
        }
    }
}
