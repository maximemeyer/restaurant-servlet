package com.example.resto.FormModif;

import com.example.resto.DAO.RecetteDAO;
import com.example.resto.DAO.TypeRecetteDAO;
import com.example.resto.Model.Recette;
import com.example.resto.Model.TypeRecette;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet qui affiche le formulaire de modification d'une recette : appel ConfirmModif pour modification en BDD
 */
@WebServlet(name = "modifier-recette-servlet", value = "/modifier-recette-servlet")
public class ModifierRecette extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Je récupère l'id du restaurant et de la recette concernés par la modification
        int id = Integer.parseInt(request.getParameter("id"));
        int idRecette = Integer.parseInt(request.getParameter("idRecette"));
        RecetteDAO recetteDAO = null;
        TypeRecetteDAO typeRecetteDAO = null;
        Recette recette;
        ArrayList<TypeRecette> lesTypes;
        //Je charge les informations de ma recette et les différents types de plats
        try {
            recetteDAO = new RecetteDAO();
            recette = recetteDAO.getRecetteById(idRecette);
            typeRecetteDAO = new TypeRecetteDAO();
            lesTypes = typeRecetteDAO.getAllType();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        //J'affiche mon formulaire
        out.println("<html>");
        out.println("<body>");
        try {
            out.println("<form action=\"confirm-modif-servlet\" method=\"get\">" +
                    "<label for=\"nom\">Nom de la recette : </label>" +
                    "<input type=\"text\" name=\"nom\" id=\"nom\" value="+recette.getNomRecette()+">" +
                    "<label for=\"typeRecette\">Type de recette : </label>" +
                    "<select name=\"typeRecette\" id=\"typeRecette\">" +
                    "<option value="+recette.getIdTypeRecette()+">"+typeRecetteDAO.getTypeById(recette.getIdTypeRecette())+"</option>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (TypeRecette type : lesTypes) {
                    out.println("<option value="+type.getIdType()+">"+type.getLibelle()+"</option>");
                }
                out.println("</select>" +
                "<label for=\"prix\">Prix : </label>" +
                "<input type=\"prix\" name=\"prix\" id=\"prix\" value="+recette.getPrix()+">" +
                "<input type=\"hidden\" name=\"action\" value=\"modifRecette\" />" +
                "<input type=\"hidden\" name=\"idRecette\" value="+idRecette+" />" +
                "<input type=\"hidden\" name=\"id\" value="+id+" />" +
                "<input type=\"submit\" value=\"Modifier\">" +
                "</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
