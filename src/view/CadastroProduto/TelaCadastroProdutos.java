package view.CadastroProduto;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ControleEstoque;
import model.Produto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tela responsável pelo cadastro e listagem de produtos.
 * <p>
 * Consolida o formulário de cadastro e a tabela de produtos, além de
 * orquestrar as ações de persistência por meio de {@link ControleEstoque}.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class TelaCadastroProdutos extends JPanel {
    private ControleEstoque controle;
    private PainelFormularioProduto painelFormulario;
    private PainelTabelaProdutos painelTabela;

    /**
     * Construtor que inicializa componentes e associa as ações dos botões.
     *
     * @param controleEstoque instância de {@link ControleEstoque} utilizada para
     *                         persistência e recuperação de dados
     */
    public TelaCadastroProdutos(ControleEstoque controleEstoque) {

        this.controle = controleEstoque;

        setLayout(new BorderLayout(10, 10));

        painelFormulario = new PainelFormularioProduto();
        painelTabela = new PainelTabelaProdutos();

        add(painelFormulario, BorderLayout.NORTH);
        add(painelTabela, BorderLayout.CENTER);

        painelTabela.atualizarTabela(controle.getTodosProdutos());
        painelFormulario.definirAcaoBotaoCadastrar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto produto = painelFormulario.obterProdutoDoForulario(controle);
                if (produto != null) {
                    controle.adicionarProduto(produto);
                    painelTabela.atualizarTabela(controle.getTodosProdutos());
                    painelFormulario.limparFormulario();
                }
            }
        });
        painelTabela.atualizarTabela(controle.getTodosProdutos());
        painelTabela.definirAcaoBotaoRemover(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = painelTabela.obterLinhaSelecionada();
                if (linhaSelecionada >= 0) {
                    controle.excluirProduto(linhaSelecionada);
                    painelTabela.atualizarTabela(controle.getTodosProdutos());
                }
            }
        });
    }

    /**
     * Obtém a instância do painel que contém a tabela de produtos.
     *
     * @return painel de tabela de produtos
     */
    public PainelTabelaProdutos getPainelTabelaProdutos() {
        return painelTabela;
    }
}
