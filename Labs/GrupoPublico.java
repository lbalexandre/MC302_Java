import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GrupoPublico extends Grupo {
	
	private ArrayList<CaronaPublica> caronas = new ArrayList<CaronaPublica>();
	
	public GrupoPublico(String nome, String descricao, Usuario dono) {
		super(nome, descricao, dono);
	}
	
	public ArrayList<CaronaPublica> getCaronas() {
		return caronas;
	}

	public void setCaronas(ArrayList<CaronaPublica> caronas) {
		this.caronas = caronas;
	}

	public void adicionarMembros(Usuario usuario) {
		getMembros().add(new GrupoUsuario(getId(), usuario, this));
	}
	
	public void salvarParaArquivo(File arquivo) {
		try {
			FileWriter arq = new FileWriter("grupoPublico.txt", true);
			BufferedWriter escrever = new BufferedWriter(arq);	
			escrever.write(toString()+"\n");
			escrever.close();
			arq.close();	
		}catch(IOException ex) {
			
		}	
	}
}
