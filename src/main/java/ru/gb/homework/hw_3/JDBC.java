package ru.gb.homework.hw_3;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class JDBC {

    public void createPersonsTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                create table persons (
                id bigint not null primary key,
                department_id bigint,
                name varchar(256) not null,
                age integer,
                active boolean,
                foreign key (department_id) references departments(id)
                )
                """);
        }
    }

    public void createDepartmentsTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    create table departments (
                    id bigint not null primary key,
                    name varchar(128) not null
                    )
                    """);
        }
    }

    public void insertPersonsData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            StringBuilder insert = new StringBuilder("insert into persons (id, department_id, name, age, active) values");
            for (int i = 1; i <= 10; i++) {
                int age = ThreadLocalRandom.current().nextInt(20, 60);
                boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
                long randId = ThreadLocalRandom.current().nextInt(1, 11);
                insert.append(String.format("\n(%s, %s, '%s', %s, %s)", i, randId, "Person #" + i, age, nextBoolean));

                if (i != 10) insert.append(",");
            }

            int insertCount = statement.executeUpdate(insert.toString());
            System.out.println("Rows inserted: " + insertCount);
        }
    }

    public void insertDepartmentsData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            StringBuilder insert = new StringBuilder("insert into departments (id, name) values");
            for (int i = 1; i <= 10; i++) {
                insert.append(String.format("\n(%s, '%s')", i, "Department #" + i));

                if (i != 10) insert.append(",");
            }

            int insertCount = statement.executeUpdate(insert.toString());
            System.out.println("Rows inserted: " + insertCount);
        }
    }

    public void selectPersonsData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from persons");

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long depId= resultSet.getLong("department_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                boolean active = resultSet.getBoolean("active");
                System.out.println("Row was found: [id = " + id + ", department_id = " + depId +
                        ", name = " + name + ", age = " + age + ", active = " + active + "]");
            }
        }
    }

    public void selectDepartmentsData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from departments");

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                System.out.println("Row was found: [id = " + id +
                        ", name = " + name + "]");
            }
        }
    }

    public String selectDepartmentNameByPersonsId (Connection connection, String id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("""
select d.name
from departments d
join persons p on d.id =
p.department_id where p.id = ?
""")) {
            statement.setLong(1, Long.parseLong(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        }
        return null;
    }

    /*
    метод, который загружает Map<String, String>, в которой маппинг person.name -> department.name
     */
    public Map<String, String> selectDepartmentNamesForPerson (Connection connection) throws SQLException {
        Map<String, String> map = new HashMap<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select p.name as person_name, d.name as department_name
                    from departments d
                    join persons p on d.id =
                    p.department_id
                    """);

            while (resultSet.next()) {
                String personName = resultSet.getString("person_name");
                String departmentName = resultSet.getString("department_name");
                map.put(personName, departmentName);
            }

            return map;
        }
    }

    /*
    метод, который загружает Map<String, List<String>>, в которой маппинг department.name -> <person.name>
     */
    public Map<String, List<String>> selectPersonNamesForDepartment (Connection connection) throws SQLException {
        Map<String, List<String>> map = new HashMap<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select p.name as person_name, d.name as department_name
                    from departments d
                    join persons p on d.id =
                    p.department_id
                    """);

            while (resultSet.next()) {
                String personName = resultSet.getString("person_name");
                String departmentName = resultSet.getString("department_name");
                if (!map.containsKey(departmentName)) {
                    map.put(departmentName, new ArrayList<>());
                }
                map.get(departmentName).add(personName);
            }

            return map;
        }
    }
}
