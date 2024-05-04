package org.example;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:hello.sqlite";
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        //String createTableSQL = "CREATE TABLE cats (name TEXT, age INTEGER)";
        //statement.executeUpdate(createTableSQL);

        //String insertDataSQL = "INSERT INTO cats VALUES ('Maru' , 10)";
        //statement.executeUpdate(insertDataSQL);

        // String insertDataSQL = "Insert into cats VALUES ('Hello Kitty' , 40)";
        // statement.executeUpdate(insertDataSQL);

        String getAllDataSQL = "SELECT * FROM cats";
        ResultSet allCats = statement.executeQuery(getAllDataSQL);

        while (allCats.next()) {
            String name = allCats.getString("name");
            int age = allCats.getInt("age");
            System.out.println( name + "is " + age + " years old");
        }


    }
}
