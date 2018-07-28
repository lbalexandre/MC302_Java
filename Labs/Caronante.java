import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Caronante implements Salvavel {
	
	private int tempoHabilitacao;
	private String generoMusicalFavorito;
	private String placaVeiculo;
	private String carteiraMotorista;
	private String marcaVeiculo;
	private String modeloVeiculo;
	private ArrayList<CaronaCaronante> carona = new  ArrayList<CaronaCaronante>();
	private Perfil perfil;
	
	public Caronante(int tempoHabilitacao, String generoMusicalFavorito, String placaVeiculo, String carteiraMotorista,
		String marcaVeiculo, String modeloVeiculo) {
		this.tempoHabilitacao = tempoHabilitacao;
		this.generoMusicalFavorito = generoMusicalFavorito;
		this.placaVeiculo = placaVeiculo;
		this.carteiraMotorista = carteiraMotorista;
		this.marcaVeiculo = marcaVeiculo;
		this.modeloVeiculo = modeloVeiculo;
	}

	// --------------------------  GETs e SETs -------------------------------------- //
	
	public int getTempoHabilitacao() {
		return tempoHabilitacao;
	}
	
	public void setTempoHabilitacao(int tempoHabilitacao) {
		this.tempoHabilitacao = tempoHabilitacao;
	}
	
	public String getGeneroMusicalFavorito() {
		return generoMusicalFavorito;
	}
	
	public void setGeneroMusicalFavorito(String generoMusicalFavorito) {
		this.generoMusicalFavorito = generoMusicalFavorito;
	}
	
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	
	public String getCarteiraMotorista() {
		return carteiraMotorista;
	}
	
	public String getMarcaVeiculo() {
		return marcaVeiculo;
	}
	
	public void setMarcaVeiculo(String marcaVeiculo) {
		this.marcaVeiculo = marcaVeiculo;;
	}
	
	public String getModeloVeiculo() {
		return modeloVeiculo;
	}
	
	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void setCarteiraMotorista(String carteiraMotorista) {
		this.carteiraMotorista = carteiraMotorista;
	}
	
	public ArrayList<CaronaCaronante> getCarona() {
		return carona;
	}

	public void setCarona(ArrayList<CaronaCaronante> carona) {
		this.carona = carona;
	}

	// -------------------------- TO STRING -------------------------------------- //
	
	public String toString() {
		String out = "Informações de Caronante\n";
		out = out + "Tempo de habilitacao: "+getTempoHabilitacao()+" anos\n";
		out =  out + "Genero musical favorito: "+getGeneroMusicalFavorito()+"\n";
		out = out + "Placa do veiculo: "+getPlacaVeiculo()+"\n";
		out = out + "Carteira de motorista: "+getCarteiraMotorista()+"\n";
		out = out + "Marca do veiculo: "+getMarcaVeiculo()+"\n";
		out = out + "Modelo do veiculo: "+getModeloVeiculo()+"\n";
		
		return out;
	}	
		
	// -------------------------- METODOS -------------------------------------- //
	
	public CaronaPublica oferecerCaronaPublica(double lonE, double latE, double lonD,
								 double latD, String horaDia, int ocupMax, int assentosDisp, float valor) { 
		
		CaronaPublica carona = new CaronaPublica(lonE, latE, lonD, latD, horaDia, ocupMax, assentosDisp, valor);
		CaronaCaronante cc = new CaronaCaronante(this, carona);
		this.getCarona().add(cc);
		carona.setCaronante(cc);
		return carona;
	}
	
	public CaronaPrivada oferecerCaronaPrivada(double lonE, double latE, double lonD,
			 double latD, String horaDia, int ocupMax, int assentosDisp, float valor) { 
		
		CaronaPrivada carona = new CaronaPrivada(lonE, latE, lonD, latD, horaDia, ocupMax, assentosDisp, valor);
		CaronaCaronante cc = new CaronaCaronante(this, carona);
		this.getCarona().add(cc);
		carona.setCaronante(cc);
		return carona;
}
	
	
	public void salvarParaArquivo(File arquivo) {
		try {
			FileWriter arq = new FileWriter("caronante.txt", true);
			BufferedWriter escrever = new BufferedWriter(arq);	
			escrever.write(toString()+"\n");
			escrever.close();
			arq.close();	
		}catch(IOException ex) {
			
		}	
	}
}