package view;

import javax.swing.JFrame;

import model.ControleEstoque;
import model.Produto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroProdutos extends JFrame{
    private ControleEstoque controle;
    private PainelFormularioProduto painelFormulario;
    private PainelTabelaProdutos painelTabela;
    private PainelLateral painelLateral;


    public TelaCadastroProdutos(){
        setTitle("Cadastro de Produtos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);

        controle = new ControleEstoque();
        
        setLayout(new BorderLayout(10, 10));

        painelLateral = new PainelLateral();
        add(painelLateral, BorderLayout.WEST);
        
        painelFormulario = new PainelFormularioProduto();
        painelTabela = new PainelTabelaProdutos();

        
        add(painelFormulario, BorderLayout.NORTH);
        add(painelTabela, BorderLayout.CENTER);

        painelTabela.atualizarTabela(controle.getTodosProdutos());
        painelFormulario.definirAcaoBotaoCadastrar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Produto produto = painelFormulario.obterProdutoDoForulario(controle);
                if(produto != null){
                    controle.adicionarProduto(produto);
                    painelTabela.atualizarTabela(controle.getTodosProdutos());
                    painelFormulario.limparFormulario();
                }
            }
        });

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
}
