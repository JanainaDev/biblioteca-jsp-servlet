package com.historicobiblioteca.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/biblioteca"; // Substitua 'seu_banco'
        String usuario = "root"; // Substitua pelo seu usuário
        String senha = "root"; // Substitua pela sua senha

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            if (conn != null) {
                System.out.println("Conexão com o MySQL estabelecida com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer a conexão: " + e.getMessage());
        }
    }
}
