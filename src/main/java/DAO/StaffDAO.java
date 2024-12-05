/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.staff;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edrag
 */
public class StaffDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/segumas";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_STAFF_SQL = "INSERT INTO staff (staffID,fullname,username,password) values (?,?,?,?)";
    private static final String SELECT_ALL_STAFF = "SELECT * from staff";
    private static final String SELECT_STAFF_BY_ID = "SELECT staffID,fullname,username,password from staff where staffID = ?";
    private static final String UPDATE_STAFF_SQL = "UPDATE staff set fullname= ?,username= ?,password= ? where staffID = ?";
    private static final String DELETE_STAFF_SQL = "DELETE from staff where staffID =?";

    protected Connection getConnection() { //mathod connection

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertStaff(staff staff) {
        System.out.println(INSERT_STAFF_SQL);

        try (Connection connection = getConnection();//connect database guna method getConnection 
                 PreparedStatement ps = connection.prepareStatement(INSERT_STAFF_SQL);) { //insert data SQL
            ps.setString(1, staff.getStaffID());
            ps.setString(2, staff.getFullname());
            ps.setString(3, staff.getUsername());
            ps.setString(4, staff.getPassword());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    //select all staff
    public List<staff> selectAllStaff() {

        List<staff> staffs = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String staffID = rs.getString("staffID");
                String fullname = rs.getString("fullname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                staffs.add(new staff(staffID, fullname, username, password));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return staffs;
    }

    //select staff by id
    public staff selectStaff(String staffID) {
        staff staff = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
            preparedStatement.setString(1, staffID);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String fullname = rs.getString("fullname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                staff = new staff(staffID, fullname, username, password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return staff;
    }

    //update staff
    public boolean updateStaff(staff updateStaff) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_STAFF_SQL);) {
            ps.setString(1, updateStaff.getFullname());
            ps.setString(2, updateStaff.getUsername());
            ps.setString(3, updateStaff.getPassword());
            ps.setString(4, updateStaff.getStaffID());

            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteStaff(String staffID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STAFF_SQL);) {
            statement.setString(1, staffID);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
