package com.historicobiblioteca.model;

import java.sql.Date;

public class Reserva {
    private int id;
    private int livroId;
    private Date dataReserva;
    private String statusReserva;

    // Construtor
    public Reserva(int id, int livroId, Date dataReserva, String statusReserva) {
        this.id = id;
        this.livroId = livroId;
        this.dataReserva = dataReserva;
        this.statusReserva = statusReserva;
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

    public Date getDataReserva() {
        return dataReserva;
    }

    public String getStatusReserva() {
        return statusReserva;
    }
}
