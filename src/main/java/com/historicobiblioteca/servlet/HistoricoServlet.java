package com.historicobiblioteca.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import com.historicobiblioteca.model.Emprestimo;
import com.historicobiblioteca.model.Reserva;
import com.historicobiblioteca.util.MySQLConnection;

public class HistoricoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));  // Pega o ID do usuário a partir da requisição
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        ArrayList<Reserva> reservas = new ArrayList<>();
        
        try {
            // Conectando ao banco de dados
            con = MySQLConnection.getConnection();

            // Busca os empréstimos do usuário
            String sqlEmprestimos = "SELECT * FROM Emprestimos WHERE usuario_id = ?";
            pst = con.prepareStatement(sqlEmprestimos);
            pst.setInt(1, usuarioId);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                    rs.getInt("id"),
                    rs.getInt("livro_id"),
                    rs.getDate("data_emprestimo"),
                    rs.getDate("data_devolucao_prevista"),
                    rs.getDate("data_devolucao"),
                    rs.getString("status_devolucao")
                );
                emprestimos.add(emprestimo);
            }

            // Fechar o ResultSet dos empréstimos
            rs.close();
            pst.close();

            // Busca as reservas do usuário
            String sqlReservas = "SELECT * FROM Reservas WHERE usuario_id = ?";
            pst = con.prepareStatement(sqlReservas);
            pst.setInt(1, usuarioId);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Reserva reserva = new Reserva(
                    rs.getInt("id"),
                    rs.getInt("livro_id"),
                    rs.getDate("data_reserva"),
                    rs.getString("status_reserva")
                );
                reservas.add(reserva);
            }

            // Envia os dados para a JSP
            request.setAttribute("emprestimos", emprestimos);
            request.setAttribute("reservas", reservas);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/historico.jsp"); // Ajuste o caminho conforme sua estrutura
            rd.forward(request, response);

        } catch (SQLException e) {
            // Tratamento de erro para SQLException
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace(); // Mostra o stack trace para ajudar na depuração
        } catch (Exception e) {
            // Tratamento genérico para outras exceções
            e.printStackTrace();
        } finally {
            // Fechar recursos na ordem inversa
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pst != null) pst.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
