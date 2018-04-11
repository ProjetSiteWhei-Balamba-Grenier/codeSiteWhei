package fr.hei.iti.sitewebwhei.servlets;

import fr.hei.iti.sitewebwhei.managers.MembreLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/SupprimerMembre")
public class SupprimerMembreServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialisation du parametre
        Integer membreId = null;

        // Recuperation du parametre
        try {
            membreId = Integer.parseInt(req.getParameter("id"));
        }

        catch (NumberFormatException ignored) {
        }

        // Suppression du membre grace a l'id
        try {
            MembreLibrary.getInstance().deleteMembre(membreId);
            // Redirectiion vers AccueilAdmin
            resp.sendRedirect("AccueilAdmin");
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierMembre?id="+membreId);
        }
    }
}
