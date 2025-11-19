package view.ListarMovimentos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelaListarMovimentos extends JPanel {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaListarMovimentos() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Movimentações do Estoque");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(
                new Object[]{"Tipo", "Data", "Produto (ID)", "Quantidade", "Valor Unitário"}, 
                0
        );

        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        carregarMovimentosDoCSV();
    }

    public void carregarMovimentosDoCSV() {
        modeloTabela.setRowCount(0);

        String caminho = "movimentos.csv"; // ajuste o caminho se necessário

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] partes = linha.split(";");

                if (partes.length == 5) {
                    modeloTabela.addRow(new Object[]{
                            partes[0], // Tipo (ENTRADA/SAIDA)
                            partes[1], // Data
                            partes[2], // ID do Produto
                            partes[3], // Quantidade
                            partes[4]  // Valor
                    });
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao ler movimentos do arquivo CSV.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}