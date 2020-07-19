package lesson3;

import com.mysql.cj.jdbc.MysqlDataSource;


import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        addUser("Bob 32 bob32@gmail.com");
        addUser("Вася 22 vasya@pipkin.ru");
        addUser("Петя 33 petya@petrov.ru");
        addUser("Маша 44 masha@ivanova.ru");

        System.out.println("All users");
        printUsers();

        System.out.println("Users with age from 30 to 50");
        printUsersByAge(30, 50);

        System.out.println("Deleted Маша");
        deleteUserByName("Маша");

        System.out.println("All users");
        printUsers();
    }

    public static void addUser(String params)
    {
        String[] paramsSplit = params.split(" ");

        try {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO users (name, age, email) VALUES (?, ?, ?)"
            );
            stmt.setString(1, paramsSplit[0]);
            stmt.setInt(2, Integer.parseInt(paramsSplit[1]));
            stmt.setString(3, paramsSplit[2]);
            stmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void printUsers()
    {
        try {
            Connection connection = getConnection();

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            printUsersByResultSet(rs);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printUsersByAge(int min, int max)
    {
        try {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM users WHERE age BETWEEN ? and ?"
            );
            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();

            printUsersByResultSet(rs);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserByName(String name)
    {
        try {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM users WHERE name = ?"
            );
            stmt.setString(1, name);
            stmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void printUsersByResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String email = rs.getString("email");
            System.out.println("Name: " + name + ", age: " + age + ", email: " + email);
        }
    }

    protected static Connection getConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("rootpassword");
        dataSource.setUrl("jdbc:mysql://localhost:3306/java3lesson3");
        dataSource.setServerTimezone("UTC");
        dataSource.setCharacterEncoding("utf8");

        return dataSource.getConnection();
    }
}
