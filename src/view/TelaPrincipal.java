package view;
import javax.swing.*;

import view.CadastroProduto.TelaCadastroProdutos;
import view.ListarMovimentos.TelaListarMovimentos;


import java.awt.*;

public class TelaPrincipal extends JFrame{
    private CardLayout cardLayout;
    private JPanel painelConteudo;

    public TelaPrincipal(){
        setTitle("Sistema loja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        PainelLateral painelLateral = new PainelLateral();
        add(painelLateral, BorderLayout.WEST);

        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);

        painelConteudo.add(new TelaCadastroProdutos(), "produtos");
        painelConteudo.add(new TelaListarMovimentos(),"movimentos");

        add(painelConteudo, BorderLayout.CENTER);

        painelLateral.onProdutos(() -> mostrarTela("produtos"));
        painelLateral.onMovimentos(() -> mostrarTela("movimentos"));
    }

    private void mostrarTela(String nome) {
        cardLayout.show(painelConteudo, nome);
    }
}