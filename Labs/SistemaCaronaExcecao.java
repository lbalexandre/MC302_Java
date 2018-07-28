
public class SistemaCaronaExcecao extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SistemaCaronaExcecao(String msg){
        super(msg);
    }
	
	public SistemaCaronaExcecao(String msg, Throwable cause){
		super(msg, cause);
	}

	
}
