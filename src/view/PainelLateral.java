package view;

import javax.swing.*;
import java.awt.*;

/**
 * Painel lateral contendo botões de navegação da interface.
 * <p>
 * Fornece botões para acessar as telas de gerenciamento de produtos,
 * registrar entradas/saídas, consultar saldo e listar movimentos.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class PainelLateral extends JPanel {
    private JButton btnProdutos;
    private JButton btnEntrada;
    private JButton btnSaida;
    private JButton btnSaldo;
    private JButton btnMovimentos;

    /**
     * Construtor que monta a interface dos botões laterais.
     */
    public PainelLateral() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnProdutos = criarBotao("Produtos");
        btnEntrada = criarBotao("Registrar Entrada");
        btnSaida = criarBotao("Registrar Saída");
        btnSaldo = criarBotao("Consultar Saldo");
        btnMovimentos = criarBotao("Listar Movimentos");

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

    /**
     * Registra callback acionado quando o botão "Produtos" for pressionado.
     *
     * @param r ação a ser executada
     */
    public void onProdutos(Runnable r) { btnProdutos.addActionListener(e -> r.run()); }

    /**
     * Registra callback para o botão de registrar entrada.
     *
     * @param r ação a ser executada
     */
    public void onEntrada(Runnable r) { btnEntrada.addActionListener(e -> r.run()); }

    /**
     * Registra callback para o botão de registrar saída.
     *
     * @param r ação a ser executada
     */
    public void onSaida(Runnable r) { btnSaida.addActionListener(e -> r.run()); }

    /**
     * Registra callback para o botão de consultar saldo.
     *
     * @param r ação a ser executada
     */
    public void onSaldo(Runnable r) { btnSaldo.addActionListener(e -> r.run()); }

    /**
     * Registra callback para o botão de listar movimentos.
     *
     * @param r ação a ser executada
     */
    public void onMovimentos(Runnable r) { btnMovimentos.addActionListener(e -> r.run()); 
    }
}