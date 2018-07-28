import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GrupoPrivado extends Grupo {
	
	private ArrayList<CaronaPrivada> caronas = new ArrayList<CaronaPrivada>();
	
	public GrupoPrivado(String nome, String descricao, Usuario dono) {
		super(nome, descricao, dono);
	}
	
	public ArrayList<CaronaPrivada> getCaronas() {
		return caronas;
	}

	public void setCaronas(ArrayList<CaronaPrivada> caronas) {
		this.caronas = caronas;
	}
	
	public void adicionarMembros(Usuario usuario) {
		getMembros().add(new GrupoUsuario(getId(), usuario, this));
	}
	
	public void salvarParaArquivo(File arquivo) {
		try {
			FileWriter arq = new FileWriter("grupoPrivado.txt", true);
			BufferedWriter escrever = new BufferedWriter(arq);	
			escrever.write(toString()+"\n");
			escrever.close();
			arq.close();	
		}catch(IOException ex) {
			
		}	
	}
}
