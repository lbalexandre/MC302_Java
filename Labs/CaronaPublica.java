import java.util.ArrayList;

public class CaronaPublica extends Carona{
	
	private ArrayList<GrupoPublico> grupos = new ArrayList<GrupoPublico>();

	public CaronaPublica(double longitudeEncontro, double latitudeEncontro,
			double longitudeDestino, double latitudeDestino, String horaDiaEncontro, int ocupacaoMaxima,
			int assentosDisponiveis, float valor) {
		
		super(longitudeEncontro, latitudeEncontro, longitudeDestino, latitudeDestino, horaDiaEncontro,
				ocupacaoMaxima, assentosDisponiveis, valor);	
	}
	
	public ArrayList<GrupoPublico> getGrupos() {
		return grupos;
	}
	
	public void setGrupos(ArrayList<GrupoPublico> grupos) {
		this.grupos = grupos;
	}
	
	boolean adicionarCaroneiro(Caroneiro caroneiro) {
		if(grupos.size() == 0) {
			getCaroneiros().add(new CaronaCaroneiro(caroneiro, this));
			caroneiro.getCarona().add(new CaronaCaroneiro(caroneiro, this));
			setAssentosDisponiveis(getAssentosDisponiveis() - 1);
			return true;
		}else {
			for(int i= 0; i<grupos.size(); i++) {
				try {
					if(grupos.get(i).checarPresencaUsuario(caroneiro.getPerfil().getUsuario())) {
						getCaroneiros().add(new CaronaCaroneiro(caroneiro, this));
						caroneiro.getCarona().add(new CaronaCaroneiro(caroneiro, this));
						setAssentosDisponiveis(getAssentosDisponiveis() - 1);
						return true;
					}
				} catch (SistemaCaronaExcecao e) {
					
				}
			}		
			return false;
		}
	}
		
	public boolean adicionarGrupo(GrupoPublico grupo) {
		for(int i=0; i<grupo.getMembros().size(); i++) {
			if(grupo.getMembros().get(i).getUsuario() == getCaronante().getCaronante().getPerfil().getUsuario()) {
				grupos.add(grupo);
				return true;	
			}
		}
		return false;
	}	
}
	
	
	
	

