package cda.sda.jdbc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static cda.sda.jdbc.Configuration.*;

public class Main7UserExercise {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user"); //właściwie prawie zawsze trzeba dodać jakiś LIMIT
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM user LIMIT 10");

        List<User> users = new ArrayList<>();

        while (resultSet.next()){
            long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            User user = new User(id, username, password);
            users.add(user);
        }

//        User user1 = new User(0L,"Ale","password1");


        resultSet.close();
        statement.close();
        connection.close();

        users.forEach(System.out::println);
    }
}
