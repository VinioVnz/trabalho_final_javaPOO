package view;

import javax.swing.*;

import model.ControleEstoque;
import view.CadastroProduto.TelaCadastroProdutos;
import view.ConsularSaldo.TelaConsultarSaldo;
import view.ListarMovimentos.TelaListarMovimentos;
import view.RegistrarEntrada.TelaRegistrarEntrada;
import view.RegistrarSaida.TelaRegistrarSaida;

import java.awt.*;

/**
 * Janela principal da aplicação contendo navegação entre as telas.
 * <p>
 * A classe monta a interface principal, instancia o controle de estoque e
 * sincroniza as ações dos botões laterais com as telas correspondentes.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class TelaPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelConteudo;


    private ControleEstoque controleEstoque = new ControleEstoque();

    private TelaListarMovimentos telaListarMovimentos = new TelaListarMovimentos(controleEstoque);
    private TelaConsultarSaldo telaConsultarSaldo = new TelaConsultarSaldo(controleEstoque);
    private TelaCadastroProdutos telaProdutos = new TelaCadastroProdutos(controleEstoque);

    /**
     * Constrói a tela principal e configura a navegação entre as demais telas.
     */
    public TelaPrincipal() {

        setTitle("Sistema loja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        PainelLateral painelLateral = new PainelLateral();
        add(painelLateral, BorderLayout.WEST);


        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);

        painelConteudo.add(telaProdutos, "produtos");
        painelConteudo.add(telaListarMovimentos, "movimentos");
        painelConteudo.add(new TelaRegistrarEntrada(controleEstoque, telaListarMovimentos), "registrarEntrada");
        painelConteudo.add(new TelaRegistrarSaida(controleEstoque, telaListarMovimentos), "registrarSaida");
        painelConteudo.add(telaConsultarSaldo, "saldo");

        add(painelConteudo, BorderLayout.CENTER);


        painelLateral.onProdutos(() -> {
            telaProdutos.getPainelTabelaProdutos()
                    .atualizarTabela(controleEstoque.getTodosProdutos()); 
            mostrarTela("produtos");
        });


        painelLateral.onMovimentos(() -> {
            telaListarMovimentos.carregarMovimentosDoCSV(); 
            mostrarTela("movimentos");
        });


        painelLateral.onEntrada(() -> mostrarTela("registrarEntrada"));


        painelLateral.onSaida(() -> {
            telaListarMovimentos.carregarMovimentosDoCSV();
            mostrarTela("registrarSaida");
        });


        painelLateral.onSaldo(() -> {
            telaConsultarSaldo.atualizarSaldo();
            mostrarTela("saldo");
        });
    }

    /**
     * Exibe a tela identificada pelo nome no painel de conteúdo.
     *
     * @param nome identificador da tela a ser exibida
     */
    private void mostrarTela(String nome) {
        cardLayout.show(painelConteudo, nome);
    }
}
