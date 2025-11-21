package main.java.br.icev.vendas;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Pedido {
    public enum Status { PAGO }

    private final Map<String, Integer> itensPorCodigo;
    private final BigDecimal totalPago;
    private final String codigoAutorizacao;
    private final Status status;

    public Pedido(Map<String, Integer> itensPorCodigo, BigDecimal totalPago,
                  String codigoAutorizacao, Status status) {
        this.itensPorCodigo = new HashMap<>(itensPorCodigo);
        this.totalPago = totalPago;
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
    }

    public BigDecimal getTotalPago() { throw new UnsupportedOperationException("TODO"); }
    public String getCodigoAutorizacao() { throw new UnsupportedOperationException("TODO"); }
    public Status getStatus() { throw new UnsupportedOperationException("TODO"); }
    public int getQuantidadeItem(String codigo) { throw new UnsupportedOperationException("TODO"); }
}
