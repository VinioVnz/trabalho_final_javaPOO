package model.categorias;

/**
 * Representa a categoria genérica de um produto.
 * <p>
 * Subclasses concretas devem definir categorias específicas como
 * periféricos, componentes, acessórios, etc.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public abstract class Categoria {
    String nome;
    
    /**
     * Constrói uma categoria com o nome informado.
     *
     * @param nome nome descritivo da categoria
     */
    public Categoria(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o nome da categoria.
     *
     * @return nome da categoria
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da categoria.
     *
     * @param nome novo nome da categoria
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
