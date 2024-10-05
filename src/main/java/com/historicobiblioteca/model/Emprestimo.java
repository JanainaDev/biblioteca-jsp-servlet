package com.historicobiblioteca.model;

import java.sql.Date;

public class Emprestimo {
    private int id;
    private int livroId;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucao;
    private String statusDevolucao;

    // Construtor
    public Emprestimo(int id, int livroId, Date dataEmprestimo, Date dataDevolucaoPrevista, Date dataDevolucao, String statusDevolucao) {
        this.id = id;
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucao = dataDevolucao;
        this.statusDevolucao = statusDevolucao;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getLivroId() {
        return livroId;
    }

    public String getTituloLivro() {
        // Supondo que você tenha um método para obter o título do livro.
        // Você precisará implementar a lógica para recuperar o título se necessário.
        return "Título do Livro"; // Substitua por uma lógica real.
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public String getStatusDevolucao() {
        return statusDevolucao;
    }
}
