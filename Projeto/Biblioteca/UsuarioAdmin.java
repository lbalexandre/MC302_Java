package trabalho1;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


// Classe UsuarioAdmin: Abstração de um administrador para os efeitos do sistema. Extende usuario.

public class UsuarioAdmin extends Usuario {

    // Construtor.

    public UsuarioAdmin(String nome, String senha, String dataNasc, String email, boolean status) {
        super(nome, senha, dataNasc, email, status);
    }

    // Métodos de usuarioAdmin.

    // Método cadastrarCupom: Permite ao Admin cadastrar um cupom no sistema a partir de dados obtidos pela interface gráfica.

    public void cadastrarCupom(String codigo, double desconto) {

        Cupom cupom = new Cupom(codigo, desconto);
        Biblioteca.cupons.add(cupom);

    }

    // Método cadastrarLivro: A partir dos dados obtidos via interface gráfica, gera um objeto livro e o adiciona no acervo do sistema.

    public void cadastrarLivro(String nome, String autor, int indice, int edicao, int ano, int livrosDisponiveis, double valor) {

        List<Genero> list = new ArrayList<Genero>(EnumSet.allOf(Genero.class));

        Livro livro = new Livro(nome, autor, list.get(indice), edicao, ano, livrosDisponiveis, valor);
        Biblioteca.acervo.add(livro);  // Aqui está a justificativa de cadastrarLivro ser abstrato: Enquanto usuarioComum e usuarioEstudante adicionam livros em seus acervos, usuarioAdmin adiciona livros na AL acervo da Biblioteca.
    }

    // Método banirUsuario: Permite o admin do sistema banir um usuário. Lança SistemaExcecao.

    public void banirUsuario(String nome) throws SistemaExcecao {

        int resultado = Gerenciador.checaUsuario(nome);

        // Se nenhuma exceção foi lançada até esse ponto, o usuário que se deseja banir existe.

        Biblioteca.usuarios.remove(Biblioteca.usuarios.get(resultado));
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
