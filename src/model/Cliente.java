package model;

public class Cliente {

    private String nome;

    public Cliente(String nomeCliente) {
        this.nome = nomeCliente;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                '}';
    }
}

