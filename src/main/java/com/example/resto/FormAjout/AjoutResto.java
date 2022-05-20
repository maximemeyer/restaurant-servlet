package com.example.resto.FormAjout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet qui affiche le formulaire d'ajout d'un restaurant : appel ConfirmAjout pour ajout en BDD
 */
@WebServlet(name = "ajout-resto-servlet", value = "/ajout-resto-servlet")
public class AjoutResto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //J'affiche mon formulaire
        out.println("<html>");
        out.println("<body>");
        out.println("<form action=\"confirm-ajout-servlet\" method=\"get\">" +
                "<label for=\"nom\">Nom du restaurant : </label>" +
                "<input type=\"text\" name=\"nom\" id=\"nom\">" +
                "<label for=\"adresse\">Adresse du restaurant: </label>" +
                "<input type=\"adresse\" name=\"adresse\" id=\"adresse\">" +
                "<label for=\"typecuisine\">Type de cuisine du restaurant : </label>" +
                "<input type=\"typecuisine\" name=\"typecuisine\" id=\"typecuisine\">" +
                "<input type=\"hidden\" name=\"action\" value=\"ajoutResto\" />" +
                "<input type=\"submit\" value=\"Ajouter\">" +
                "</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
