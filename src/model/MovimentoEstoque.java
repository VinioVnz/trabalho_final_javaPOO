package model;

import java.time.LocalDate;

/**
 * Representa um movimento de entrada ou saída de estoque.
 * <p>
 * Esta classe abstrata fornece os atributos e comportamentos comuns a todos
 * os movimentos registrados no sistema, incluindo data, produto e quantidade.
 * Implementa {@link Comparable} para permitir ordenação por data.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public abstract class MovimentoEstoque implements Comparable<MovimentoEstoque>{
    private LocalDate data;
    private Produto produto;
    private int quantidade;
    
    /**
     * Constrói um movimento de estoque com os parâmetros fornecidos.
     *
     * @param data      data do movimento
     * @param produto   produto afetado pelo movimento
     * @param quantidade quantidade movimentada (positivo)
     */
    public MovimentoEstoque(LocalDate data, Produto produto, int quantidade) {
        this.data = data;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * Obtém a data do movimento.
     *
     * @return data do movimento
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Define a data do movimento.
     *
     * @param data nova data do movimento
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Obtém o produto associado ao movimento.
     *
     * @return produto afetado
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Define o produto associado ao movimento.
     *
     * @param produto produto a ser associado
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * Obtém a quantidade movimentada.
     *
     * @return quantidade movimentada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade movimentada.
     *
     * @param quantidade nova quantidade movimentada
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(MovimentoEstoque outro) {
        return this.data.compareTo(outro.getData());
    }

}
