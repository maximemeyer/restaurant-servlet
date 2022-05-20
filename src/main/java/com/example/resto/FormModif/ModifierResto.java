package com.example.resto.FormModif;

import com.example.resto.DAO.RestaurantDAO;
import com.example.resto.Model.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet qui affiche le formulaire de modification d'un restaurant : appel ConfirmModif pour modifier en base de données
 */
@WebServlet(name = "modifier-resto-servlet", value = "/modifier-resto-servlet")
public class ModifierResto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Je récupère l'ID du restaurant concernés par la modification
        int id = Integer.parseInt(request.getParameter("id"));
        RestaurantDAO restaurantDAO = null;
        Restaurant restaurant;
        //Je récupère les données du restaurant
        try {
            restaurantDAO = new RestaurantDAO();
            restaurant = restaurantDAO.getRestaurantById(id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        //J'affiche le formulaire
        out.println("<html>");
        out.println("<body>");
        out.println("<form action=\"confirm-modif-servlet\" method=\"get\">" +
                "<label for=\"nom\">Nom du restaurant : </label>" +
                "<input type=\"text\" name=\"nom\" id=\"nom\" value="+restaurant.getNomResto()+">" +
                "<label for=\"adresse\">Adresse du restaurant: </label>" +
                "<input type=\"adresse\" name=\"adresse\" id=\"adresse\" value="+restaurant.getAdresseResto()+">" +
                "<label for=\"typecuisine\">Type de cuisine du restaurant : </label>" +
                "<input type=\"typecuisine\" name=\"typecuisine\" id=\"typecuisine\" value="+restaurant.getTypeCuisine()+">" +
                "<input type=\"hidden\" name=\"action\" value=\"modifResto\" />" +
                "<input type=\"hidden\" name=\"id\" value="+id+" />" +
                "<input type=\"submit\" value=\"Modifier\">" +
                "</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
