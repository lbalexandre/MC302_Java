import java.util.ArrayList;

public abstract class Carona {
	
	private ArrayList<CaronaCaroneiro> caroneiros = new ArrayList<CaronaCaroneiro>();
	private CaronaCaronante caronante;
	private	double longitudeEncontro, latitudeEncontro;
	private double longitudeDestino, latitudeDestino; 
	private String horaDiaEncontro;
	private int ocupacaoMaxima;
	private int assentosDisponiveis;
	private float valor;
	private ArrayList<MetodoPagamento> formaPagAceitas = new ArrayList<MetodoPagamento>();
	
	public Carona(double longitudeEncontro, double latitudeEncontro, double longitudeDestino,
				  double latitudeDestino, String horaDiaEncontro, int ocupacaoMaxima, int assentosDisponiveis, float valor) {

		this.longitudeEncontro = longitudeEncontro;
		this.latitudeEncontro = latitudeEncontro;
		this.longitudeDestino = longitudeDestino;
		this.latitudeDestino = latitudeDestino;
		this.horaDiaEncontro = horaDiaEncontro;
		this.ocupacaoMaxima = ocupacaoMaxima;
		this.assentosDisponiveis = assentosDisponiveis;
		this.valor = valor;
	}

	// -------------------------- SETs e GETs -------------------------------------- //
	
	public ArrayList<CaronaCaroneiro> getCaroneiros() {
		return caroneiros;
	}

	public void setCaroneiros(ArrayList<CaronaCaroneiro> caroneiros) {
		this.caroneiros = caroneiros;
	}

	public CaronaCaronante getCaronante() {
		return caronante;
	}

	public void setCaronante(CaronaCaronante caronante) {
		this.caronante = caronante;
	}

	public double getLongitudeEncontro() {
		return longitudeEncontro;
	}

	public void setLongitudeEncontro(double longitudeEncontro) {
		this.longitudeEncontro = longitudeEncontro;
	}

	public double getLatitudeEncontro() {
		return latitudeEncontro;
	}

	public void setLatitudeEncontro(double latitudeEncontro) {
		this.latitudeEncontro = latitudeEncontro;
	}

	public double getLongitudeDestino() {
		return longitudeDestino;
	}

	public void setLongitudeDestino(double longitudeDestino) {
		this.longitudeDestino = longitudeDestino;
	}

	public double getLatitudeDestino() {
		return latitudeDestino;
	}

	public void setLatitudeDestino(double latitudeDestino) {
		this.latitudeDestino = latitudeDestino;
	}

	public String getHoraDiaEncontro() {
		return horaDiaEncontro;
	}

	public void setHoraDiaEncontro(String horaDiaEncontro) {
		this.horaDiaEncontro = horaDiaEncontro;
	}

	public int getOcupacaoMaxima() {
		return ocupacaoMaxima;
	}

	public void setOcupacaoMaxima(int ocupacaoMaxima) {
		this.ocupacaoMaxima = ocupacaoMaxima;
	}

	public int getAssentosDisponiveis() {
		return assentosDisponiveis;
	}

	public void setAssentosDisponiveis(int assentosDisponiveis) {
		this.assentosDisponiveis = assentosDisponiveis;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public ArrayList<MetodoPagamento> getFormaPagAceitas() {
		return formaPagAceitas;
	}

	public void setFormaPagAceitas(ArrayList<MetodoPagamento> formaPagAceitas) {
		this.formaPagAceitas = formaPagAceitas;
	}
	
	// -------------------------- CARONEIROS -------------------------------------- //
	
	 abstract boolean adicionarCaroneiro(Caroneiro caroneiro);
	 
	 boolean removerCaroneiro(Caroneiro caroneiro) {
		 for(int i=0; i<caroneiros.size(); i++) {
			 if(caroneiros.get(i).getCaroneiro() == caroneiro) {
				 caroneiros.remove(i);
				 return true;
			 }
		 }
		 return false;
	 }
		
	 int verificaOcupacao(){
		 return ocupacaoMaxima - assentosDisponiveis;
	 }

	 boolean caronaVazia() {
		 if(ocupacaoMaxima == assentosDisponiveis)
			 return true;
	else
		return false;
	 }

	// -------------------------- PAGAMENTO -------------------------------------- //
	
	public boolean adicionarFormaPagamento(MetodoPagamento mp) {
		for(int i=0; i<formaPagAceitas.size(); i++) {
			if(formaPagAceitas.get(i) == mp) 
				return false;	// Caso a forma de pagameneto ja exista
		}
		
		if(mp == MetodoPagamento.Gratis) {
			for(int i=0; i<formaPagAceitas.size(); i++) 
				formaPagAceitas.remove(i);
			
			formaPagAceitas.add(mp);
			setValor(0);
			return true;
		}
				
		formaPagAceitas.add(mp);
		
		return true;
	}
	
	public boolean removerFormaPagamento(MetodoPagamento mp) {
		for(int i=0; i<formaPagAceitas.size(); i++) {
			if(formaPagAceitas.get(i) == mp) {
				formaPagAceitas.remove(i);
				return true;
			}
		}
					
		return false;
	}
	
	public boolean checarExistenciaFormaPagamento(MetodoPagamento mp) {
		for(int i=0; i<formaPagAceitas.size(); i++) {
			if(formaPagAceitas.get(i) == mp) 
				return true;
		}
		
		return false;
	}
	
	public boolean caronaGratuita() {
		for(int i=0; i<formaPagAceitas.size(); i++) {
			if(formaPagAceitas.get(i) == MetodoPagamento.Gratis) 
				return true;
		}
		
		return false;	
	}
	
	// -------------------------- AVALIACAO -------------------------------------- //
	
	public boolean atribuirNotaCaroneiro(int idUsuario, float avaliacao) {
		for(int i=0; i<caroneiros.size(); i++) {
			if(caroneiros.get(i).getCaroneiro().getPerfil().getUsuario().getId() == idUsuario) {
				caroneiros.get(i).getCaroneiro().getCarona().get(0).setAvaliacao(avaliacao);
				caroneiros.get(i).getCaroneiro().getPerfil().atualizaAvaliacao();
				return true;
			}		
		}
		return false;
	}
	
	public boolean atribuirNotaCaronante(float avaliacao) {
		if(caronante == null )
			return false;
		else {
			caronante.setAvaliacao(avaliacao);
			caronante.getCaronante().getPerfil().atualizaAvaliacao();
			return true; 
		}
	}
	
	// -------------------------- TO STRING -------------------------------------- //
	
	public String toString(){
		String out =  getCaronante().getCaronante().getPerfil().getUsuario().getNome()+"\n";
		out = out + getCaronante().getCaronante()+"\n";
		out = out + "Local de encontro - Latitude: "+getLatitudeEncontro()+", Longitude: "+getLongitudeEncontro()+"\n";
		out = out + "Local destino - Latitude: "+getLatitudeDestino()+", Longitude: "+getLongitudeDestino()+"\n";
		out = out + "Horario e data: "+getHoraDiaEncontro()+"\n";
		out = out + "Ocupacao maxima: "+getOcupacaoMaxima()+"\n";
		out = out + "Assentos disponiveis: "+getAssentosDisponiveis()+"\n";
		out = out + "Valor: "+getValor()+"\n";
		out = out + "\nCaroneiros\n";
		if(caroneiros.size() < 1)
			out = out + "Ainda nao ha caroneiros\n";
		else {
			for(int i=0; i<caroneiros.size(); i++)
				out = out + caroneiros.get(i).getCaroneiro().getPerfil().getUsuario().getNome()+"\n";
		}
		
		out = out + "\nFormas de pagamento\n";
		for(int i=0; i<formaPagAceitas.size(); i++)
			out = out + formaPagAceitas.get(i)+"\n";
	
		return out;	
	}
}
