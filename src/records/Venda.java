package records;

import model.Cliente;
import model.Conta;

public record Venda(Cliente cliente, Conta conta){
}

