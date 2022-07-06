package cda.sda.jdbc;

import cda.sda.jdbc.DAO.TaskDAO;
import cda.sda.jdbc.model.Task;

import java.sql.SQLException;

public class Main10Exercise {
    public static void main(String[] args) throws SQLException {
        TaskDAO taskDAO = new TaskDAO();
        Task task1 = new Task(0L,"task1 descriptionion", 37L);
        Task task2 = new Task(1L,"task2 descriptionion", 38L);
        Task task3 = new Task(2L,"task3 descriptionion", 37L);
        Task task4 = new Task(3L,"task4 descriptionion", 40L);
        Task task5 = new Task(4L,"task5 descriptionion", 40L);

        taskDAO.create(task1);
        taskDAO.create(task2);
        taskDAO.create(task3);
        taskDAO.create(task4);
        taskDAO.create(task5);

        System.out.println(taskDAO.read(2));

    }
}
