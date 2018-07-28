import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Perfil implements Comparable<Perfil>, Salvavel{

	private char sexo;
	private final String dataNascimento;
	private String cidade;
	private String estado;
	private String telefone;
	private boolean fumante;
	private float avaliacao;
	private Caroneiro caroneiro;
	private Caronante caronante;
	private Usuario usuario;

	public Perfil(char sexo, String dataNascimento, String cidade, String estado, String telefone, boolean fumante) {
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone = telefone;
		this.fumante = fumante;
	}

	// --------------------------  GETs e SETs -------------------------------------- //
	
	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean getFumante() {
		return fumante;
	}

	public void setFumante(boolean fumante) {
		this.fumante = fumante;
	}

	public float getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(float avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Caroneiro getCaroneiro() {
		return caroneiro;
	}

	public void setCaroneiro(Caroneiro caroneiro) {
		this.caroneiro = caroneiro;
	}

	public Caronante getCaronante() {
		return caronante;
	}

	public void setCaronante(Caronante caronante) {
		this.caronante = caronante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// -------------------------- TO STRING -------------------------------------- //
	
	public String toString() {
		String out = "Informações de Perfil\n";
		out = out + "Sexo: " + getSexo() + "\n";
		out = out + "Data de nascimento: " + getDataNascimento() + "\n";
		out = out + "Cidade: " + getCidade() + "\n";
		out = out + "Estado: " + getEstado() + "\n";
		out = out + "Telefone: " + getTelefone() + "\n";
		if (fumante)
			out = out + "Fumante: sim\n";
		else
			out = out + "Fumante: nao\n";
		out = out + "Avaliacao: " + getAvaliacao() + "\n";
		out = out + getCaroneiro() + "\n";
		out = out + getCaronante() + "\n";

		return out;
	}
	
	// -------------------------- METODOS -------------------------------------- //
	
	public void atualizaAvaliacao() {
		
		float soma=0;
		for(int i=0; i<caroneiro.getCarona().size(); i++)
			soma = soma + caroneiro.getCarona().get(i).getAvaliacao();
		
		for(int i=0; i<caronante.getCarona().size(); i++)
			soma = soma + caronante.getCarona().get(i).getAvaliacao();
			
			
		setAvaliacao(soma / (caronante.getCarona().size() + caroneiro.getCarona().size()));
	}
	
	
	public int compareTo(Perfil outroPerfil) {
		if(this.avaliacao < outroPerfil.avaliacao)
			return -1;
		if(this.avaliacao > outroPerfil.avaliacao)
			return 1;

		return 0;
	}
	
	public void salvarParaArquivo(File arquivo) {
		try {
			FileWriter arq = new FileWriter("perfils.txt", true);
			BufferedWriter escrever = new BufferedWriter(arq);	
			escrever.write(toString()+"\n");
			escrever.close();
			arq.close();	
		}catch(IOException ex) {
			
		}	
	}
}
