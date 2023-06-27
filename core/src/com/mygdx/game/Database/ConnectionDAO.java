package com.mygdx.game.Database;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionDAO {
    Connection connection = null;
    public void connect() {

        // Connnecting to remote database
        Dotenv dotenv = Dotenv.configure().load();
    	String dbHost = dotenv.get("DB_HOST");
    	String dbUsername = dotenv.get("DB_USERNAME");
    	String dbPassword = dotenv.get("DB_PASSWORD");
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbHost, dbUsername, dbPassword);

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