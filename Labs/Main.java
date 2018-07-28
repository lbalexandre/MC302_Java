
/*
Parte I: Sobre interfaces e classes abstratas, responda as seguintes questões:

1. É possível uma interface herdar de uma classe abstrata? É possível uma classe abstrata implementar
uma interface?
	Uma interface não pode herdar de uma class abstrata, mas uma classe abtratra pode implementar uma interface

2. Quais as principais diferenças entre classes abstratas e interfaces?
	As classes abstratas tem pelo menos um método abstrato, ou seja um método que não tem corpo, já as interfaces
nenhum método tem corpo e são implicitamente abstratas e publicas. As classe abstratas não pode conter um contrutor, 
já, as interfaces, não.

3. O que acontece se uma classe implementar 2 interfaces diferentes, mas que tem um método com o
mesmo nome e assinatura?
	Não há problema, a classe pode utilizar qualquer um dos metodos.

4. No caso geral, não é possível implementar métodos em interfaces. Mas a partir do Java 8, é possível
um de dois modificadores aos métodos de uma interface para que seja possível implementá-los.
Quais são esses modificadores?
	Modificador default.

Parte II: Sobre arquivos e exceção, responda as seguintes questões:

1. Qual a diferença na leitura através de um objeto BufferedInputStream para um InputStream
(incluindo suas subclasses)?
	Esta classe abstrata é a superclasse de todas as classes que representam um fluxo de
entrada de bytes. As aplicações que precisam definir uma subclasse de InputStream devem s
empre fornecer um método que retorne o próximo byte de entrada
	Um BufferedInputStream adiciona funcionalidade a outro fluxo de entrada, ou seja, a capacidade de 
armazenar em buffer a entrada e oferecer suporte aos métodos de marca e redefinição.  Quando o
BufferedInputStream é criado,  uma matriz de buffer interna é criada. Como os bytes do
fluxo são lidos ou ignorados, o buffer interno é reabastecido conforme necessário a partir do
fluxo de entrada contido, muitos bytes de cada vez.

2. A classe DataOutputStream tem alguns métodos para escrita, com as assinaturas descritas abaixo.
Esses métodos dão throw em exceções do tipo IOException. Por que esse tipo de exceção é
jogada? Em que contextos faz sentido jogar uma exceção do tipo IOException?
public final void writeInt(int i) throws IOException;
public final void writeFloat(float f) throws IOException;
public final void writeByte(int b) throws IOException;
public final void writeChar(int i) throws IOException;
...;
	Uma IOException é uma exceção de entrada ou saída, ou seja,  sssa exceção ocorre quando
uma operação de E/S falha por algum motivo.

3. Explique a utilidade da interface Serializable, como ela pode ser usada com arquivos e cite
duas exceções que métodos dessa interface jogam.
	Ela dá capacidade da classe produzir um formato em que os dados do objeto seja usado de 
forma externa ao código, em geral ele é persistido  em alguma forma de armazenamento
temporário ou permanente ou é transmitido para outro recurso.
java.io.NotSerializableException
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
	
	public static void main(String[] args) throws SistemaCaronaExcecao {
		
		// #1
		
		ArrayList<Usuario> u = new ArrayList<Usuario>();
		ArrayList<Perfil> perfil = new ArrayList<Perfil>();
		ArrayList<Caronante> caronante = new ArrayList<Caronante>();
		ArrayList<Caroneiro> caroneiro = new ArrayList<Caroneiro>();
		
		// Usuarios
		u.add(new Usuario("Marta", "marta@gmail.com", "senha#123#"));
		u.add(new Usuario("Douglas", "douglas@gmail.com", "senha#123#"));
		u.add(new Usuario("Guilherme", "guilherme@gmail.com", "senha#123#"));
		u.add(new Usuario("Lais", "lais@gmail.com", "senha#123#"));
		u.add(new Usuario("Wesley", "wesley@gmail.com", "senha#123#"));
		
		// Perfis
		perfil.add(new Perfil('F', "26/04/1997", "SJC", "SP", "(12)99823-2452", false));
		perfil.add(new Perfil('M', "09/12/1996", "Riberao Preto", "SP", "(12)93343-2452", false));
		perfil.add(new Perfil('M', "22/01/1996", "SJC", "SP", "(12)92346-2452", false));
		perfil.add(new Perfil('F', "17/06/1997", "Campinas", "SP", "(12)99823-6446", false));
		perfil.add(new Perfil('M', "31/03/1997", "SJC", "SP", "(12)99235-2432", true));
		
		// Caronante
		caronante.add(new Caronante(1, "Pagode", "CHE-1234", "6DSDS32", "VW", "Parati"));
		caronante.add(new Caronante(3, "Funk", "SFW-7421", "639232", "Hyundai", "HB20"));
		caronante.add(new Caronante(4, "Eletronica", "DDE-4321", "336232", "Fiat", "Palio"));
		caronante.add(new Caronante(2, "Funknejo", "STT-7353", "192732", "Chevolet", "Prisma"));
		caronante.add(new Caronante(2, "Rock", "RTF-2345", "549232", "Fiat", "Uno"));
		
		// Caroneiro
		caroneiro.add(new Caroneiro("1234 4356 7654 0988"));
		caroneiro.add(new Caroneiro("4545 4365 7852 0668"));
		caroneiro.add(new Caroneiro("1889 4569 3357 0656"));
		caroneiro.add(new Caroneiro("3560 0876 7054 8878"));
		caroneiro.add(new Caroneiro("1544 4357 5551 7688"));
		
		// Relações
		for(int i=0; i<u.size(); i++) {
			u.get(i).setPerfil(perfil.get(i));
			perfil.get(i).setUsuario(u.get(i));
			perfil.get(i).setCaronante(caronante.get(i));
			perfil.get(i).setCaroneiro(caroneiro.get(i));
			caronante.get(i).setPerfil(perfil.get(i));
			caroneiro.get(i).setPerfil(perfil.get(i));		
		}
		
		// #2
		GrupoPublico gpu = u.get(0).criarGrupoPublico("gpu", "Grupo público");
		
		// #3
		GrupoPrivado EC017 = u.get(0).criarGrupoPrivado("EC017", "Grupo privado");
		
		// #4
		System.out.println("(#4)");
		System.out.println(EC017);
		try {
			u.get(0).adicionarUsuarioAUmGrupo(EC017, u.get(1));
			u.get(0).adicionarUsuarioAUmGrupo(EC017, u.get(2));
		}catch(SistemaCaronaExcecao e){
			System.out.println(e.getMessage());
		}
		System.out.println(EC017);
		
		// #5
		System.out.println("(#5)");
		try {
			u.get(2).adicionarUsuarioAUmGrupo(EC017, u.get(4));
		}catch(SistemaCaronaExcecao e){
			System.out.println(e.getMessage()+"\n");
		}
		System.out.println(EC017);
		
		// #6
		System.out.println("(#6)");
		u.get(2).adicionarGrupo(gpu);
		u.get(3).adicionarGrupo(gpu);
		u.get(4).adicionarGrupo(gpu);
		System.out.println(gpu);
		
		// #7
		CaronaPublica carona1 = u.get(3).getPerfil().getCaronante().oferecerCaronaPublica( 22.3, 23.4, 25.1, 26.7, "15h-15/06/2018", 3, 3, 30);
		gpu.getCaronas().add((CaronaPublica)carona1);
		carona1.getGrupos().add(gpu);
		
		// #8
		System.out.println("(#8)");
		try {
			u.get(0).removerGrupo(gpu);	
		}catch(SistemaCaronaExcecao e) {
			System.out.println(e.getMessage()+"\n");
		}
		System.out.println(gpu);

		// #9
		CaronaPrivada carona2 = u.get(2).getPerfil().getCaronante().oferecerCaronaPrivada(54.3, 56.7, 25.8, 58.7, "20h30-19/06/2018", 3, 3, 50);
		EC017.getCaronas().add((CaronaPrivada)carona2);
		carona2.getGrupos().add(EC017);

		// #10
		CaronaPrivada carona3 = u.get(4).getPerfil().getCaronante().oferecerCaronaPrivada(42.0, 11.0, 43.1, 13.7, "08h-17/06/2018", 3, 3, 40);
		EC017.getCaronas().add((CaronaPrivada)carona3);
		carona3.getGrupos().add(EC017);
		
		// #11
		System.out.println("(#11)");
		if(u.get(0).getPerfil().getCaroneiro().pedirCarona(carona2))
			System.out.println("Caroneiro adicionado.");
		else
			System.out.println("Não foi possivel adicinar o caroneiro.");
		
		if(u.get(1).getPerfil().getCaroneiro().pedirCarona(carona2))
			System.out.println("Caroneiro adicionado.");
		else
			System.out.println("Não foi possivel adicinar o caroneiro.");
		
		if(u.get(3).getPerfil().getCaroneiro().pedirCarona(carona2))
			System.out.println("Caroneiro adicionado.");
		else
			System.out.println("Não foi possivel adicinar o caroneiro.");
		
		System.out.println(carona2);
		
		// #12
		ArrayList<Perfil> lista = new ArrayList<Perfil>();
		for(int i=0; i<3; i++) 
			lista.add(perfil.get(i));
		
		// #13
		u.get(0).getPerfil().getCaroneiro().getCarona().get(0).getCarona().atribuirNotaCaronante(5);
		u.get(1).getPerfil().getCaroneiro().getCarona().get(0).getCarona().atribuirNotaCaronante(5);
		u.get(2).getPerfil().getCaronante().getCarona().get(0).getCarona().atribuirNotaCaroneiro(u.get(0).getId(), 4);
		u.get(2).getPerfil().getCaronante().getCarona().get(0).getCarona().atribuirNotaCaroneiro(u.get(1).getId(), 3);
		
		// #14
		Collections.sort (lista);
		
		// #15
		System.out.println("(#15)");
		for(int i=0; i<u.size(); i++) 
			System.out.println(u.get(i));
				
		System.out.println(gpu);
		System.out.println(EC017);
		System.out.println(lista);
				
		// #16
		File arquivo1 = new File ("usuarios.txt");
		for(int i=0; i<u.size(); i++) 
			u.get(i).salvarParaArquivo(arquivo1);
				
		File arquivo2 = new File ("perfils.txt");
		for(int i=0; i<perfil.size(); i++) 
			perfil.get(i).salvarParaArquivo(arquivo2);
				
		File arquivo3 = new File ("caronante.txt");
		for(int i=0; i<caronante.size(); i++) 
			caronante.get(i).salvarParaArquivo(arquivo3);
				
		File arquivo4 = new File ("caroneiro.txt");
		for(int i=0; i<caroneiro.size(); i++) 
			caroneiro.get(i).salvarParaArquivo(arquivo4);
				
		File arquivo5 = new File ("grupoPublico.txt");
		gpu.salvarParaArquivo(arquivo5);
				
		File arquivo6 = new File ("grupoPrivado.txt");
		EC017.salvarParaArquivo(arquivo6);	
	}	
}	
			
		
		
		

		
