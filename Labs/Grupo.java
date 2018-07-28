import java.io.File;
import java.util.ArrayList;

public abstract class Grupo implements Salvavel{
	
	private int id;
	private	String nome;
	private String descricao;
	private Usuario dono;
	private static int geradorId;
	private ArrayList<GrupoUsuario> membros = new ArrayList<GrupoUsuario>();

	public Grupo(String nome, String descricao, Usuario dono) {
		this.nome = nome;
		this.descricao = descricao;
		this.dono = dono;
		geradorId++;
		id = geradorId;
	}

	// -------------------------- SETs e GETs -------------------------------------- //
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public static int getGeradorId() {
		return geradorId;
	}

	public static void setGeradorId(int geradorId) {
		Grupo.geradorId = geradorId;
	}

	public ArrayList<GrupoUsuario> getMembros() {
		return membros;
	}

	public void setMembros(ArrayList<GrupoUsuario> membros) {
		this.membros = membros;
	}
	
	// -------------------------- METODOS -------------------------------------- //
	
	public abstract void adicionarMembros(Usuario usuario);
		
	public void alterarDono(Usuario dono, Usuario novoDono) {
		if(getDono() == dono)
			setDono(novoDono);
	}
		
	public boolean checarPresencaUsuario(Usuario usuario) throws SistemaCaronaExcecao {
		for(int i=0; i<membros.size(); i++) {
			if(membros.get(i).getUsuario() == usuario)
				return true;
		}
		throw new SistemaCaronaExcecao("O usuário nao pertence ao  grupo.");	
	}
	
	public abstract void salvarParaArquivo(File arquivo);
	
	// -------------------------- TO STRING -------------------------------------- //
	
	public String toString() {
		String out = getNome()+"(ID: "+getId()+")\n";
		out = out + "Dono: "+ getDono().getNome()+"\n";
		out = out + "Descricao: " +getDescricao()+"\n";
		out = out + "Membros:\n";
		for(int i=0; i<membros.size(); i++)
			out = out + membros.get(i).getUsuario().getNome()+"\n";
		
		return out;
	}
}
