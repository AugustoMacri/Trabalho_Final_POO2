package com.mygdx.game;

import java.sql.Connection;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScriptsDAO {
    public static void createTable(Connection conn) throws SQLException {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS score (id SERIAL PRIMARY KEY, Score int)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    public static void insertTable(Connection conn, Character character) throws SQLException{
        String sql = "INSERT INTO score (Score) VALUES ('" + character.getSCORE() + "')";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
    }
}

