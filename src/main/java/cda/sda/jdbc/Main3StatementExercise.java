package cda.sda.jdbc;

import java.sql.*;

import static cda.sda.jdbc.Configuration.*;

public class Main3StatementExercise {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        boolean execute = statement.execute("CREATE TABLE IF NOT EXISTS user(id BIGINT NOT NULL AUTO_INCREMENT, username VARCHAR(50) UNIQUE, password VARCHAR(50), PRIMARY KEY(id))");
//        System.out.println(execute);

        statement.executeUpdate("INSERT INTO user(username, password) VALUES" +
                "('Adam','1234')," +
                "('Paweł','abcd')," +
                "('Happy','Password')");

        ResultSet resultSet = statement.executeQuery("SELECT username FROM user");
        while (resultSet.next()) {
            String name = resultSet.getString("username");
            System.out.println(name);
        }
        resultSet.close();

        statement.executeUpdate("DELETE FROM user");

        statement.close();
        connection.close();
    }
}

