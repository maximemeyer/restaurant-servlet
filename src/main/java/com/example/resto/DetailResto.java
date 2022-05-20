package com.example.resto;

import com.example.resto.DAO.RecetteDAO;
import com.example.resto.DAO.TypeRecetteDAO;
import com.example.resto.Model.Recette;
import com.example.resto.Model.TypeRecette;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Servlet qi affiche la carte d'un restaurant et les boutons d'ajout de plat, modification de plat et restaurant et suppression de plat et de restaurant
 */
@WebServlet(name = "helloServlet", value = "/detail-resto-servlet")
public class DetailResto extends HttpServlet {
    private ArrayList<Recette> listeRecette;
    private String libelle;

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Je récupère l'ID du resto
        int id = Integer.parseInt(request.getParameter("id"));
        listeRecette = new ArrayList<>();
        //Je récupère les recettes
        try {
            RecetteDAO recetteDAO = new RecetteDAO();
            listeRecette = recetteDAO.getRecettesResto(id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Je récupère le type d'une recette
        TypeRecetteDAO typeRecetteDAO;
        try {
            typeRecetteDAO = new TypeRecetteDAO();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        //J'affiche les boutons d'actions et la carte
        response.setContentType("text/html");

        out.println("<html><head><link type=\"text/css\" rel=\"stylesheet\" href=\"CSS/style.css\"></head><body>");
        out.println("<header>");
        out.println("<form method=\"get\" action=\"ajout-plat-servlet\">" +
                "        <input type=\"hidden\" name=\"id\" value="+ id + " />" +
                "        <input type=\"submit\" value=\"Ajouter un plat\"/>" +
                "    </form>");
        out.println("<form method=\"get\" action=\"supprimer-servlet\">" +
                "        <input type=\"hidden\" name=\"id\" value="+ id + " />" +
                "        <input type=\"hidden\" name=\"action\" value=\"supprimerResto\" />" +
                "        <input type=\"submit\" value=\"Supprimer le restaurant\"/>" +
                "    </form>");
        out.println("<form method=\"get\" action=\"modifier-resto-servlet\">" +
                "        <input type=\"hidden\" name=\"id\" value="+ id + " />" +
                "        <input type=\"submit\" value=\"Modifier des informations\"/>" +
                "    </form>");
        out.println("</header>");
        out.println("<h1>Carte du restaurant</h1>");
        for (Recette recette:listeRecette) {
            out.println("<div class=\"card\"");
            out.println("<h4>"+recette.getNomRecette()+"</h4>");
            try {
                out.println("<h6> Type : "+typeRecetteDAO.getTypeById(recette.getIdTypeRecette())+"</h6>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            out.println("<h6> Prix : "+recette.getPrix()+"</h6>");
            out.println("<form method=\"get\" action=\"modifier-recette-servlet\">" +
                    "        <input type=\"hidden\" name=\"id\" value="+ recette.getIdRecette() + " />" +
                    "        <input type=\"hidden\" name=\"idRecette\" value="+ recette.getIdRecette() + " />" +
                    "        <input type=\"submit\" value=\"Modifier la recette\"/>" +
                    "    </form>");
            out.println("<form method=\"get\" action=\"supprimer-servlet\">" +
                    "        <input type=\"hidden\" name=\"id\" value="+ id + " />" +
                    "        <input type=\"hidden\" name=\"idRecette\" value="+ recette.getIdRecette() + " />" +
                    "        <input type=\"hidden\" name=\"action\" value=\"supprimerRecette\" />" +
                    "        <input type=\"submit\" value=\"Supprimer la recette\"/>" +
                    "    </form>");
            out.println("</div>");
        }
        out.println("</body></html>");

    }

    public void destroy() {
    }
}