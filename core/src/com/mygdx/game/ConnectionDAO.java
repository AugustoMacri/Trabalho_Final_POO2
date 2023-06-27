package com.mygdx.game;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class ConnectionDAO {
    Connection connection = null;
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ranking", "root", "1234");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver do BD não localizado");
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
