package view.CadastroProduto;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import model.Produto;

/**
 * Painel que exibe a tabela de produtos cadastrados e fornece ação de remoção.
 *
 * A tabela é atualizável a partir de uma lista de {@link Produto} e expõe
 * métodos para obter a linha selecionada e definir a ação do botão remover.
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class PainelTabelaProdutos extends JPanel{
    private JTable tabela;
	private DefaultTableModel modeloTabela;
	private JButton botaoRemover;

    /**
     * Construtor que inicializa a tabela e o botão de remoção.
     */
    public PainelTabelaProdutos() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createTitledBorder("Produtos Cadastrados"));

        String[] colunas = {"Id","Nome","Preco Unitário","Quantidade","Categoria"};
        modeloTabela = new DefaultTableModel(colunas,0);

        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane painelRolagem = new JScrollPane(tabela);

        botaoRemover = new JButton("Remover Selecionado");

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        painelBotoes.add(botaoRemover);

        add(painelRolagem, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

    }

    /**
     * Atualiza o conteúdo da tabela com a lista de produtos fornecida.
     *
     * @param produtos lista de produtos a ser exibida
     */
    public void atualizarTabela(List<Produto> produtos ){
        modeloTabela.setRowCount(0);

        for(Produto produto : produtos){
            Object[] linha = {produto.getCodigo() ,produto.getNome(), produto.getPrecoUnitario(), produto.getQuantidade(), produto.getNomeCategoria()};
            modeloTabela.addRow(linha);
        }
    }   

    /**
     * Retorna o índice da linha atualmente selecionada na tabela.
     *
     * @return índice da linha selecionada ou -1 se nenhuma linha estiver selecionada
     */
    public int obterLinhaSelecionada() {
		return tabela.getSelectedRow();
	}

	/**
	 * Define a ação a ser executada quando o botão remover for acionado.
	 *
	 * @param acao listener de ação a ser associado ao botão remover
	 */
	public void definirAcaoBotaoRemover(ActionListener acao) {
		botaoRemover.addActionListener(acao);
	}
}
