package cda.sda.jdbc.DAO;

import cda.sda.jdbc.model.Task;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static cda.sda.jdbc.Configuration.*;

public class TaskDAO implements AutoCloseable {

    private Connection connection = null;

    public TaskDAO() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement(); //////WITEK : Tutaj niżej możemy sobie dodać autoincrement
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS task(id BIGINT NOT NULL, description VARCHAR(255), user_id BIGINT, PRIMARY KEY (id), CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user(id))");
            statement.executeUpdate("DELETE from task");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Task task) throws SQLException {
        // tworzymy nowy task w bazie danych na podstawie informacji z argumentu
        try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO task(id, description, user_id) VALUE" +
                "('" + task.getId() + "','" + task.getDescription() + "','" + task.getUserId() + "')");
        statement.close();
            System.out.println("Task: " + task.getId() + " został dodany");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Task> read(long id) throws SQLException {
        // wyciągamy dane z bazy na podstawie id taska i przypisujemy do obiektu klasy Task
        // jeśli znajdzie wiersz to zwracamy Optional.of(new Task(...))
        // jeśli nie znajdzie to Optional.empty()
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT " + id + " FROM task");
            Optional<Task> task = Optional.empty();
            while (resultSet.next()) {
                task = Optional.of(new Task(
                        resultSet.getLong("id"),
                        resultSet.getString("description"),
                        resultSet.getLong("user_id")));
            }
            resultSet.close();
            return task;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Task> readAll() throws SQLException {
        // wyciągamy wszystkie wiersze z bazy danych
        // wyniki zapisujemy w liście obiektów klasy Task
        return Collections.emptyList();
    }

    public void update(Task task) throws SQLException {
        // aktualizujemy description i user_id na podstawie id taska
    }

    public void delete(long id) throws SQLException {
        // usuwamy wiersz z bazy na podstawie id taska
    }

    public List<Task> readAllForUser(String username) throws SQLException {
        // dla ochotników
        // konstruujemy query z użyciem JOIN i odwołaniem do tabeli user
        return Collections.emptyList();
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
