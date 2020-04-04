package lab5a;

import konovalov.dnd.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class WorkWithDB extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Query query = new Query();
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n");
        String out = query.executeQuery("SELECT CharacterName, ArmorName, ArmorTypeName " +
                "FROM PlayersCharacter LEFT OUTER JOIN Armor ON (PlayersCharacter.Armor = Armor.ArmorId) " +
                "LEFT OUTER JOIN ArmorType ON (Armor.ArmorType = ArmorType.ArmorTypeId);");
        printWriter.println(out + "</body>" + "</html>");

    }
}
