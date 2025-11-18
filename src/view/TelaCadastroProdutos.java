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

    public TelaCadastroProdutos(){
        setTitle("Cadastro de Produtos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);

        controle = new ControleEstoque();
        
        setLayout(new BorderLayout(10, 10));

        painelFormulario = new PainelFormularioProduto();

        add(painelFormulario, BorderLayout.NORTH);

        painelFormulario.definirAcaoBotaoCadastrar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Produto produto = painelFormulario.obterProdutoDoForulario(controle);
                if(produto != null){
                    controle.adicionarProduto(produto);
                    painelFormulario.limparFormulario();
                }
            }
        });
    }
}
