package trabalho1;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

// Classe UsuarioEstudante: abstração de um usuário estudante para os efeitos do sistema. Extende Usuario.

public class UsuarioEstudante extends Usuario {

    // Atributos.

    private String instituicao;
    private int ra;

    // Construtor.

    public UsuarioEstudante(String nome, String senha, String dataNasc, String email, boolean status, String instituicao, int ra) {
        super(nome, senha, dataNasc, email, status);
        this.instituicao = instituicao;
        this.ra = ra;
    }

    // Getters & Setters.

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    // Método buscaUsuarioUniversidade: Exclusivo de usuarioEstudante, é capaz de informar quais usuários do sistema estão na mesma instituição de ensino do usuário atual. Lança SistemaExcecao.

    public String buscaUsuarioUniversidade() throws SistemaExcecao {
        boolean flag = false;
        String out = "";

        for (int i = 0; i < Biblioteca.usuarios.size(); i++) {
            if (Biblioteca.usuarios.get(i) instanceof UsuarioEstudante // Verifica se o usuário é um UsuarioEstudante
                    && instituicao.equals(((UsuarioEstudante) Biblioteca.usuarios.get(i)).getInstituicao()) // Verifica se ambos possuem a mesma instiuição de ensino
                    && !(this.getNome().equals(Biblioteca.usuarios.get(i).getNome()))) { // Verifica se não é o próprio usuário que chamou o método
                out += "Usuário " + Biblioteca.usuarios.get(i).getNome() + " também pertence à " + instituicao + "\n";
                flag = true;
            }
        }
        if (!flag)
            throw new SistemaExcecao("Não encontramos nenhum usuário de sua instituição de ensino!");
        return out;
    }

    // Método alteraDados: permite que o usuário altere seus dados a partir de dados obtidos pela interface gráfica.

    public void alteraDados(String novoNome, String novaSenha, String novoEmail, String novoAniversario, int novoRA, String novaInstituicao) {
        this.setNome(novoNome);
        this.setSenha(novaSenha);
        this.setEmail(novoEmail);
        this.setDataNasc(novoAniversario);
        this.setRa(novoRA);
        this.setInstituicao(novaInstituicao);
    }

    // Implementação de cadastrarLivro.

    public void cadastrarLivro(String nome, String autor, int indice, int edicao, int ano, int livrosDisponiveis, double valor) {

        List<Genero> list = new ArrayList<Genero>(EnumSet.allOf(Genero.class));

        Livro livro = new Livro(nome, autor, list.get(indice), edicao, ano, livrosDisponiveis, valor);
        this.getLivrosDoUsuario().add(livro);
    }


    // toString de usuarioEstudante.

    @Override
    public String toString() {
        String out = super.toString();
        out = out + "Instituicao: " + getInstituicao() + "\n";
        out = out + "Ra: " + getRa() + "\n";

        return out;
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

