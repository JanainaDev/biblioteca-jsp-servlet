<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagamento de Multa</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('/HistoricoBiblioteca/images/dinheiro.jpg'); /* Definindo a imagem de fundo */
            background-size: cover; /* Faz a imagem cobrir toda a tela */
            color: white; /* Mudando a cor do texto para branco para melhor contraste */
            text-align: center; /* Centraliza o texto */
        }
        .container {
            background-color: rgba(0, 0, 0, 0.7); /* Fundo escuro semi-transparente */
            padding: 20px;
            border-radius: 10px; /* Cantos arredondados */
            display: inline-block; /* Centraliza o container */
        }
        h1 {
            margin-bottom: 20px; /* Espaço abaixo do título */
        }
        input[type="text"], select {
            padding: 10px; /* Espaçamento interno */
            margin: 10px 0; /* Margem em cima e embaixo */
            width: 80%; /* Largura do campo */
            border-radius: 5px; /* Cantos arredondados nos campos */
            border: none; /* Sem borda */
        }
        input[type="submit"] {
            background-color: #007BFF; /* Cor de fundo do botão */
            color: white; /* Cor do texto do botão */
            border: none; /* Sem borda */
            border-radius: 5px; /* Cantos arredondados */
            padding: 10px 20px; /* Espaçamento interno */
            cursor: pointer; /* Muda o cursor para indicar que é clicável */
        }
        input[type="submit"]:hover {
            background-color: #0056b3; /* Cor do botão ao passar o mouse */
        }
    </style>
    
    <script>
        function calcularMulta() {
            var emprestimoId = document.getElementById("emprestimoId").value;

            if (emprestimoId) {
                fetch("http://localhost:8085/HistoricoBiblioteca/calcular-multa?emprestimoId=" + emprestimoId)
                    .then(response => response.json())
                    .then(data => {
                        document.getElementById("valorMulta").value = data.valorMulta;
                    })
                    .catch(error => {
                        console.error('Erro:', error);
                    });
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Pagamento de Multa</h1>

        <%
            // Inicialização de variáveis
            int emprestimoId = -1;
            Double valorMulta = 0.0; // Mudei para Double para permitir null

            try {
                // Pega os parâmetros enviados pelo servlet
                emprestimoId = Integer.parseInt(request.getParameter("emprestimoId"));
                valorMulta = (Double) request.getAttribute("valorMulta");
            } catch (NumberFormatException e) {
                out.println("<p style='color:red;'>Erro: ID de empréstimo inválido.</p>");
            } catch (Exception e) {
                out.println("<p style='color:red;'>Erro: Não foi possível obter o valor da multa.</p>");
            }
        %>

        <p>Empréstimo ID: <%= emprestimoId != -1 ? emprestimoId : "Inválido" %></p>
        <p>Valor da Multa: R$ <%= valorMulta != null && valorMulta > 0 ? valorMulta : 0 %></p>

         <form action="<%= request.getContextPath() %>/pagarMulta" method="post">
            <input type="text" id="emprestimoId" name="emprestimoId" placeholder="Digite o ID do Empréstimo" onblur="calcularMulta()" required>
            <input type="text" id="valorMulta" name="valorMulta" placeholder="Valor da multa" readonly>
            <select name="metodoPagamento">
                <option value="cartao">Cartão</option>
                <option value="dinheiro">Dinheiro</option>
            </select>
            <input type="submit" value="Pagar Multa">
        </form>
    </div>
</body>
</html>