package telas;

import trabalho1.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPerguntaModoTeste extends JFrame {

    // Declaração de componentes.

    private JPanel contentPane;
    private JButton sim, nao;
    private JLabel perguntaModoTeste;
    private Usuario dummy, dummy1, dummy2;


    public TelaPerguntaModoTeste() {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Modo teste");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 150);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Instaciamento de botões e labels.

        sim = new JButton("Sim");
        nao = new JButton("Não");

        perguntaModoTeste = new JLabel("Você deseja inicializar o modo teste do sistema? ");

        ActionListener btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sim) {

                    // Teste das principais funcionalidades do sistema.

                    try {

                        // Geração de usuários teste...

                        Gerenciador.geradorUsuario("Dummy", "123", "dummy@email", "10/10/97", "Unicamp", 176131, true);

                        Gerenciador.geradorUsuario("Dummy1", "321", "dummy1@email", "11/11/98", "", 0, false);

                        // Atribuição de objetos à referências

                        dummy = Biblioteca.usuarios.get(Gerenciador.login("Dummy", "123"));

                        dummy1 = Biblioteca.usuarios.get(Gerenciador.login("Dummy1", "321"));

                        // Testando a exceção de usuário ou senha incorretas...

                        Gerenciador.login("Errado", "Errado");

                    } catch (SistemaExcecao excecao) {
                        JOptionPane.showMessageDialog(TelaPerguntaModoTeste.this, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                    try {

                        dummy2 = new UsuarioAdmin("Dummy2", "132", "12/12/99", "dummy2@email", true);

                        // Cadastrando um livro no acervo de um usuário e no acervo da Biblioteca

                        dummy.cadastrarLivro("Romeu e Julieta", "Shakespere", 1, 3, 1500, 3, 40.0);

                        // Vamos tentar um empréstimo inválido entre dois usuários (o saldo de dummy1 atualmente é zero).

                        dummy1.novoEmprestimo("Romeu e Julieta", dummy, null);

                    } catch (SistemaExcecao excecao1) {
                        JOptionPane.showMessageDialog(TelaPerguntaModoTeste.this, excecao1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                    try {

                        // Tentemos, agora, um empréstimo inválido devido ao nome do livro.

                        dummy1.novoEmprestimo("Julieta e Romeu", dummy, null);

                    } catch (SistemaExcecao excecao2) {
                        JOptionPane.showMessageDialog(TelaPerguntaModoTeste.this, excecao2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                    try {

                        // Por fim, façamos um empréstimo válido. Os dados desse empréstimo serão exibidos pela saída padrão.

                        dummy1.setSaldo(100);

                        Emprestimo emprestimoTeste = dummy1.novoEmprestimo("Romeu e Julieta", dummy, null);

                        System.out.println(emprestimoTeste);

                        // Agora, vamos tentar adicionar um usuário que não existe.

                        dummy1.adicionarAmigo("Gasparzinho");

                    } catch (SistemaExcecao excecao3) {

                        JOptionPane.showMessageDialog(TelaPerguntaModoTeste.this, excecao3.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                    try {

                        // Adicionemos um usuário que existe, agora.

                        dummy1.adicionarAmigo("Dummy");

                        // Vamos mostrar a lista de amigos de dummy1 para verificar se o usuário adicionado realmente está lá.

                        System.out.println(dummy1.getAmigos());

                        // Vamos enviar uma mensagem à outro usuário.

                        dummy1.enviarMensagem("Dummy", "Mensagem de teste!");

                        // Verificando se a mensagem realmente chegou...

                        System.out.println(dummy.getMensagens());

                        // Testemos, agora, um método exclusivo de usuarioEstudante. Como não há nenhum outro usuário do sistema que pertence à Unicamp, o método lançara uma exceção.

                        ((UsuarioEstudante) dummy).buscaUsuarioUniversidade();

                    } catch (SistemaExcecao excecao4) {

                        JOptionPane.showMessageDialog(TelaPerguntaModoTeste.this, excecao4.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                    // Por fim, testemos um método de usuarioAdmin. Vamos banir dummy do sistema.

                    try {

                        ((UsuarioAdmin) dummy2).banirUsuario("Dummy");

                        // Para averiguar se dummy realmente foi banido, vamos imprimir todos os usuários do sistema.

                        System.out.println(Biblioteca.usuarios);

                        // Vamos imprimir todas as instâncias do sistema para averiguar seu estado...

                        Biblioteca bibliotecaTeste = new Biblioteca();

                        System.out.println(bibliotecaTeste.toString());

                        JOptionPane.showMessageDialog(TelaPerguntaModoTeste.this, "Fim do modo teste!", "Fim", JOptionPane.INFORMATION_MESSAGE);

                    } catch (SistemaExcecao excecao5) {
                        JOptionPane.showMessageDialog(TelaPerguntaModoTeste.this, excecao5.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                    new TelaInicial().setVisible(true);

                    TelaPerguntaModoTeste.this.dispose();

                }
            }
        };

        sim.addActionListener(btnListener);
        nao.addActionListener(btnListener);

        contentPane.add(perguntaModoTeste);
        contentPane.add(sim);
        contentPane.add(nao);

    }

}
