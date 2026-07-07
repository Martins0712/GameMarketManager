package model;

import enums.Rank;
import interfaces.Vendavel;

public abstract class Conta implements Vendavel {
    private int id;
    private String nome;
    private double preco;
    private Rank rank;
    private boolean vendida;

    public Conta() {

    }

    public abstract void vender();
    public abstract void mostrarInformacoes();

    public Conta(int id, String nome, double preco, Rank rank) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.rank = rank;
        this.vendida = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public boolean getVendida() {
        return vendida;
    }

    public boolean isVendida() {
        return this.vendida;
    }

    public void setVendida(boolean vendida) {
        this.vendida = vendida;
    }
}
