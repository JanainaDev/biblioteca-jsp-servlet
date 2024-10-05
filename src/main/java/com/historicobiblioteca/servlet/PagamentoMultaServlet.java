package com.historicobiblioteca.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagamentoMultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redireciona para a página JSP de pagamento de multa
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/pagamentoMulta.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int emprestimoId = -1;
        double valorMulta = 0.0;
        String metodoPagamento = null;

        // Verificação de parâmetros
        try {
            // Captura os parâmetros do formulário
            if (request.getParameter("emprestimoId") == null || request.getParameter("valorMulta") == null) {
                throw new IllegalArgumentException("Parâmetros faltando.");
            }

            emprestimoId = Integer.parseInt(request.getParameter("emprestimoId"));
            valorMulta = Double.parseDouble(request.getParameter("valorMulta"));
            metodoPagamento = request.getParameter("metodoPagamento");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de empréstimo ou valor da multa inválidos.");
            return;
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        // Conexão ao banco de dados e processamento
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_biblioteca", "root", "senha")) {
            // Obtém o ID da multa
            int multaId = getMultaId(con, emprestimoId);

            if (multaId == -1) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Multa não encontrada para o empréstimo informado.");
                return;
            }

            // Insere o pagamento na tabela Pagamentos
            inserirPagamento(con, multaId, valorMulta);

            // Atualiza o status da multa para "Pago"
            atualizarStatusMulta(con, multaId);

            // Redireciona para a página de recibo
            request.setAttribute("multaId", multaId);
            request.setAttribute("valorPago", valorMulta);
            request.setAttribute("metodoPagamento", metodoPagamento);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/recibo.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");
            e.printStackTrace();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro inesperado.");
            e.printStackTrace();
        }
    }

    private int getMultaId(Connection con, int emprestimoId) throws SQLException {
        String sql = "SELECT id FROM Multas WHERE emprestimo_id = ? AND status_pagamento = 'Pendente'";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, emprestimoId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1; // Retorna -1 se não encontrar nenhuma multa pendente
    }

    private void inserirPagamento(Connection con, int multaId, double valorMulta) throws SQLException {
        String insertPagamento = "INSERT INTO Pagamentos (multa_id, data_pagamento, valor_pago) VALUES (?, NOW(), ?)";
        try (PreparedStatement pst = con.prepareStatement(insertPagamento)) {
            pst.setInt(1, multaId);
            pst.setDouble(2, valorMulta);
            pst.executeUpdate();
        }
    }

    private void atualizarStatusMulta(Connection con, int multaId) throws SQLException {
        String updateMulta = "UPDATE Multas SET status_pagamento = 'Pago', data_pagamento = NOW() WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(updateMulta)) {
            pst.setInt(1, multaId);
            pst.executeUpdate();
        }
    }
}
