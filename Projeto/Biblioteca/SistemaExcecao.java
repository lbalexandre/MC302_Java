package trabalho1;

/* Classe que representa as exceções vigentes no sistema. No lugar de criar uma série de exceções para cada caso, preferimos criar apenas uma (para
   assim, tornar o tratamento de exceções algo universal/uniforme) que recebe a frase adequada ao erro que ocorreu durante seu instanciamento.
 */

public class SistemaExcecao extends Exception {

    private static final long serialVersionUID = 1L;

    public SistemaExcecao(String mensagem) {
        super(mensagem);
    }
}
