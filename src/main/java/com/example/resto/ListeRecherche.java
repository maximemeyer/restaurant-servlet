package com.example.resto;

import com.example.resto.DAO.RestaurantDAO;
import com.example.resto.Model.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "liste-recherche-servlet", value = "/liste-recherche-servlet")
public class ListeRecherche extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String recherche = request.getParameter("recherche");
        ArrayList<Restaurant> listeRestaurant = new ArrayList<Restaurant>();
        ArrayList<Restaurant> listeRestaurantByNom = new ArrayList<Restaurant>();
        ArrayList<Restaurant> listeRestaurantByType = new ArrayList<Restaurant>();
        RestaurantDAO restaurantDAO = null;
        try {
            restaurantDAO = new RestaurantDAO();
            listeRestaurantByType = restaurantDAO.getRestaurantByType(recherche);
            listeRestaurantByNom = restaurantDAO.getRestaurantByNom(recherche);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (listeRestaurantByNom != null) {
            for (Restaurant unResto: listeRestaurantByNom) {
                listeRestaurant.add(unResto);
            }
        }

        if (listeRestaurantByType != null) {
            for (Restaurant unResto : listeRestaurantByType) {
                if (!listeRestaurantByNom.contains(unResto)) {
                    listeRestaurant.add(unResto);
                }
            }
        }
        out.println("<html><head><link type=\"text/css\" rel=\"stylesheet\" href=\"CSS/style.css\"></head><body>");
        if (listeRestaurant.size() != 0) {
            for (Restaurant unResto : listeRestaurant) {
                out.println("" +
                        "<div class=\"card\">" +
                        "  <h2><b>"+unResto.getNomResto()+"</b></h4>" +
                        "  <img src=\"Assets/download.jpg\" alt=\"Restaurant pictures\" style=\"width:50%\">" +
                        "  <div class=\"container\">" +
                        "    <p>"+unResto.getAdresseResto()+"</p>" +
                        "    <p>"+unResto.getTypeCuisine()+"</p>" +
                        "    <form method=\"get\" action=\"detail-resto-servlet\">" +
                        "    <input type=\"hidden\" name=\"id\" value="+ unResto.getIdResto() + " />" +
                        "    <input type=\"submit\" value=\"Afficher la carte\" />" +
                        "    </form>" +
                        "  </div>" +
                        "</div>");
            }
        } else {
            out.println("<p>Aucun Resultat</p>");
            out.println("<a href=\"index.jsp\">Retour à l'accueil</a>");
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
