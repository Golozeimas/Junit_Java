package main.java.br.icev.vendas;



import main.java.br.icev.vendas.excecoes.QuantidadeInvalidaException;
import main.java.br.icev.vendas.excecoes.SemEstoqueException;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<String, Integer> produtos;

    public Estoque() {
        this.produtos = new HashMap<>();
    }

    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        produtos.put(codigo, produtos.getOrDefault(codigo, 0) + quantidade);
    }

    public int getDisponivel(String codigo) {
        // esse metodo vai retornar um valor associado a uma chave específica ou o padrão, que seria 0
        return produtos.getOrDefault(codigo, 0);
    }

    public void reservar(String codigo, int quantidade) throws SemEstoqueException, QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        int disponivel = getDisponivel(codigo);
        if (disponivel < quantidade) {
            throw new SemEstoqueException("Estoque insuficiente");
        }
        produtos.put(codigo, disponivel - quantidade);
    }
}
