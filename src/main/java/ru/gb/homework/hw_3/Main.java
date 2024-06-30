package ru.gb.homework.hw_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        JDBC jdbc = new JDBC();



        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test")) {
            jdbc.createDepartmentsTable(connection);
            jdbc.insertDepartmentsData(connection);
            jdbc.createPersonsTable(connection);
            jdbc.insertPersonsData(connection);
            jdbc.selectDepartmentsData(connection);
            jdbc.selectPersonsData(connection);
            System.out.println(jdbc.selectDepartmentNameByPersonsId(connection,"1"));
            System.out.println(jdbc.selectDepartmentNamesForPerson(connection));
            System.out.println(jdbc.selectPersonNamesForDepartment(connection));
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
