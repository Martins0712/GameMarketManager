package model;

import enums.Rank;

public class ContaLol extends Conta {

    public ContaLol(int id, String nome, double preco, Rank rank) {
        super(id, nome, preco, rank);
    }

    @Override
    public void vender() {
        if(isVendida()) {
            System.out.println("Aviso: Esta conta já foi vendida anteriormente!");
            return;
        }
        setVendida(true);
        }

    @Override
    public void mostrarInformacoes() {
        System.out.println("----------------------------------------");
        System.out.println("[League Of Legends] ID: " + getId());
        System.out.println("Dono/Nome: " + getNome());
        System.out.println("Preço: " + getPreco());
        System.out.println("Rank: " + getRank());
        System.out.println("Status: " + (isVendida() ? "Vendida" : "Disponivel"));
        System.out.println("----------------------------------------");
    }
}
