<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.historicobiblioteca.model.Emprestimo" %>
<%@ page import="com.historicobiblioteca.model.Reserva" %>

<html>
<head>
    <title>Histórico de Empréstimos e Reservas</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            border: 1px solid black;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Histórico de Empréstimos</h1>
    <table>
        <tr>
            <th>Título</th>
            <th>Data de Empréstimo</th>
            <th>Data de Devolução Prevista</th>
            <th>Data de Devolução</th>
            <th>Status</th>
        </tr>
        <%
            @SuppressWarnings("unchecked")  // Suprimindo o aviso de tipo não verificado
            ArrayList<Emprestimo> emprestimos = (ArrayList<Emprestimo>) request.getAttribute("emprestimos");
            if (emprestimos != null) {
                for (Emprestimo emp : emprestimos) {
        %>
        <tr>
            <td><%= emp.getTituloLivro() %></td>
            <td><%= emp.getDataEmprestimo() %></td>
            <td><%= emp.getDataDevolucaoPrevista() %></td>
            <td><%= emp.getDataDevolucao() != null ? emp.getDataDevolucao() : "Ainda não devolvido" %></td>
            <td><%= emp.getStatusDevolucao() %></td>
        </tr>
        <% 
                } 
            } else { 
        %>
        <tr>
            <td colspan="5">Nenhum empréstimo encontrado.</td>
        </tr>
        <% 
            } 
        %>
    </table>

    <h1>Histórico de Reservas</h1>
    <table>
        <tr>
            <th>Título</th>
            <th>Data da Reserva</th>
            <th>Status</th>
        </tr>
        <%
            @SuppressWarnings("unchecked")  // Suprimindo o aviso de tipo não verificado
            ArrayList<Reserva> reservas = (ArrayList<Reserva>) request.getAttribute("reservas");
            if (reservas != null) {
                for (Reserva res : reservas) {
        %>
        <tr>
            <td><%= res.getTituloLivro() %></td>
            <td><%= res.getDataReserva() %></td>
            <td><%= res.getStatusReserva() %></td>
        </tr>
        <%
                } 
            } else { 
        %>
        <tr>
            <td colspan="3">Nenhuma reserva encontrada.</td>
        </tr>
        <%
            } 
        %>
    </table>
</body>
</html>
