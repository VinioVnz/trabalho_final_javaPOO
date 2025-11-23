package model;

import java.time.LocalDate;

import model.categorias.*;

/**
 * Classe de entrada do aplicativo para demonstração e testes.
 * <p>
 * O método {@code main} ilustra o uso das classes de controle de estoque,
 * registrando produtos, entradas e saídas e exibindo o saldo e os movimentos.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class Main {
    /**
     * Ponto de entrada da aplicação de demonstração.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {

        ControleEstoque controle = new ControleEstoque();

        Categoria hardware = new ComponenteHardware();
        Categoria periferico = new Periferico();

        Produto p1 = new Produto("SSD Kingston 480GB", 250.00, 10, hardware);
        Produto p2 = new Produto("Mouse Logitech", 70.00, 5, periferico);
        Produto p3 = new Produto("Mouse Logitech", 70.00, 5, periferico);

        controle.adicionarProduto(p1);
        controle.adicionarProduto(p2);

        EntradaEstoque entrada1 = new EntradaEstoque(LocalDate.of(2025, 11, 10), p1, 5, 230.00);
        EntradaEstoque entrada2 = new EntradaEstoque(LocalDate.of(2025, 11, 11), p2, 10, 60.00);

        controle.registrarEntrada(entrada1);
        controle.registrarEntrada(entrada2);

        SaidaEstoque saida1 = new SaidaEstoque(LocalDate.of(2025, 11, 12), p1, 3);
        SaidaEstoque saida2 = new SaidaEstoque(LocalDate.of(2025, 11, 13), p2, 4);

        controle.registrarSaida(saida1);
        controle.registrarSaida(saida2);

        System.out.println("\n SALDO ATUAL DO ESTOQUE:");
        controle.consultarSaldoAtual();
        
        System.out.println("\n MOVIMENTOS ORDENADOS POR DATA:");
        controle.listarMovimentos();
    }
}
