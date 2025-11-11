import java.util.ArrayList;
import java.util.Collections;

public class ControleEstoque {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<MovimentoEstoque> movimentos = new ArrayList<>();

    public void adicionarProduto(Produto produto){
        produtos.add(produto);
    }

    public void registrarEntrada(EntradaEstoque entrada){
        movimentos.add(entrada);
        entrada.getProduto().setQuantidade(entrada.getProduto().getQuantidade() + entrada.getQuantidade());
    }

    public void registrarSaida(SaidaEstoque saida){
        movimentos.add(saida);
        saida.getProduto().setQuantidade(
            saida.getProduto().getQuantidade() - saida.getQuantidade()
        );
    }

    public void listarMovimentos(){
        Collections.sort(movimentos);

        for(MovimentoEstoque movimento : movimentos){
            System.out.println(movimento.getData() + " | " + movimento.getProduto().getNome() + " | " + movimento.getQuantidade());
        }
    }

    public void consultarSaldoAtual(){
        for(Produto produto : produtos){
            System.out.println(produto.getNome() + " | "+ produto.getQuantidade()+ "unidades | R$ " + produto.calcularValorTotal());
        }
    }
}
