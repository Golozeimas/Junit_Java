package main.java.br.icev.vendas;


import main.java.br.icev.vendas.excecoes.QuantidadeInvalidaException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Carrinho {

    private Map<String, Produto> produtos;
    private Map<String, Integer> quantidades;

    public Carrinho() {
        this.produtos = new HashMap<>();
        this.quantidades = new HashMap<>();
    }

    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }

        String codigo = produto.getCodigo();
        produtos.put(codigo, produto);
        quantidades.put(codigo, quantidades.getOrDefault(codigo, 0) + quantidade);
    }

    // essa classe pega o subtotal do preços do produtos
    public BigDecimal getSubtotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (String codigo : produtos.keySet()) {
            Produto produto = produtos.get(codigo);
            int quantidade = quantidades.get(codigo);
            // uso de funções do BigDecimaal
            BigDecimal subtotalItem = produto.getPrecoUnitario()
                    .multiply(new BigDecimal(quantidade))
                    .setScale(2, RoundingMode.HALF_UP);
            total = total.add(subtotalItem);
        }
        return total;
    }

    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        throw new UnsupportedOperationException("TODO");
    }

    public int getTotalItens() {
        throw new UnsupportedOperationException("TODO");
    }
}
