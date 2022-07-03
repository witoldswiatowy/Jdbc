package cda.sda.jdbc;

import java.sql.*;
import java.util.List;

import static cda.sda.jdbc.Configuration.*;

public class Main5PreparedStatementExercise {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(username, password) VALUES (?,?)");

        List<String> name = List.of("Jan", "Ala", "Mikołaj", "Ola");
        List<String> password = List.of("password1", "password2", "password3", "password3");

        for (int i = 0; i < name.size(); i++) {
        preparedStatement.setString(1, name.get(i));
        preparedStatement.setString(2, password.get(i));
        preparedStatement.executeUpdate();
        }

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        while (resultSet.next()) {
            String name1 = resultSet.getString("username");
            System.out.println(name1);
        }
        resultSet.close();

//        statement.executeUpdate("DELETE FROM user");

        preparedStatement.close();
        connection.close();
    }

}
