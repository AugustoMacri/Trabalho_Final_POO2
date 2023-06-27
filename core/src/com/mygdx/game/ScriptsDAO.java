package com.mygdx.game;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public int topScore(Connection conn, Character character) throws SQLException {
    int resultSet = -1;
    try {
        String sql = "SELECT MAX(Score) AS MaxScore FROM score";
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);
        if (rs.next()) {
            resultSet = rs.getInt("MaxScore");
        }
        rs.close();
        return resultSet;
    } catch (SQLException e) {
        System.out.println("Ocorreu um erro: " + e.getMessage());
        return resultSet;
    }
}

}