package view.ListarMovimentos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.ControleEstoque;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Tela que lista os movimentos de estoque a partir do arquivo CSV.
 *
 * Exibe uma tabela contendo tipo, data, produto, quantidade e valor unitário
 * para cada movimento registrado.
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class TelaListarMovimentos extends JPanel {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private ControleEstoque controle;

    /**
     * Construtor que recebe o controle de estoque utilizado para mapear
     * identificadores de produto para seus nomes.
     *
     * @param controle instância de {@link ControleEstoque}
     */
    public TelaListarMovimentos(ControleEstoque controle) {
        this.controle = controle;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Movimentações do Estoque");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(
                new Object[] { "Tipo", "Data", "Produto (ID)", "Quantidade", "Valor Unitário" },
                0);

        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        carregarMovimentosDoCSV();
    }

    /**
     * Carrega os movimentos presentes no arquivo CSV e os exibe na tabela.
     */
    public void carregarMovimentosDoCSV() {
        modeloTabela.setRowCount(0);

        String caminho = "movimentos.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] partes = linha.split(";");

                if (partes.length == 5) {

                    int id = Integer.parseInt(partes[2]);

                    var produto = controle.findProdutoPorCodigo(id);
                    String nomeProduto = (produto != null)
                            ? produto.getNome()
                            : "PRODUTO EXCLUÍDO (ID: " + id + ")";

                    modeloTabela.addRow(new Object[] {
                            partes[0],
                            partes[1],
                            nomeProduto,
                            partes[3],
                            partes[4]
                    });
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao ler movimentos do arquivo CSV.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}