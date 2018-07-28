package trabalho1;

import java.io.*;
import java.util.*;

// Abstração de um usuarioComum para os efeitos do sistema. Extende Usuario.

public class UsuarioComum extends Usuario {

    // Construtor.

    public UsuarioComum(String nome, String senha, String dataNasc, String email, boolean status) {
        super(nome, senha, dataNasc, email, status);

    }

    // Método alteraDados: Permite que o usuário altere seus dados a partir de dados fornecidos pela interface gráfica.

    public void alteraDados(String novoNome, String novaSenha, String novoEmail, String novoAniversario) {
        this.setNome(novoNome);
        this.setSenha(novaSenha);
        this.setEmail(novoEmail);
        this.setDataNasc(novoAniversario);
    }

    // Implementação de cadastrarLivro.

    public void cadastrarLivro(String nome, String autor, int indice, int edicao, int ano, int livrosDisponiveis, double valor) {

        List<Genero> list = new ArrayList<Genero>(EnumSet.allOf(Genero.class));

        Livro livro = new Livro(nome, autor, list.get(indice), edicao, ano, livrosDisponiveis, valor);
        this.getLivrosDoUsuario().add(livro);
    }

    // Implementação de SalvarLer.

    public void salvar(Writer writer) throws IOException {

        writer.write(this.getMensagens().toString());

        writer.flush();
    }

    public String ler(File arquivo) throws IOException {
        String out = "";
        Reader arq = new FileReader(arquivo);
        BufferedReader ler = new BufferedReader(arq);
        String linha = ler.readLine();
        while (linha != null) {
            out += linha;
            linha = ler.readLine();
        }
        ler.close();
        arq.close();
        return out;
    }
}
