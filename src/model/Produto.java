package model;

import model.categorias.Categoria;

/**
 * Representa um produto comercializável no sistema de controle de estoque.
 * <p>
 * Esta classe encapsula os dados basilares de um produto, tais como código,
 * nome, preço unitário, quantidade em estoque e categoria associada.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class Produto {
    private static int proximoCodigo = 1;
    private int codigo;
    private String nome;
    private double precoUnitario;
    private int quantidade;
    private Categoria categoria;

    /**
     * Construtor que gera automaticamente um código sequencial para o produto.
     *
     * @param nome         Nome do produto
     * @param precoUnitario Preço unitário do produto
     * @param quantidade   Quantidade inicial em estoque
     * @param categoria    Categoria à qual o produto pertence
     */
    public Produto(String nome, double precoUnitario, int quantidade, Categoria categoria) {
        this.codigo = proximoCodigo++;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    /**
     * Construtor que permite definir explicitamente o código do produto.
     * <p>
     * Quando utilizado, o contador interno de códigos é atualizado para
     * garantir que códigos subsequentes gerados automaticamente não colidam.
     * </p>
     *
     * @param codigo       Código do produto
     * @param nome         Nome do produto
     * @param precoUnitario Preço unitário do produto
     * @param quantidade   Quantidade inicial em estoque
     * @param categoria    Categoria à qual o produto pertence
     */
    public Produto(int codigo, String nome, double precoUnitario, int quantidade, Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.categoria = categoria;

        if (codigo >= proximoCodigo) {
            proximoCodigo = codigo + 1;
        }
    }
    
    /**
     * Obtém o código do produto.
     *
     * @return código que identifica unicamente o produto
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * Define o código do produto.
     *
     * @param codigo novo código do produto
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    /**
     * Obtém o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }
    /**
     * Define o nome do produto.
     *
     * @param nome novo nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Obtém o preço unitário do produto.
     *
     * @return preço unitário em reais
     */
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    /**
     * Define o preço unitário do produto.
     *
     * @param precoUnitario novo preço unitário em reais
     */
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    /**
     * Obtém a quantidade em estoque do produto.
     *
     * @return quantidade disponível em estoque
     */
    public int getQuantidade() {
        return quantidade;
    }
    /**
     * Define a quantidade em estoque do produto.
     *
     * @param quantidade nova quantidade em estoque
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    /**
     * Obtém a categoria associada ao produto.
     *
     * @return instância de {@link Categoria} representando a categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }
    /**
     * Define a categoria do produto.
     *
     * @param categoria nova categoria do produto
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Calcula o valor total do estoque para este produto.
     *
     * @return produto da quantidade pelo preço unitário
     */
    public double calcularValorTotal(){
        return quantidade * precoUnitario;
    }
    
    /**
     * Retorna o nome da categoria associada ao produto.
     *
     * @return nome da categoria
     */
    public String getNomeCategoria(){
        return categoria.getNome();
    }
}
