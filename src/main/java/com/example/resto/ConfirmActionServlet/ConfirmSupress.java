package com.example.resto;

import com.example.resto.DAO.RecetteDAO;
import com.example.resto.DAO.RestaurantDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ResourceBundle;

@WebServlet(name = "confirm-supprimer-servlet", value = "/confirm-supprimer-servlet")
public class ConfirmSupress extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        String redirectURL = null;
        if (action.equals("supprimerResto")) {
            RestaurantDAO restaurantDAO = null;
            try {
                restaurantDAO = new RestaurantDAO();
                restaurantDAO.supprimerRestaurant(id);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            redirectURL = "index.jsp";
        } else if (action.equals("supprimerRecette")) {
            int idRecette = Integer.parseInt(request.getParameter("idRecette"));
            RecetteDAO recetteDAO = null;
            try {
                recetteDAO = new RecetteDAO();
                recetteDAO.deleteRecetteById(idRecette);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            redirectURL = "detail-resto-servlet?id="+id;
        }
        response.sendRedirect(redirectURL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
