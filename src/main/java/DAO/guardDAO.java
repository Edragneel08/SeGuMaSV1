/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.guard;
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
public class guardDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/segumas";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_GUARD_SQL = "INSERT INTO guardprofile (guardID, guardName, guardAge, guardGender, guardPhoneNo, guardEmail, nationality, guardRace, guardSalary, guardDOB, branchID, siteID) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_GUARD = "SELECT * from guardprofile";
    private static final String SELECT_GUARD_BY_ID = "SELECT guardID, guardName, guardAge, guardGender, guardPhoneNo, guardEmail, nationality, guardRace, guardSalary, guardDOB, branchID, siteID from guardprofile where guardID = ?";
    private static final String UPDATE_GUARD_SQL = "UPDATE guardprofile set guardName=?, guardAge=?, guardGender=?, guardPhoneNo=?, guardEmail=?, nationality=?, guardRace=?, guardSalary=?, guardDOB=?, branchID=?, siteID=? where guardID = ?";
    private static final String DELETE_GUARD_SQL = "DELETE from guardprofile where guardID =?";

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

    public void insertGuard(guard newGuard) {
        System.out.println(INSERT_GUARD_SQL);

        try (Connection connection = getConnection();//connect database guna method getConnection 
                 PreparedStatement ps = connection.prepareStatement(INSERT_GUARD_SQL);) { //insert data SQL
            ps.setString(1, newGuard.getGuardID());
            ps.setString(2, newGuard.getGuardName());
            ps.setInt(3, newGuard.getGuardAge());
            ps.setString(4, newGuard.getGuardGender());
            ps.setString(5, newGuard.getGuardPhoneNo());
            ps.setString(6, newGuard.getGuardEmail());
            ps.setString(7, newGuard.getNationality());
            ps.setString(8, newGuard.getGuardRace());
            ps.setDouble(9, newGuard.getGuardSalary());
            ps.setString(10, newGuard.getGuardDOB());
            ps.setString(11, newGuard.getBranchID());
            ps.setString(12, newGuard.getSiteID());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
            e.printStackTrace();
        }
    }

    //select all staff
    public List<guard> selectAllGuard() {

        List<guard> guards = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GUARD);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String guardID = rs.getString("guardID");
                String guardName = rs.getString("guardName");
                int guardAge = rs.getInt("guardAge");
                String guardGender = rs.getString("guardGender");
                String guardPhoneNo = rs.getString("guardPhoneNo");
                String guardEmail = rs.getString("guardEmail");
                String nationality = rs.getString("nationality");
                String guardRace = rs.getString("guardRace");
                double guardSalary = rs.getDouble("guardSalary");
                String guardDOB = rs.getString("guardDOB");
                String branchID = rs.getString("branchID");
                String siteID = rs.getString("siteID");

                System.out.println(guardID);

                guards.add(new guard(guardID, guardName, guardAge, guardGender, guardPhoneNo, guardEmail, nationality, guardRace, guardSalary, guardDOB, branchID, siteID));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return guards;
    }

    //guard delete
    public boolean deleteGuard(String guardID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_GUARD_SQL);) {
            statement.setString(1, guardID);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    //select guard by id
    public guard selectGuard(String guardID) {
        guard guards = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GUARD_BY_ID);) {
            preparedStatement.setString(1, guardID);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String guardName = rs.getString("guardName");
                int guardAge = rs.getInt("guardAge");
                String guardGender = rs.getString("guardGender");
                String guardPhoneNo = rs.getString("guardPhoneNo");
                String guardEmail = rs.getString("guardEmail");
                String nationality = rs.getString("nationality");
                String guardRace = rs.getString("guardRace");
                double guardSalary = rs.getDouble("guardSalary");
                String guardDOB = rs.getString("guardDOB");
                String branchID = rs.getString("branchID");
                String siteID = rs.getString("siteID");

                System.out.println(guardID);

                guards = new guard(guardID, guardName, guardAge, guardGender, guardPhoneNo, guardEmail, nationality, guardRace, guardSalary, guardDOB, branchID, siteID);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return guards;
    }
    
    //GUARD UPDATE
        public boolean updateGuard(guard updateGuard) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_GUARD_SQL);) {
            ps.setString(1, updateGuard.getGuardName());
            ps.setInt(2, updateGuard.getGuardAge());
            ps.setString(3, updateGuard.getGuardGender());
            ps.setString(4, updateGuard.getGuardPhoneNo());
            ps.setString(5, updateGuard.getGuardEmail());
            ps.setString(6, updateGuard.getNationality());
            ps.setString(7, updateGuard.getGuardRace());
            ps.setDouble(8, updateGuard.getGuardSalary());
            ps.setString(9, updateGuard.getGuardDOB());
            ps.setString(10, updateGuard.getBranchID());
            ps.setString(11, updateGuard.getSiteID());
            ps.setString(12, updateGuard.getGuardID());

            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
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
