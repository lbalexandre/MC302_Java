import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Caroneiro implements Salvavel {
	
	private String cartaoDeCredito;
	private ArrayList<CaronaCaroneiro> caronas = new ArrayList<CaronaCaroneiro>();
	private Perfil perfil;
	
	public Caroneiro(String cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;	
	}
	
	// -------------------------- SETs e GETs -------------------------------------- //
	
	public String getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public void setCartaoDeCredito(String cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public ArrayList<CaronaCaroneiro> getCarona() {
		return caronas;
	}

	public void setCarona(ArrayList<CaronaCaroneiro> caronas) {
		this.caronas = caronas;
	}
	
	// -------------------------- TO STRING -------------------------------------- //

	public String toString() {
		String out = "Informações de Caroneiro\n";
		out = out + "Cartão de credito: "+getCartaoDeCredito()+"\n";
	
		return out;
	}
	
	// -------------------------- METODOS -------------------------------------- //
	
	public boolean pedirCarona(Carona carona) {
		if(carona.getAssentosDisponiveis() > 0) {
			if(carona.adicionarCaroneiro(this))
				return true;
			else
				return false;
		}
		return false;
	}
	
	public void salvarParaArquivo(File arquivo) {
		try {
			FileWriter arq = new FileWriter("caroneiro.txt", true);
			BufferedWriter escrever = new BufferedWriter(arq);	
			escrever.write(toString()+"\n");
			escrever.close();
			arq.close();	
		}catch(IOException ex) {
			
		}	
	}
}