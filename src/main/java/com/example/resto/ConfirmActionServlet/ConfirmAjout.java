package com.example.resto.ConfirmActionServlet;

import com.example.resto.DAO.RecetteDAO;
import com.example.resto.DAO.RestaurantDAO;
import com.example.resto.DAO.TypeRecetteDAO;
import com.example.resto.Model.Recette;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet qui ajoute une recette ou un restaurant en fonction de l'action et redirige à la page d'accueil ou à la page detail du restaurant
 */
@WebServlet(name = "ConfirmAjout", value = "/confirm-ajout-servlet")
public class ConfirmAjout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Je récupère l'action que je souhaite effectuer
        String action = request.getParameter("action");
        String urlRetour = null;
        if (action.equals("ajoutResto")) {
            //Je récupère les données saisie pour l'ajout d'un restaurant
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String typeResto = request.getParameter("typecuisine");
            RestaurantDAO restaurantDAO = null;
            try {
                //J'ajoute à la BDD
                restaurantDAO = new RestaurantDAO();
                restaurantDAO.ajouteRestaurant(nom, adresse, typeResto);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //Je contruit mon URL de retour
            urlRetour = "index.jsp";
        } else if (action.equals("ajoutRecette")) {
            //Je récupère les données saisie dans le formulaire pour l'ajout d'une recette
            int idResto = Integer.parseInt(request.getParameter("id"));
            String nomRecette = request.getParameter("nom");
            int typeRecette = Integer.parseInt(request.getParameter("typeRecette"));
            double prix = Double.parseDouble(request.getParameter("prix"));
            RecetteDAO recetteDAO = null;
            TypeRecetteDAO typeRecetteDAO = null;
            try {
                //J'ajoute la recette à ma BDD
                recetteDAO = new RecetteDAO();
                recetteDAO.addRecetteToResto(idResto, nomRecette, typeRecette, prix);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //Je constuit mon URL de retour
            urlRetour = "detail-resto-servlet?id="+idResto;
        }
        //Je redirige l'utilisateur
        response.sendRedirect(urlRetour);
    }
}
