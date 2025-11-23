package model.categorias;

/**
 * Categoria genérica para produtos que não se enquadram nas demais categorias.
 *
 * Utilizada como classificação reservada para itens diversos.
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class OutroProduto extends Categoria{

    /**
     * Construtor que define o nome padronizado da categoria.
     */
    public OutroProduto() {
        super("Outro produto");

    }
    
}
