package com.example.resto;

import com.example.resto.DAO.RecetteDAO;
import com.example.resto.DAO.RestaurantDAO;
import com.example.resto.Model.Recette;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private ArrayList<Recette> listeRecette;

    public void init() {
        listeRecette = new ArrayList<>();
        try {
            RecetteDAO recetteDAO = new RecetteDAO();
            listeRecette = recetteDAO.getRecettesResto(1);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body>");
        out.println("<h1>Carte du restaurant</h1>");
        out.println("<h1>Carte du restaurant</h1>");
        out.println("</body></html>");

    }

    public void destroy() {
    }
}