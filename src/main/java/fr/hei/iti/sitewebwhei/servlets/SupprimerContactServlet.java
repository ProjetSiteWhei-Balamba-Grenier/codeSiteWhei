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

        Integer moyenDeContactId = null;

        try {
            moyenDeContactId = Integer.parseInt(req.getParameter("id"));

        }

        catch (NumberFormatException ignored) {
        }

        try {
            MoyenDeContactLibrary.getInstance().deleteMoyenDeContact(moyenDeContactId);
            resp.sendRedirect("ContactAdmin");
        }

        catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ModifierContact?id="+moyenDeContactId);
        }
    }
}
