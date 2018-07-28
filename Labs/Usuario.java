import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Usuario implements Salvavel {
	
	private int id;
	private static int geradorId=0;
	private String nome;
	private String email;
	private String senha;
	private boolean status;
	private ArrayList<GrupoUsuario> grupos = new ArrayList<GrupoUsuario>();
	private Perfil perfil;
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		geradorId++;
		id = geradorId;
	}

	// -------------------------- GETs e SETs -------------------------------------- //
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public ArrayList<GrupoUsuario> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<GrupoUsuario> grupos) {
		this.grupos = grupos;
	}

	// -------------------------- TO STRING -------------------------------------- //
	public String toString() {
		String out = "Informações de Usuário\n";
		out = out + getNome()+" (ID: "+getId()+")\n";
		out = out + "Email: "+getEmail()+"\n";
		out = out + "Senha: "+getSenha()+"\n";	
		if(status)
			out = out + "Status: online\n";
		else
			out = out + "Status: offline\n";
		out = out + getPerfil();
	
		out = out + "Grupos\n";
		for(int i=0; i<grupos.size(); i++)
			out = out + grupos.get(i).getGrupo().getNome()+"\n";
		
		return out;
	}
	
	// -------------------------- METODOS -------------------------------------- //
	
	public void adicionarGrupo(GrupoPublico grupo) throws SistemaCaronaExcecao {
		if(grupo !=  null ) {
			grupo.adicionarMembros(this);
			this.getGrupos().add(new GrupoUsuario(id, this, grupo));
		}else
			throw new SistemaCaronaExcecao("O grupo não é válido");
	}
	
	public void adicionarUsuarioAUmGrupo(GrupoPrivado gprivado, Usuario novoMembro) throws SistemaCaronaExcecao {
		if(gprivado.getDono() == this) {
			gprivado.adicionarMembros(novoMembro);
			novoMembro.getGrupos().add(new GrupoUsuario(id, novoMembro, gprivado));
		}else
			throw new SistemaCaronaExcecao ("Não foi possível adicionar o membro. O grupo é Privado.");	
	}
	
	public void removerGrupo(Grupo grupo) throws SistemaCaronaExcecao {
		if(grupo.checarPresencaUsuario(this)) {
			if(grupo.getDono() != this) {
				for(int i=0; i<grupo.getMembros().size(); i++) {
					if(grupo.getMembros().get(i).getUsuario() == this) {
						grupo.getMembros().remove(i);
						break;
					}
				}
			}else
				throw new SistemaCaronaExcecao("Não é possivel sair de um grupo nao qual voce é dono.");
			
		}else
			throw new SistemaCaronaExcecao("O usuário nao pertence ao  grupo.");	
	}
						
	public void removerGrupo(int idGrupo) throws SistemaCaronaExcecao {
		
		Grupo grupo = null;
		for(int i=0; i<grupos.size(); i++) {
			if(grupos.get(i).getGrupo().getId() == idGrupo) { 
				grupo = grupos.get(i).getGrupo();
			}
		}
		
		if(grupo instanceof GrupoPrivado) {
			if(grupo.getDono() != this) {
				for(int i=0; i<grupo.getMembros().size(); i++) {
					if(grupo.getMembros().get(i).getUsuario() == this) {
						grupo.getMembros().remove(i);
						break;
					}
				}
			}else
				throw new SistemaCaronaExcecao("Nao foi possivel possivel remover o grupo");
		}else {
			for(int i=0; i<grupo.getMembros().size(); i++) {
				if(grupo.getMembros().get(i).getUsuario() == this) {
					grupo.getMembros().remove(i);
					break;
				}
			}
			
		}	
	}
	
	public void atualizarGrupo(Usuario dono, int idGrupo, String nome, String descricao) {
		for(int i=0; i<grupos.size(); i++) {
			if(grupos.get(i).getId() == idGrupo) {
				if(grupos.get(i).getGrupo().getDono() == dono) {
					grupos.get(i).getGrupo().setNome(nome);
					grupos.get(i).getGrupo().setDescricao(descricao);
				}
					
			}		
		}
	}
	
	public void atualizarGrupo(Usuario dono, int idGrupo, String descricao) {
		for(int i=0; i<grupos.size(); i++) {
			if(grupos.get(i).getId() == idGrupo) {
				if(grupos.get(i).getGrupo().getDono() == dono) 
					grupos.get(i).getGrupo().setDescricao(descricao);
			}
		}
	}
	
	public GrupoPublico criarGrupoPublico(String nome, String descricao) {
		GrupoPublico gpublico = new GrupoPublico(nome, descricao, this);
		gpublico.adicionarMembros(this);
		this.getGrupos().add(new GrupoUsuario(id, this, gpublico));
		
		return gpublico;
	}
	
	public GrupoPrivado criarGrupoPrivado(String nome, String descricao) {
		GrupoPrivado gprivado = new GrupoPrivado(nome, descricao, this);
		gprivado.adicionarMembros(this);
		this.getGrupos().add(new GrupoUsuario(id, this, gprivado));
		
		return gprivado;
	}


	public void salvarParaArquivo(File arquivo) {
		
		try {
			FileWriter arq = new FileWriter("usuarios.txt", true);
			BufferedWriter escrever = new BufferedWriter(arq);	
			escrever.write(toString()+"\n");
			escrever.close();
			arq.close();	
		}catch(IOException ex) {
			
		}	
	}	
}


