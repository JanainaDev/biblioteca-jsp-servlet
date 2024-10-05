package com.historicobiblioteca.util; // Adapte este pacote conforme a estrutura do seu projeto

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static Connection getConnection() throws SQLException {
    	
    	// Método para obter uma conexão com o banco de dados  
    	try {
    		// Registrar o driver MySQL
    		Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver MySQL não encontrado", e);
            }
    	// Substitua os valores abaixo com os seus dados do banco de dados
        String url = "jdbc:mysql://localhost:3306/biblioteca"; // URL do banco
        String user = "root"; // Usuário do banco
        String password = "root"; // Senha do banco
        // Conectando ao banco de dados
        return DriverManager.getConnection(url, user, password);
    }
}
