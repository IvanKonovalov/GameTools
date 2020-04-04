package konovalov.dnd;

import java.sql.*;

public class Query {
    public  String executeQuery(String Query){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder result = new StringBuilder();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dnd",
                    "postgres", "9742");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query);
            while(resultSet.next()){
                 result.append(resultSet.getString("charactername")).append("  ")
                         .append(resultSet.getString("armorname")).append("  ")
                         .append(resultSet.getString("armortypename")).append("<br>");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
