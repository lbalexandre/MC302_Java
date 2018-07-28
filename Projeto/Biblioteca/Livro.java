package trabalho1;

import java.util.Random;

// Classe livro: abstração de um livro para os efeitos do sistema.

public class Livro {

    // Atributos.

    private int id;
    private static int geradorId = 0;
    private final int idMax = 1000;
    private String nome;
    private String autor;
    private Genero genero;
    private int edicao;
    private int ano;
    private int livrosDisponiveis;
    private double valorDeEmprestimo;

    // Construtor.

    public Livro(String nome, String autor, Genero genero, int edicao, int ano, int livrosDisponiveis, double valorDeEmprestimo) {
        this.nome = nome;
        this.autor = autor;
        this.genero = genero;
        this.edicao = edicao;
        this.ano = ano;
        this.livrosDisponiveis = livrosDisponiveis;
        this.valorDeEmprestimo = valorDeEmprestimo;
        Random geradorIdRandom = new Random(geradorId); // Aqui, há um processo de geração de id com auxílio da classe Random. Para cada livro gerado, seu id é um número aleatório com seed.
        this.id = geradorIdRandom.nextInt(idMax);
        geradorId++;
    }

    // Setters & Getters.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(short edicao) {
        this.edicao = edicao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(short ano) {
        this.ano = ano;
    }

    public int getLivrosDisponiveis() {
        return livrosDisponiveis;
    }

    public void setLivrosDisponiveis(int livrosDisponiveis) {
        this.livrosDisponiveis = livrosDisponiveis;
    }

    public double getValorDeEmprestimo() {
        return valorDeEmprestimo;
    }

    public void setValorDeEmprestimo(double valorDeEmprestimo) {
        this.valorDeEmprestimo = valorDeEmprestimo;
    }

    // toString de Livro.

    @Override
    public String toString() {
        String out = "Nome: " + getNome() + "(ID: " + getId() + ")\n";
        out = out + "Autor: " + getAutor() + "\n";
        out = out + "Ano: " + getAno() + "\n";
        out = out + "Edição: " + getEdicao() + "\n";
        out = out + getGenero() + "\n";
        out = out + "Livros disponiveis: " + getLivrosDisponiveis() + "\n";

        return out;
    }
}