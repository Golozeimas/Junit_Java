# Sistema de Vendas em Java

Sistema completo de vendas desenvolvido em Java com testes unitários automatizados usando JUnit 5. O projeto demonstra a aplicação de conceitos de Programação Orientada a Objetos (POO), tratamento de exceções e desenvolvimento orientado a testes (TDD).

## 📋 Descrição

Este sistema implementa um fluxo completo de vendas online, incluindo:
- Gerenciamento de produtos com validação de preços
- Controle de estoque com reserva de itens
- Carrinho de compras com cálculo de subtotais e aplicação de descontos
- Processamento de pagamentos via gateway
- Criação de pedidos após confirmação de pagamento

## 🏗️ Arquitetura

### Estrutura de Pacotes

```
br.icev.vendas
├── Produto.java
├── Carrinho.java
├── Estoque.java
├── Pedido.java
├── UtilDinheiro.java
├── PoliticaDesconto.java (interface)
├── GatewayPagamento.java (interface)
└── excecoes
    ├── QuantidadeInvalidaException.java
    ├── SemEstoqueException.java
    └── ErroPagamentoException.java
```

## 🔧 Classes Principais

### Produto
Representa um produto com código, nome e preço unitário.

**Características:**
- Validação de preço (não aceita valores negativos ou nulos)
- Igualdade baseada no código do produto
- Implementação correta de `equals()` e `hashCode()`

### Carrinho
Gerencia itens adicionados pelo cliente antes da finalização da compra.

**Funcionalidades:**
- Adicionar produtos com quantidade
- Acumular quantidades de produtos com mesmo código
- Calcular subtotal com arredondamento correto
- Aplicar políticas de desconto
- Garantir que o total nunca seja negativo

### Estoque
Controla a disponibilidade de produtos.

**Funcionalidades:**
- Adicionar quantidade ao estoque
- Consultar disponibilidade por código
- Reservar itens (reduz o estoque disponível)
- Validação de quantidades inválidas
- Proteção contra reservas maiores que o estoque

### Pedido
Representa uma compra finalizada e paga.

**Atributos:**
- Itens comprados (código e quantidade)
- Total pago
- Código de autorização do pagamento
- Status do pedido

### UtilDinheiro
Classe utilitária para operações monetárias.

**Método:**
- `arredondar2()`: Arredonda valores para 2 casas decimais usando HALF_UP

## 🎯 Interfaces

### PoliticaDesconto
Interface funcional para aplicar diferentes estratégias de desconto.

```java
@FunctionalInterface
public interface PoliticaDesconto {
    BigDecimal aplicar(BigDecimal subtotal);
}
```

### GatewayPagamento
Interface para integração com gateways de pagamento.

```java
public interface GatewayPagamento {
    String cobrar(BigDecimal valor) throws ErroPagamentoException;
}
```

## ⚠️ Exceções

### QuantidadeInvalidaException
Lançada quando uma quantidade zero ou negativa é fornecida.

### SemEstoqueException
Lançada quando não há estoque suficiente para atender uma reserva.

### ErroPagamentoException
Lançada quando ocorre erro no processamento do pagamento.

## 🔄 Fluxo de Checkout

1. **Validação de Estoque**: Verifica se há estoque suficiente para todos os itens
2. **Cálculo do Total**: Aplica política de desconto ao subtotal do carrinho
3. **Processamento de Pagamento**: Cobra o valor via gateway de pagamento
4. **Reserva de Estoque**: Reduz o estoque disponível
5. **Criação do Pedido**: Gera pedido com status PAGO

**Importante:** O estoque só é reservado APÓS o pagamento ser aprovado, garantindo que falhas de pagamento não afetem a disponibilidade.

## ✅ Testes

O projeto possui cobertura completa de testes unitários:

### ProdutoTeste
- Criação de produto válido
- Validação de preço negativo e nulo
- Igualdade por código

### CarrinhoTeste
- Soma de itens com arredondamento
- Validação de quantidade inválida
- Acumulação de produtos com mesmo código
- Aplicação de descontos com proteção contra valores negativos

### EstoqueTeste
- Adição e consulta de estoque
- Validação de quantidades inválidas
- Reserva de itens com limite de estoque

### CheckoutTeste
- Checkout com sucesso (fluxo completo)
- Falha por estoque insuficiente
- Falha de pagamento sem alterar estoque

## 🚀 Como Executar

### Pré-requisitos
- Java 11 ou superior
- JUnit 5
- Maven ou Gradle (opcional)

### Executando os Testes

```bash
# Com Maven
mvn test

# Com Gradle
gradle test

# Direto pela IDE
Execute os arquivos *Teste.java como JUnit Test
```

## 💡 Conceitos Aplicados

- **Programação Orientada a Objetos**: Encapsulamento, herança, polimorfismo
- **SOLID**: Single Responsibility, Interface Segregation
- **Design Patterns**: Strategy (PoliticaDesconto), Factory Method
- **TDD**: Desenvolvimento orientado a testes
- **Clean Code**: Código limpo e legível
- **Tratamento de Exceções**: Exceções verificadas e não verificadas
- **Imutabilidade**: Uso de `final` e cópias defensivas
- **Precisão Monetária**: Uso correto de `BigDecimal` para valores monetários

## 📝 Regras de Negócio

1. Preços não podem ser negativos ou nulos
2. Quantidades devem ser sempre positivas (maior que zero)
3. Produtos são identificados unicamente por código
4. Arredondamento monetário usa HALF_UP (arredonda .5 para cima)
5. Descontos nunca podem resultar em total negativo
6. Estoque só é reservado após confirmação de pagamento
7. Não é possível reservar mais itens do que o disponível em estoque

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

## 📄 Licença

Este projeto é de código aberto

## ✨ Autor

João Matheus Ramos Araujo

Desenvolvido como projeto educacional para prática de conceitos de POO e testes automatizados.

---

**Nota**: Este projeto foi desenvolvido seguindo as especificações dos testes unitários fornecidos. Todos os testes devem passar com sucesso para garantir o correto funcionamento do sistema.
