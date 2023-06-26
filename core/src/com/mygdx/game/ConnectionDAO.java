package com.mygdx.game;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class ConnectionDAO {
    Connection connection = null;
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://silly.db.elephantsql.com:5432/iqhizwhy", "iqhizwhy", "iYd_982gm4jk3La-lK7yBowVGupF0tN7");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver do BD nao localizado");
        } catch (SQLException e){
            System.out.println("Erro ao acessar o banco " + e.getMessage());
        } finally {
            if(connection != null){
                System.out.println("Conexao feita com sucesso");
            }
        }
    }


    public Connection getConnection() {
        return connection;
    }
}