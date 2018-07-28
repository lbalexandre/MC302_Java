
public class CaronaCaronante {
	
	private Caronante caronante;
	private Carona carona;
	private float avaliacao;
	
	public CaronaCaronante(Caronante caronante, Carona carona) {
		this.caronante = caronante;
		this.carona = carona;
	}
	
	public Caronante getCaronante() {
		return caronante;
	}
	
	public void setCaronante(Caronante caronante) {
		this.caronante = caronante;
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
