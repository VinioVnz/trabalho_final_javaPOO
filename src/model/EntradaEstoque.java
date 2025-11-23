package model;

import java.time.LocalDate;

/**
 * Representa uma entrada de mercadoria no estoque.
 * <p>
 * Extensão de {@link MovimentoEstoque} que inclui o valor unitário
 * associado à operação de entrada.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class EntradaEstoque extends MovimentoEstoque{
    private double valorUnitario;

    /**
     * Constrói uma entrada de estoque com os dados fornecidos.
     *
     * @param data         data da entrada
     * @param produto      produto que entrou
     * @param quantidade   quantidade entrada
     * @param valorUnitario preço unitário da entrada
     */
    public EntradaEstoque(LocalDate data, Produto produto, int quantidade, double valorUnitario) {
        super(data, produto, quantidade);
        this.valorUnitario = valorUnitario;
    }

    /**
     * Obtém o valor unitário registrado para a entrada.
     *
     * @return valor unitário em reais
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Define o valor unitário para a entrada.
     *
     * @param valorUnitario novo valor unitário em reais
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}
