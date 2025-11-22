package view.RegistrarSaida;

import model.ControleEstoque;
import model.SaidaEstoque;
import model.Produto;
import view.ListarMovimentos.TelaListarMovimentos;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaRegistrarSaida extends JPanel {

    private ControleEstoque controle;
    private TelaListarMovimentos telaListarMovimentos;

    public TelaRegistrarSaida(ControleEstoque controle, TelaListarMovimentos telaListarMovimentos) {
        this.controle = controle;
        this.telaListarMovimentos = telaListarMovimentos;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Registrar Saída do Estoque");
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

        JButton btnRegistrar = new JButton("Registrar Saída");
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

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        painelForm.add(btnRegistrar, c);

        add(painelForm, BorderLayout.CENTER);

        // Lógica do botão
        btnRegistrar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText().trim());
                int quantidade = Integer.parseInt(txtQuantidade.getText().trim());

                if (quantidade <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero!");
                    return;
                }

                Produto p = controle.getTodosProdutos()
                        .stream()
                        .filter(prod -> prod.getCodigo() == codigo)
                        .findFirst()
                        .orElse(null);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                    return;
                }

                // Validação: impedir estoque negativo
                if (p.getQuantidade() < quantidade) {
                    JOptionPane.showMessageDialog(this, "Estoque insuficiente! Disponível: " + p.getQuantidade());
                    return;
                }

                SaidaEstoque saida = new SaidaEstoque(
                        LocalDate.now(),
                        p,
                        quantidade
                );

                controle.registrarSaida(saida);

                // Atualiza tabela de movimentos (a instância passada pela TelaPrincipal)
                if (telaListarMovimentos != null) {
                    telaListarMovimentos.carregarMovimentosDoCSV();
                }

                JOptionPane.showMessageDialog(this, "Saída registrada com sucesso!");
                txtCodigo.setText("");
                txtQuantidade.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });
    }
}
