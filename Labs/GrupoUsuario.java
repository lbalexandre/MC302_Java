import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GrupoUsuario implements Salvavel {
	
	private int id;
	private Usuario usuario;
	private Grupo grupo;
	
	public GrupoUsuario(int id, Usuario usuario, Grupo grupo) {
		this.id = id;
		this.usuario = usuario;
		this.grupo = grupo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public void salvarParaArquivo(File arquivo) {
		try {
			FileWriter arq = new FileWriter("arquivo.txt", true);
			BufferedWriter escrever = new BufferedWriter(arq);	
			escrever.write(toString()+"\n");
			escrever.close();
			arq.close();	
		}catch(IOException ex) {
			
		}	
	}
}
