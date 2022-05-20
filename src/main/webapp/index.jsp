<%@ page import="com.example.resto.DAO.RestaurantDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.resto.Model.Restaurant" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Annuaire de restaurant</title>
    <link type="text/css" rel="stylesheet" href="CSS/style.css">
</head>
<body>
<header>

</header>

<%
    //J'affiche les différents restaurant stockées en base
    PrintWriter sortie = response.getWriter();
    RestaurantDAO restoDAO = new RestaurantDAO();
    ArrayList<Restaurant> listeRestaurants = new ArrayList<>();
    listeRestaurants = restoDAO.getAllRestaurant();
    sortie.println("<form method=\"get\" action=\"liste-recherche-servlet\">");
    sortie.println("<input id=\"recherche\" type=\"text\" name=\"recherche\" placeholder=\"Rechercher un restaurant\">");
    sortie.println("<input type=\"submit\" value=\"Recherche\" />");
    sortie.println("</form>" +
            "    <form method=\"get\" action=\"ajout-resto-servlet\">" +
            "        <input type=\"submit\" value=\"Ajouter un restaurant\"/>" +
            "    </form>");
    for (Restaurant unResto:listeRestaurants) {
        sortie.println("" +
                "<div class=\"card\">\n" +
                "  <h2><b>"+unResto.getNomResto()+"</b></h4>\n" +
                "  <img src=\"Assets/download.jpg\" alt=\"Restaurant pictures\" style=\"width:50%\">\n" +
                "  <div class=\"container\">\n" +
                "    <p>"+unResto.getAdresseResto()+"</p>\n" +
                "    <p>"+unResto.getTypeCuisine()+"</p>\n" +
                "    <form method=\"get\" action=\"detail-resto-servlet\">" +
                "    <input type=\"hidden\" name=\"id\" value="+ unResto.getIdResto() + " />" +
                "    <input type=\"submit\" value=\"Afficher la carte\" />" +
                "    </form>" +
                "  </div>\n" +
                "</div>");
    }
%>
</body>
</html>