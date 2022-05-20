package com.example.resto.ConfirmActionServlet;

import com.example.resto.DAO.RecetteDAO;
import com.example.resto.DAO.RestaurantDAO;
import com.example.resto.Model.Recette;
import com.example.resto.Model.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet qui modifie une recette en fonction de l'action passé en paramètre
 */
@WebServlet(name = "confirm-modif-servlet", value = "/confirm-modif-servlet")
public class ConfirmModif extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Je récupère l'action souhaité
        String action = request.getParameter("action");
        String returnURL = null;
        //Je récupère l'ID du restaurant
        int id = Integer.parseInt(request.getParameter("id"));
        if (action.equals("modifResto")) {
            //Je récupère les données saisie pour la modification d'un restaurant
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String typecuisine = request.getParameter("typecuisine");
            //Je construit mon objet
            Restaurant restaurant = new Restaurant(id, nom, adresse, typecuisine);
            RestaurantDAO restaurantDAO = null;
            try {
                //Je modifie les informtions dans la BDD
                restaurantDAO = new RestaurantDAO();
                restaurantDAO.modifRestaurant(restaurant);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //Je construit mon URL de retour
            returnURL = "detail-resto-servlet?id="+id;
        } else if (action.equals("modifRecette")) {
            //Je récupère les données saisie pour la modification d'une recette
            int idRecette = Integer.parseInt(request.getParameter("idRecette"));
            String nom = request.getParameter("nom");
            int typeRecette = Integer.parseInt(request.getParameter("typeRecette"));
            double prix = Double.parseDouble(request.getParameter("prix"));
            //Je contruit mon objet
            Recette recette = new Recette(idRecette, id, nom, typeRecette, prix);
            RecetteDAO recetteDAO = null;
            try {
                //Je modifie les informations de la BDD
                recetteDAO = new RecetteDAO();
                recetteDAO.modifRecetteToResto(recette);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //Je construit mon URL de retour
            returnURL = "detail-resto-servlet?id="+id;
        }
        //Je redirige l'utilisateur après la modification
        response.sendRedirect(returnURL);
    }
}
