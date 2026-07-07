package service;

import exception.ContaNaoEncontradaException;
import model.Cliente;
import model.Conta;
import records.Venda;

public class VendaService {

    private final java.util.List<Venda> historicoVendas = new java.util.ArrayList<>();

    public void realizarVenda(int idConta, Cliente cliente, Estoque estoque){
        Conta conta = estoque.getMapaContas().get(idConta);

        if (conta == null){
            throw new ContaNaoEncontradaException("Não é possivel vender: Conta não encontrada ");
        }

        conta.vender();

        Venda novaVenda = new Venda(cliente, conta);
        System.out.println("Venda registrada com sucesso para "+ cliente.getNome());
        historicoVendas.add(novaVenda);

    }
    public java.util.List<Venda> getHistoricoVendas() {
        return this.historicoVendas;
}
}
