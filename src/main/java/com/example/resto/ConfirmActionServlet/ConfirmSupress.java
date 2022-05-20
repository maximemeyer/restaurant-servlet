package com.example.resto.ConfirmActionServlet;

import com.example.resto.DAO.RecetteDAO;
import com.example.resto.DAO.RestaurantDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Servlet qui supprime un restaurant ou une recette
 */
@WebServlet(name = "confirm-supprimer-servlet", value = "/confirm-supprimer-servlet")
public class ConfirmSupress extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Je récupère l'action souhaitée
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        String redirectURL = null;
        if (action.equals("supprimerResto")) {
            RestaurantDAO restaurantDAO = null;
            try {
                //Je supprime le restaurant de la BDD
                restaurantDAO = new RestaurantDAO();
                restaurantDAO.supprimerRestaurant(id);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //Je construit mon URL de redirection
            redirectURL = "index.jsp";
        } else if (action.equals("supprimerRecette")) {
            //je récupère l'ID de ma recette
            int idRecette = Integer.parseInt(request.getParameter("idRecette"));
            RecetteDAO recetteDAO = null;
            try {
                //Je supprime ma recette
                recetteDAO = new RecetteDAO();
                recetteDAO.deleteRecetteById(idRecette);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //Je construit mon URL de redirection
            redirectURL = "detail-resto-servlet?id="+id;
        }
        //Je redirige mon utilisateur
        response.sendRedirect(redirectURL);
    }
}
