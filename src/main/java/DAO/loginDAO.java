/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edrag
 */
public class loginDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/segumas";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean validate(String username, String pass) {
        boolean status = false;
        try (Connection connection = getConnection();//connect database guna method getConnection 
                 PreparedStatement ps = connection.prepareStatement("select * from admin where username=? and pass=?");) {

            ps.setString(1, username);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
