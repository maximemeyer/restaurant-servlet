package com.example.resto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet qui demande une confirmation avant suppression de données de la base
 */
@WebServlet(name = "supprimer-servlet", value = "/supprimer-servlet")
public class SupprimerAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        int idRecette = Integer.parseInt(request.getParameter("idRecette"));
        //J'affiche les 2 boutons : un qui confirme et l'autre annule l'opération
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h4>Confirmez vous la suppression ?</h4>");
        out.println("<form method=\"get\" action=\"confirm-supprimer-servlet\">" +
                "        <input type=\"hidden\" name=\"action\" value="+ action + " />" +
                "        <input type=\"hidden\" name=\"id\" value="+ id + " />");
                if (action.equals("supprimerRecette")) {
                    out.println("<input type=\"hidden\" name=\"idRecette\" value="+ idRecette + " />");
        }
                out.println("        <input type=\"submit\" value=\"Confirmer\"/>" +
                "    </form>");
        out.println("<form method=\"get\" action=\"detail-resto-servlet?id="+id+">" +
                "        <input type=\"submit\" value=\"Annuler\"/>" +
                "    </form>");
        out.println("</body></html>");
    }
}
