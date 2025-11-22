package view.RegistrarEntrada;

import model.ControleEstoque;
import model.EntradaEstoque;
import model.Produto;
import view.ListarMovimentos.TelaListarMovimentos;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaRegistrarEntrada extends JPanel {

    private ControleEstoque controle;
    private TelaListarMovimentos telaListarMovimentos;
    public TelaRegistrarEntrada(ControleEstoque controle,TelaListarMovimentos telaMovimentos) {
        this.controle = controle;
        this.telaListarMovimentos = telaMovimentos;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Registrar Entrada no Estoque");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        // Painel central
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        JLabel lblCodigo = new JLabel("Código do Produto:");
        JTextField txtCodigo = new JTextField();

        JLabel lblQuantidade = new JLabel("Quantidade:");
        JTextField txtQuantidade = new JTextField();

        JLabel lblValor = new JLabel("Valor Unitário:");
        JTextField txtValor = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Entrada");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));

        // Adicionando ao painel
        c.gridx = 0; c.gridy = 0;
        painelForm.add(lblCodigo, c);
        c.gridx = 1;
        painelForm.add(txtCodigo, c);

        c.gridx = 0; c.gridy = 1;
        painelForm.add(lblQuantidade, c);
        c.gridx = 1;
        painelForm.add(txtQuantidade, c);

        c.gridx = 0; c.gridy = 2;
        painelForm.add(lblValor, c);
        c.gridx = 1;
        painelForm.add(txtValor, c);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        painelForm.add(btnRegistrar, c);

        add(painelForm, BorderLayout.CENTER);

        // Lógica do botão
        btnRegistrar.addActionListener(e -> {

            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                int quantidade = Integer.parseInt(txtQuantidade.getText());
                double valor = Double.parseDouble(txtValor.getText());

                Produto p = controle.getTodosProdutos()
                        .stream()
                        .filter(prod -> prod.getCodigo() == codigo)
                        .findFirst()
                        .orElse(null);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                    return;
                }

                EntradaEstoque entrada = new EntradaEstoque(
                        LocalDate.now(),
                        p,
                        quantidade,
                        valor
                );

                controle.registrarEntrada(entrada);
                telaListarMovimentos.carregarMovimentosDoCSV();
                JOptionPane.showMessageDialog(this, "Entrada registrada com sucesso!");
                txtCodigo.setText("");
                txtQuantidade.setText("");
                txtValor.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!");
            }
        });
    }
}