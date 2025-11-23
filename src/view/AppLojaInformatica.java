package view;

/**
 * Aplicação de interface gráfica para a loja de informática.
 * <p>
 * Classe de inicialização que exibe a janela principal da aplicação.
 * </p>
 *
 * @author Vinicius Bornhoffen
 * @author Caio Schumann
 * @author Arthur Nascimento Pereira
 * @author Vitor André Pickler
 */
public class AppLojaInformatica {
   /**
    * Inicializa e exibe a interface gráfica principal.
    *
    * @param args argumentos de linha de comando (não utilizados)
    */
   public static void main(String[] args) {
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);
    }
}
