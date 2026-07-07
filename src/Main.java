import enums.Rank;
import exception.ContaNaoEncontradaException;
import model.Cliente;
import model.Conta;
import model.ContaLol;
import model.ContaRocketLeague;
import service.Estoque;
import service.VendaService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main (String[] args){
        Scanner iUser = new Scanner(System.in);
        Estoque estoque = new Estoque();

        VendaService vendaService = new VendaService();

        while (true){

            try {


                System.out.print("================== Game Market ==================\n" +
                        "\n1 - Cadastrar Conta" +
                        "\n2 - Listar Contas" +
                        "\n3 - Procurar Contas" +
                        "\n4 - Vender Contas" +
                        "\n5 - Estatisticas " +
                        "\n0 - Sair" +
                        "\n\nInsira a opção: ");
                int opc = iUser.nextInt();

                if (opc == 0) {
                    System.out.println("Saindo do progama...");
                    break;
                }

                switch (opc) {
                    case 1 -> {
                        System.out.print("================== Cadastrar Conta ==================" +
                                "\n1 - Conta Rocket League" +
                                "\n2 - Conta League Of Legends" +
                                "\nEscolha a Opção: ");

                        int opcCadastrarConta = iUser.nextInt();

                        System.out.print("Insira o Id da Conta: ");
                        int idDigitado = iUser.nextInt();
                        iUser.nextLine();

                        System.out.print("Insira o Nome da Conta: ");
                        String nomeDigitado = iUser.nextLine();

                        System.out.print("Insira o Preço da Conta: R$");
                        double precoDigitado = iUser.nextDouble();
                        iUser.nextLine();

                        System.out.println("Selecione o Rank da Conta:");
                        Rank[] todosOsRanks = Rank.values();
                        for (int i = 0; i < todosOsRanks.length; i++) {
                            System.out.println((i + 1) + " - " + todosOsRanks[i]);
                        }

                        System.out.print("Escolha a Opção: ");
                        int opcRank = iUser.nextInt();
                        iUser.nextLine();

                        Rank rankEscolhido = todosOsRanks[opcRank - 1];


                        if (opcCadastrarConta == 1) {
                            Conta RocketLeague = new ContaRocketLeague(idDigitado, nomeDigitado, precoDigitado, rankEscolhido);
                            estoque.cadastrarConta(RocketLeague);
                        } else if (opcCadastrarConta == 2) {
                            Conta LeagueOfLegends = new ContaLol(idDigitado, nomeDigitado, precoDigitado, rankEscolhido);
                            estoque.cadastrarConta(LeagueOfLegends);
                        }

                    }
                    case 2 -> {
                        estoque.listarContas();

                    }
                    case 3 -> {
                        System.out.print("================== Procurar Conta ==================" +
                                "\nInsira o Id da conta a ser procurada: ");
                        int idBusca = iUser.nextInt();
                        iUser.nextLine();

                        try {
                            estoque.procurarContaPorID(idBusca);
                        } catch (ContaNaoEncontradaException e) {
                            System.out.println("Erro no sistema: " + e.getMessage());
                        }

                    }
                    case 4 -> {
                        System.out.println("======== Vender Conta ========" +
                                "\nInsira o id da conta a ser vendida: ");

                        int idVenda = iUser.nextInt();
                        iUser.nextLine();

                        System.out.print("Insira o Nome do Cliente comprador: ");
                        String nomeCliente = iUser.nextLine();

                        Cliente cliente = new Cliente(nomeCliente);

                        try {
                            vendaService.realizarVenda(idVenda, cliente, estoque);
                        } catch (exception.ContaNaoEncontradaException e) {
                            System.out.println("\nERRO DE OPERAÇÃO: " + e.getMessage());
                            System.out.println("Por favor, verifique o ID digitado e tente novamente.\n");
                        }

                    }
                    case 5 -> {
                        System.out.println("-=-=-=-=-= Estatisticas -=-=-=-=-=\n");
                        estoque.mostrarEstatisticas();
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }

            catch (InputMismatchException e){
                System.out.println("ERRO: O usuario deve digitar apenas numeros!");
                iUser.nextLine();
            }

            catch (ContaNaoEncontradaException e){
                System.out.println("\nAVISO: " + e.getMessage());
            }

            catch (Exception e) {
                System.out.println("\nOcorreu um erro inesperado " + e.getMessage());
            }

            System.out.println();
        }
        iUser.close();
    }
}