package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fornece utilitários para cálculo do saldo financeiro do estoque.
 * <p>
 * A classe permite calcular o valor total do estoque e obter um mapa com as
 * quantidades por produto. O cálculo atual considera os produtos retornados
 * pelo {@link ControleEstoque}.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class SaldoCalculator {

    /**
     * Representa o resultado do cálculo de saldo, incluindo o valor total
     * e as quantidades por produto.
     */
    public static class ResultadoSaldo {
        private double valorTotal;
        private Map<Produto, Integer> quantidadesPorProduto;

        /**
         * Construtor do resultado de saldo.
         *
         * @param valorTotal           soma monetária do estoque
         * @param quantidadesPorProduto mapa com quantidades por produto
         */
        public ResultadoSaldo(double valorTotal, Map<Produto, Integer> quantidadesPorProduto) {
            this.valorTotal = valorTotal;
            this.quantidadesPorProduto = quantidadesPorProduto;
        }

        /**
         * Obtém o valor total calculado.
         *
         * @return valor total do estoque em reais
         */
        public double getValorTotal() {
            return valorTotal;
        }

        /**
         * Obtém o mapa de quantidades por produto.
         *
         * @return mapa onde a chave é o produto e o valor a quantidade disponível
         */
        public Map<Produto, Integer> getQuantidadesPorProduto() {
            return quantidadesPorProduto;
        }
    }
    
    /**
     * Calcula o saldo do estoque a partir da lista de movimentos.
     * <p>
     * Observação: a implementação atual considera o inventário retornado pelo
     * {@link ControleEstoque} e ignora o parâmetro de movimentos fornecido.
     * </p>
     *
     * @param movimentos lista de movimentos de estoque (não utilizada na implementação atual)
     * @return instância de {@link ResultadoSaldo} com o total e as quantidades
     */
    public ResultadoSaldo calcularSaldo(List<MovimentoEstoque> movimentos) {


        ControleEstoque controle = new ControleEstoque();
        List<Produto> produtos = controle.getTodosProdutos();

        Map<Produto, Integer> quantidades = new HashMap<>();
        double total = 0.0;

        for (Produto p : produtos) {
            quantidades.put(p, p.getQuantidade());
            total += p.getQuantidade() * p.getPrecoUnitario();
        }

        return new ResultadoSaldo(total, quantidades);
    }
}
