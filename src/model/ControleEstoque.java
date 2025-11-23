package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.categorias.*;

import java.util.Locale;

/**
 * Responsável pelo gerenciamento do conjunto de produtos e movimentos de estoque.
 * <p>
 * A classe fornece operações para adicionar, registrar entradas e saídas,
 * persistir dados em CSV e recuperar informações persistidas.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class ControleEstoque {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<MovimentoEstoque> movimentos = new ArrayList<>();

    private static final String PRODUTOS_CSV = "produtos.csv";
    private static final String MOVIMENTOS_CSV = "movimentos.csv";
    private static final String CSV_SEPARATOR = ";";

    public ControleEstoque() {
        recuperarProdutos();
        recuperarMovimentos();
    }

    /**
     * Construtor padrão que inicializa o controle de estoque carregando os
     * dados persistidos de produtos e movimentos.
     */

    /**
     * Adiciona um novo produto ao controle de estoque e persiste os dados.
     *
     * @param produto instância de {@link Produto} a ser adicionada
     */
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        gravarDados();
    }

        /**
         * Registra uma entrada de estoque: atualiza a quantidade do produto,
         * persiste os dados e exibe um resumo da operação.
         *
         * @param entrada instância de {@link EntradaEstoque} contendo os dados da entrada
         */
        public void registrarEntrada(EntradaEstoque entrada) {
        movimentos.add(entrada);

        Produto p = entrada.getProduto();
        int antes = p.getQuantidade();
        int depois = antes + entrada.getQuantidade();

        p.setQuantidade(depois);
        gravarDados();

        double valorTotal = p.calcularValorTotal();

        JOptionPane.showMessageDialog(null,
            "Entrada registrada!\n\n" +
                "Produto: " + p.getNome() + "\n" +
                "Quantidade adicionada: " + entrada.getQuantidade() + "\n" +
                "Saldo anterior: " + antes + "\n" +
                "Saldo atual: " + depois + "\n" +
                "Valor total atual: R$ " + String.format("%.2f", valorTotal),
            "Entrada registrada",
            JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Registra uma saída de estoque e persiste os dados.
     *
     * @param saida instância de {@link SaidaEstoque} contendo os dados da saída
     */
    public void registrarSaida(SaidaEstoque saida) {
        movimentos.add(saida);
        saida.getProduto().setQuantidade(
                saida.getProduto().getQuantidade() - saida.getQuantidade());
        gravarDados();
    }

    /**
     * Lista os movimentos presentes no sistema, imprimindo-os ordenados por data.
     */
    public void listarMovimentos() {
        Collections.sort(movimentos);

        for (MovimentoEstoque movimento : movimentos) {
            System.out.println(
                    movimento.getData() + " | " +
                            movimento.getProduto().getNome() + " | " +
                            movimento.getQuantidade());
        }
    }

    /**
     * Imprime no console o saldo atual de cada produto com o valor total por produto.
     */
    public void consultarSaldoAtual() {
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " | " + produto.getQuantidade() + "unidades | R$ "
                    + produto.calcularValorTotal());
        }
    }

    /**
     * Persiste os dados de produtos e movimentos em arquivos CSV.
     */
    public void gravarDados() {
        gravarProdutos();
        gravarMovimentos();
    }

    private void gravarProdutos() {

        try (PrintWriter writer = new PrintWriter(PRODUTOS_CSV)) {

            for (Produto p : produtos) {
                String precoFormatado = String.format(Locale.US, "%.2f", p.getPrecoUnitario());

                String linha = p.getCodigo() + CSV_SEPARATOR +
                        p.getNome() + CSV_SEPARATOR +
                        precoFormatado + CSV_SEPARATOR +
                        p.getQuantidade() + CSV_SEPARATOR +
                        p.getCategoria().getNome();

                writer.println(linha);
            }
            System.out.println("Produtos gravados com sucesso (PrintWriter).");

        } catch (IOException e) {
            System.err.println("Erro ao gravar produtos: " + e.getMessage());
        }
    }

    private void gravarMovimentos() {
        /* Se não houver movimentos, não faz nada */
        if (movimentos.isEmpty()) {
            return;
        }

        /* Ordenar movimentos antes de gravar */
        Collections.sort(movimentos);

        try (PrintWriter writer = new PrintWriter(MOVIMENTOS_CSV)) {

            for (MovimentoEstoque m : movimentos) {

                String tipo = (m instanceof EntradaEstoque) ? "ENTRADA" : "SAIDA";
                double valorUnit = 0.0;

                if (m instanceof EntradaEstoque entrada) {
                    valorUnit = entrada.getValorUnitario();
                }

                String valorFormatado = String.format(Locale.US, "%.2f", valorUnit);

                String linha = tipo + CSV_SEPARATOR +
                        m.getData() + CSV_SEPARATOR +
                        m.getProduto().getCodigo() + CSV_SEPARATOR +
                        m.getQuantidade() + CSV_SEPARATOR +
                        valorFormatado;

                writer.println(linha);
            }

        } catch (IOException e) {
            System.err.println("Erro ao gravar movimentos: " + e.getMessage());
        }
    }

    private void recuperarProdutos() {
        File arquivoProdutos = new File(PRODUTOS_CSV);

        if (!arquivoProdutos.exists()) {
            System.out.println("Arquivo " + PRODUTOS_CSV + " não encontrado. Iniciando novo.");
            return;
        }

        try (Scanner scannerArquivo = new Scanner(arquivoProdutos)) {

            while (scannerArquivo.hasNextLine()) {
                String linha = scannerArquivo.nextLine();

                Scanner scannerLinha = new Scanner(linha);
                scannerLinha.useDelimiter(CSV_SEPARATOR);
                scannerLinha.useLocale(Locale.US);

                try {
                    int codigo = scannerLinha.nextInt();
                    String nome = scannerLinha.next();
                    double preco = scannerLinha.nextDouble();
                    int quantidade = scannerLinha.nextInt();
                    String categoriaNome = scannerLinha.next();

                    Categoria categoria = getCategoriaPorNome(categoriaNome);

                    if (categoria != null) {
                        Produto p = new Produto(codigo, nome, preco, quantidade, categoria);
                        this.produtos.add(p);
                    } else {
                        System.err.println("Categoria não encontrada: " + categoriaNome);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao ler linha do produto: " + linha + " | Erro: " + e.getMessage());
                }
                scannerLinha.close();
            }
            System.out.println("Produtos recuperados com sucesso (Scanner).");

        } catch (FileNotFoundException e) {
            System.err.println("Erro ao recuperar produtos: " + e.getMessage());
        }
    }

    private void recuperarMovimentos() {
    File arquivoMovimentos = new File(MOVIMENTOS_CSV);

    if (!arquivoMovimentos.exists()) {
        System.out.println("Arquivo " + MOVIMENTOS_CSV + " não encontrado. Iniciando novo.");
        return;
    }

    try (Scanner scannerArquivo = new Scanner(arquivoMovimentos)) {

        while (scannerArquivo.hasNextLine()) {
            String linha = scannerArquivo.nextLine();
            Scanner scannerLinha = new Scanner(linha);
            scannerLinha.useDelimiter(CSV_SEPARATOR);
            scannerLinha.useLocale(Locale.US);

            try {
                String tipo = scannerLinha.next();
                LocalDate data = LocalDate.parse(scannerLinha.next());
                int produtoCodigo = scannerLinha.nextInt();
                int quantidade = scannerLinha.nextInt();
                double valorUnit = scannerLinha.nextDouble();

                Produto produto = findProdutoPorCodigo(produtoCodigo);

                if (produto == null) {
                    System.err.println("Movimento ignorado! Produto não existe mais: " + produtoCodigo);
                    continue;
                }

                if (tipo.equals("ENTRADA")) {
                    movimentos.add(new EntradaEstoque(data, produto, quantidade, valorUnit));
                } else if (tipo.equals("SAIDA")) {
                    movimentos.add(new SaidaEstoque(data, produto, quantidade));
                }

            } catch (Exception e) {
                System.err.println("Erro ao ler linha do movimento: " + linha + " | Erro: " + e.getMessage());
            }
            scannerLinha.close();
        }
        System.out.println("Movimentos recuperados com sucesso (Scanner).");

    } catch (FileNotFoundException e) {
        System.err.println("Erro ao recuperar movimentos: " + e.getMessage());
    }
}


    /**
     * Retorna uma instância de {@link Categoria} correspondente ao nome informado.
     *
     * @param nome nome da categoria conforme armazenado no CSV
     * @return instância de {@link model.categorias.Categoria} ou {@code null} se não existir
     */
    public Categoria getCategoriaPorNome(String nome) {
        if (nome == null) {
            return null;
        }
        switch (nome.trim()) {
            case "Componente de Hardware":
                return new ComponenteHardware();

            case "Periférico":
                return new Periferico();

            case "Acessorio":
                return new Acessorio();

            case "Outro Produto":
                return new OutroProduto();

            default:
                return null;
        }
    }

    /**
     * Busca um produto pelo código.
     *
     * @param codigo código do produto
     * @return produto correspondente ou {@code null} se não encontrado
     */
    public Produto findProdutoPorCodigo(int codigo) {
        for (Produto p : this.produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    /**
     * Exclui um produto pelo índice da lista e persiste as alterações.
     *
     * @param indice posição do produto na lista de gerenciamento
     */
    public void excluirProduto(int indice) {
        if (indice >= 0 && indice < produtos.size()) {
            produtos.remove(indice);
        }
        gravarDados();
    }

    /**
     * Retorna uma cópia da lista de todos os produtos cadastrados.
     *
     * @return lista de produtos
     */
    public List<Produto> getTodosProdutos() {
        return new ArrayList<>(produtos);
    }

    /**
     * Calcula o saldo atual do estoque utilizando o serviço {@link SaldoCalculator}.
     *
     * @return resultado com o valor total e as quantidades por produto
     */
    public SaldoCalculator.ResultadoSaldo calcularSaldoAtual() {
        SaldoCalculator service = new SaldoCalculator();
        return service.calcularSaldo(this.movimentos);
    }

    /**
     * Retorna a lista de movimentos de estoque atualmente carregada.
     *
     * @return lista de movimentos
     */
    public List<MovimentoEstoque> getMovimentoEstoques(){
        return movimentos;
    }
}