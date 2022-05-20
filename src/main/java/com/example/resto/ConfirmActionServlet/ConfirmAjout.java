package com.example.resto;

import com.example.resto.DAO.RecetteDAO;
import com.example.resto.DAO.RestaurantDAO;
import com.example.resto.DAO.TypeRecetteDAO;
import com.example.resto.Model.Recette;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ConfirmAjout", value = "/confirm-ajout-servlet")
public class ConfirmAjout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String urlRetour = null;
        if (action.equals("ajoutResto")) {
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String typeResto = request.getParameter("typecuisine");
            RestaurantDAO restaurantDAO = null;
            try {
                restaurantDAO = new RestaurantDAO();
                restaurantDAO.ajouteRestaurant(nom, adresse, typeResto);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            urlRetour = "index.jsp";
        } else if (action.equals("ajoutRecette")) {
            int idResto = Integer.parseInt(request.getParameter("id"));
            String nomRecette = request.getParameter("nom");
            int typeRecette = Integer.parseInt(request.getParameter("typeRecette"));
            double prix = Double.parseDouble(request.getParameter("prix"));
            RecetteDAO recetteDAO = null;
            TypeRecetteDAO typeRecetteDAO = null;
            try {
                recetteDAO = new RecetteDAO();
                recetteDAO.addRecetteToResto(idResto, nomRecette, typeRecette, prix);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            urlRetour = "detail-resto-servlet?id="+idResto;
        }
        response.sendRedirect(urlRetour);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
