package trabalho1;

/* Classe Gerenciador: Contém métodos estáticos que asseguram o funcionamento do sistema. É responsável por métodos estáticos de criação de usuários (geradorUsuario),
   verificação de elementos no sistema (métodos checaXXXX) e login. */

public class Gerenciador {

    // Metodo geradorUsuario: Gera um um objeto Usuario ou UsuarioEstudante com os dados fornecidos pela interface gráfica.

    public static void geradorUsuario(String nome, String senha, String email, String data, String instituicao, int ra, boolean estudante) {   
        if (estudante) {
            UsuarioEstudante novoUsuarioEstudante = new UsuarioEstudante(nome, senha, data, email, true,
                    instituicao, ra);
        } else {
            UsuarioComum novoUsuario = new UsuarioComum(nome, senha, data, email, true);
        }
    }

    /* Método checaUsuario: Retorna o indice de um usuario caso ele exista (nome consta na AL usuarios da Biblioteca).
    Caso contrário, lança SistemaExcecao */

    public static int checaUsuario(String nome) throws SistemaExcecao {
        for (int i = 0; i < Biblioteca.usuarios.size(); i++) {
            if ((Biblioteca.usuarios.get(i)).getNome().equals(nome))
            	return i;
        }

        throw new SistemaExcecao("Usuário não existe!");
    }

    /* Método checaExececao: Lança exceções se o usuário, durante a execução do método adicionarAmigo,
     tentar se adicionar ou adicionar um usuário que já está em sua lista de amigos.*/

    public static void checaExcecao(Usuario usuarioAtual, Usuario usuarioAlvo) throws SistemaExcecao {
        for (int i = 0; i < usuarioAtual.getAmigos().size(); i++) {
            if (usuarioAtual.getAmigos().get(i).getNome().equals(usuarioAlvo.getNome())) {
            	throw new SistemaExcecao("Você já possui esse usuário em sua lista de amigos!");
            }
        }
        
        if (usuarioAtual.getNome().equals(usuarioAlvo.getNome())) {
        	throw new SistemaExcecao("Você não pode se adicionar em sua lista de amigos!");
        }
    }

    /* Método login: Retorna o índice do usuário na AL usuarios da Biblioteca caso encontrado
      (nome e senha batem com os dados na AL), e lança SistemaExcecao caso contrário. */

    public static int login(String nome, String senha) throws SistemaExcecao {
    	
        for (int i = 0; i < Biblioteca.usuarios.size(); i++) {
            if (Biblioteca.usuarios.get(i).getNome().equals(nome) && Biblioteca.usuarios.get(i).getSenha().equals(senha))
                return i;
        }
        throw new SistemaExcecao("Usuário ou senha incorretos!");
    }

    /* Método pagamentoVálido: "Checa" se as informações de pagamento dadas são válidas. Se não houver
     16 caracteres na string (como em um número de cartão da vida real), lança SistemaExcecao. */

    public static void pagamentoValido(String infoPagamento) throws SistemaExcecao {
        if(!(infoPagamento.length() == 2)) throw new SistemaExcecao("Cartão de crédito inválido! Por favor, insira um método de pagamento válido");
    }

    /* Método checaCupom: Verifica se o código de Cupom dado pelo usuário é um código válido
     - isto é, existe um cupom com esse código e ele não foi utilizado.
     Retorna o índice do cupom na AL cupons da Biblioteca caso o cupom seja válido e lança SistemaExcecao caso contrário.*/

    public static int checaCupom(String codigo) throws SistemaExcecao {
        for (int i = 0; i < Biblioteca.cupons.size(); i++) {
            if (Biblioteca.cupons.get(i).getCodigo().equals(codigo)) {
                if (!Biblioteca.cupons.get(i).getFoiUsado()) {
                    return i;
                } else {
                    throw new SistemaExcecao("Cupom já utilizado!");
                }
            }
        }
       throw new SistemaExcecao("Código de cupom inválido (não existe)!");
    }

}