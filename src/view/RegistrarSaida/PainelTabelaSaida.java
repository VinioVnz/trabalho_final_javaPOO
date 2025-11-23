package view.RegistrarSaida;

import model.SaidaEstoque;
import model.MovimentoEstoque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Painel que exibe as saídas de estoque em formato de tabela.
 *
 * Filtra a lista de movimentos e apresenta apenas as saídas, exibindo
 * data, produto e quantidade de cada operação.
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class PainelTabelaSaida extends JPanel {

    private JTable tabela;
    private DefaultTableModel modelo;

    /**
     * Inicializa a tabela de saídas.
     */
    public PainelTabelaSaida() {
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Data", "Produto", "Quantidade"}, 0);
        tabela = new JTable(modelo);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    /**
     * Carrega as saídas a partir da lista de movimentos e preenche a tabela.
     *
     * @param movimentos lista de movimentos do estoque
     */
    public void carregarSaidas(List<MovimentoEstoque> movimentos) {
        modelo.setRowCount(0);

        for (MovimentoEstoque m : movimentos) {
            if (m instanceof SaidaEstoque saida) {
                modelo.addRow(new Object[]{
                        saida.getData(),
                        saida.getProduto().getNome(),
                        saida.getQuantidade()
                });
            }
        }
    }
}
