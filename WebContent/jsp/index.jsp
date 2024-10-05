<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sistema de Biblioteca</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('/HistoricoBiblioteca/images/biblioteca.jpg'); /* Caminho relativo */
            background-size: cover; /* Cobre todo o fundo */
            background-repeat: no-repeat; /* Não repete a imagem */
            background-position: center; /* Centraliza a imagem */
            height: 100vh; /* Garante que o corpo ocupa toda a altura da viewport */
            margin: 0; /* Remove margens padrão */
            padding: 0; /* Remove preenchimento padrão */
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
            background: rgba(255, 255, 255, 0.8); /* Fundo semi-transparente para contraste */
        }
        h1 {
            text-align: center;
        }
        .menu {
            text-align: center;
            margin: 20px 0;
        }
        .menu a {
            margin: 0 15px;
            text-decoration: none;
            color: #007BFF;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bem-vindo ao Sistema de Biblioteca</h1>
        <div class="menu">
            <a href="/HistoricoBiblioteca/historico?usuarioId=1">Ver Histórico</a>
            <a href="/HistoricoBiblioteca/pagarMulta">Pagar Multa</a>
        </div>
    </div>
</body>
</html>
