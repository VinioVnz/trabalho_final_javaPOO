package view;

import javax.swing.*;
import java.awt.*;

public class PainelLateral extends JPanel {

    public PainelLateral() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnProdutos = criarBotao("Produtos");
        JButton btnEntrada = criarBotao("Registrar Entrada");
        JButton btnSaida = criarBotao("Registrar Saída");
        JButton btnSaldo = criarBotao("Consultar Saldo");
        JButton btnMovimentos = criarBotao("Listar Movimentos");

        add(btnProdutos);
        add(Box.createVerticalStrut(8));
        add(btnEntrada);
        add(Box.createVerticalStrut(8));
        add(btnSaida);
        add(Box.createVerticalStrut(8));
        add(btnSaldo);
        add(Box.createVerticalStrut(8));
        add(btnMovimentos);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);

        botao.setAlignmentX(CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(150, 35));

        return botao;
    }
}
