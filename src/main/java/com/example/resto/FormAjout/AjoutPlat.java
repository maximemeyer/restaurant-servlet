package com.example.resto.FormAjout;

import com.example.resto.DAO.TypeRecetteDAO;
import com.example.resto.Model.TypeRecette;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet qui affiche le formulaire d'ajjout d'un plat : appel confirmAjout pour ajouter en BDD
 */
@WebServlet(name = "ajout-plat-servlet", value = "/ajout-plat-servlet")
public class AjoutPlat extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Je récupère les types de recettes pour afficher dans une liste déroulante
        TypeRecetteDAO typeRecetteDAO = null;
        ArrayList<TypeRecette> lesTypes;
        try {
            typeRecetteDAO = new TypeRecetteDAO();
            lesTypes = typeRecetteDAO.getAllType();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Je récupère l'ID du restaurant concernés
        int id = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        //J'affiche mon formulaire
        out.println("<html>");
        out.println("<body>");
        out.println("<form action=\"confirm-ajout-servlet\" method=\"get\">" +
                "<br>" +
                "<label for=\"nom\">Nom de la recette : </label>" +
                "<input type=\"text\" name=\"nom\" id=\"nom\">" +
                "<label for=\"typeRecette\">Type de recette : </label>" +
                "<select name=\"typeRecette\" id=\"typeRecette\">" +
                "<option value=\"\">--</option>");
                for (TypeRecette type : lesTypes) {
                    out.println("<option value="+type.getIdType()+">"+type.getLibelle()+"</option>");
                }
                out.println("</select>" +
                "<label for=\"prix\">Prix : </label>" +
                "<input type=\"prix\" name=\"prix\" id=\"prix\">" +
                "<input type=\"hidden\" name=\"action\" value=\"ajoutRecette\" />" +
                "<input type=\"hidden\" name=\"id\" value="+id+" />" +
                "<input type=\"submit\" value=\"Ajouter\">" +
                "</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
