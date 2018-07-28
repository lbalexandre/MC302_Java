package trabalho1;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

// Interface SalvarLer: mostra a assinatura dos métodos ligados à obter e registrar dados em arquivos (files). Implementada por Usuario (e suas classes filhas) e Biblioteca.

public interface SalvarLer {

    public void salvar(Writer writer) throws IOException;

    public String ler(File arquivo) throws IOException;

}
