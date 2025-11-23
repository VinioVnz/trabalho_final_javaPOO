package view.RegistrarEntrada;

import model.EntradaEstoque;
import model.MovimentoEstoque;
import model.ControleEstoque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Painel que exibe entradas de estoque em forma de tabela.
 *
 * O painel filtra os movimentos recebidos exibindo apenas as entradas e
 * apresenta data, produto, quantidade e valor unitário.
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class PainelTabelaEntrada extends JPanel {

    private JTable tabela;
    private DefaultTableModel modelo;

    /**
     * Construtor que inicializa a tabela de entradas.
     */
    public PainelTabelaEntrada() {
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Data", "Produto", "Quantidade", "Valor Unit."}, 0);
        tabela = new JTable(modelo);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    /**
     * Carrega os movimentos recebidos e preenche a tabela com as entradas.
     *
     * @param movimentos lista de movimentos do estoque
     */
    public void carregarEntradas(List<MovimentoEstoque> movimentos) {
        modelo.setRowCount(0);

        for (MovimentoEstoque m : movimentos) {
            if (m instanceof EntradaEstoque entrada) {
                modelo.addRow(new Object[]{
                        entrada.getData(),
                        entrada.getProduto().getNome(),
                        entrada.getQuantidade(),
                        entrada.getValorUnitario()
                });
            }
        }
    }
}
