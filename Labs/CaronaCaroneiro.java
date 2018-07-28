
public class CaronaCaroneiro {

	private Caroneiro caroneiro;
	private Carona carona;
	private float avaliacao;
	
	public CaronaCaroneiro(Caroneiro caroneiro, Carona carona) {
		this.caroneiro = caroneiro;
		this.carona = carona;
	}

	public Caroneiro getCaroneiro() {
		return caroneiro;
	}

	public void setCaroneiro(Caroneiro caroneiro) {
		this.caroneiro = caroneiro;
	}

	public Carona getCarona() {
		return carona;
	}

	public void setCarona(Carona carona) {
		this.carona = carona;
	}

	public float getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(float avaliacao) {
		this.avaliacao = avaliacao;
	}	
}
