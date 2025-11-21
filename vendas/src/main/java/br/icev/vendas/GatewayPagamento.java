package main.java.br.icev.vendas;
import main.java.br.icev.vendas.excecoes.ErroPagamentoException;

import java.math.BigDecimal;

public interface GatewayPagamento {
    String cobrar(BigDecimal valor) throws ErroPagamentoException;
}
