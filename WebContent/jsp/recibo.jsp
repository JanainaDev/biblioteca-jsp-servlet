<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recibo de Pagamento de Multa</title>
</head>
<body>
    <h1>Recibo de Pagamento</h1>
    <p><strong>Multa ID:</strong> <%= request.getAttribute("multaId") %></p>
    <p><strong>Valor Pago:</strong> R$ <%= request.getAttribute("valorPago") %></p>
    <p><strong>Método de Pagamento:</strong> <%= request.getAttribute("metodoPagamento") %></p>
    <p><strong>Data do Pagamento:</strong> <%= new java.util.Date() %></p>
    
    <h2>Obrigado pelo seu pagamento!</h2>
</body>
</html>
