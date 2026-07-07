package service;

import exception.ContaNaoEncontradaException;
import model.Conta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Estoque {


    private ArrayList<Conta> contas = new ArrayList<>();

    private HashMap<Integer, Conta> mapaContas = new HashMap<>();
    private HashSet<Integer> ids = new HashSet<>();

    public HashMap<Integer, Conta> getMapaContas() {
        return mapaContas;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void cadastrarConta(Conta conta) {

        if (ids.contains(conta.getId())) {
            System.out.println("== Esse ID já existe. Cadastre uma conta com id disponivel ==");
            return;
        }

        contas.add(conta);
        mapaContas.put(conta.getId(), conta);
        ids.add(conta.getId());

        System.out.println("== Conta Cadastrada com Sucesso! ==\n");
    }

    public void listarContas() {

        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada no estoque.");
            return;
        }
        System.out.println("==== Lista de Contas no Estoque ====");
        contas.forEach(Conta::mostrarInformacoes);
    }

    public void procurarContaPorID(int id) {

        Conta contaEncontrada = mapaContas.get(id);

        if (contaEncontrada == null) {
            throw new ContaNaoEncontradaException("Nenhuma conta encontrada com o ID: " + id);
        }
        System.out.println("\n=== Conta Encontrada ===");
        contaEncontrada.mostrarInformacoes();
    }

    public void mostrarEstatisticas() {

        service.VendaService vService = new VendaService();
        java.util.List<model.Conta> todasAsContas = contas;
        java.util.List<records.Venda> vendasRealizadas = vService.getHistoricoVendas();

        System.out.println("========= Total de contas ========= \nTotal de contas cadastradas: " + todasAsContas.size());

        System.out.println("==== Total de contas vendidas ==== \nTotal de contas vendidas:    " + vendasRealizadas.size());

        System.out.println("=== Total de contas disponiveis === \nTotal de contas disponíveis: " + (todasAsContas.size() - vendasRealizadas.size()));

        double valorArrecadado = vendasRealizadas.stream()
                .mapToDouble(v -> v.conta().getPreco())
                .sum();

        System.out.printf("==== Total de valor arrecadado ==== \nTotal de valor arrecadado:   R$ %.2f\\n", valorArrecadado);

        if (todasAsContas.isEmpty()) {

            System.out.println("_______________________________________________" +
                                "\n|                                             |" +
                                "\n|Nenhuma conta valida para liberar estatistica|" +
                                "\n|                                             |" +
                                "\n|_____________________________________________|");
        } else {
            todasAsContas.stream()
                    .max(java.util.Comparator.comparingDouble(model.Conta::getPreco))
                    .ifPresent(c -> System.out.println("========= Conta mais cara =========" +
                            "\nID: " + c.getId() + " | Nome - " + c.getNome() + " | Preço - (R$ " + c.getPreco() + ")"));

            todasAsContas.stream()
                    .min(java.util.Comparator.comparingDouble(model.Conta::getPreco))
                    .ifPresent(c -> System.out.println("======== Conta mais barata ========" +
                            "\nID: " + c.getId() + " | Nome - " + c.getNome() + " | Preço - (R$ " + c.getPreco() + ")"));

            todasAsContas.stream()
                    .mapToDouble(model.Conta::getPreco)
                    .average()
                    .ifPresent(media -> System.out.printf("=========== Preço Medio ===========\nR$ %.2f\n", media));

        }
    }
}
