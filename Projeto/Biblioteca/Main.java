// Alexandre Luciano Barbosa	165232
// João Pedro de Amorim 		176131
// Leonardo Rodrigues Marques	178610

package trabalho1;
import telas.TelaInicial;
import telas.TelaPerguntaModoTeste;

import java.io.*;

// Classe main: Ponto de partida do sistema. Ela apenas instancia a interface inicial do nosso programa. O fluxo do programa é ditado pelas interfaces.

public class Main {

    public static void main(String[] args) throws SistemaExcecao {

        new TelaPerguntaModoTeste().setVisible(true);

    }
}
