package com.historicobiblioteca.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculoMultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int emprestimoId = Integer.parseInt(request.getParameter("emprestimoId"));
        double valorMulta = 0.0;

        // Conexão ao banco de dados e cálculo da multa
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_biblioteca", "root", "senha")) {
            String sql = "SELECT valor_multa FROM Multas WHERE emprestimo_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, emprestimoId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    valorMulta = rs.getDouble("valor_multa");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retorna o valor da multa
        response.setContentType("application/json");
        response.getWriter().write("{\"valorMulta\": " + valorMulta + "}");
    }
}
