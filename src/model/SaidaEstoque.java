package model;

import java.time.LocalDate;

/**
 * Representa uma saída de mercadoria do estoque.
 * <p>
 * Extensão de {@link MovimentoEstoque} utilizada para registrar operações
 * que diminuem a quantidade disponível de um produto.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class SaidaEstoque extends MovimentoEstoque{

    /**
     * Constrói uma saída de estoque com os dados fornecidos.
     *
     * @param data      data da saída
     * @param produto   produto que saiu
     * @param quantidade quantidade removida
     */
    public SaidaEstoque(LocalDate data, Produto produto, int quantidade) {
        super(data, produto, quantidade);
    }

}
