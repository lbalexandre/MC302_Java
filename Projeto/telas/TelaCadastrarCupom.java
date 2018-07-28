package telas;

import trabalho1.Gerenciador;
import trabalho1.SistemaExcecao;
import trabalho1.UsuarioAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastrarCupom extends JFrame {

    private JPanel contentPane;
    private JLabel perguntaCodigo, perguntaValor;
    private JTextField codigo, valor;
    private JButton botaoConfirmar;


    public TelaCadastrarCupom(UsuarioAdmin admin) {

        // Definições de tamanho e criação do frame e painel.

        setTitle("Cadastro de cupom");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new WrapLayout(WrapLayout.CENTER, 0, 10));
        setContentPane(contentPane);

        // Labels de pergunta e seus JTextFields correspondentes (respostas).

        perguntaCodigo = new JLabel("Digite o código do cupom a ser cadastrado: ");
        codigo = new JTextField("", 25);

        perguntaValor = new JLabel("Digite o valor (em porcentagem) do desconto: ");
        valor = new JTextField("", 25);

        // Botão de confirmação.

        botaoConfirmar = new JButton("Confirmar");

        // ActionListener do botão de confirmação.

        botaoConfirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Gerenciador.checaCupom(codigo.getText());

                    // Se nenhuma exceção foi lançada, é porque um cupom com esse código já existe no sistema

                    JOptionPane.showMessageDialog(TelaCadastrarCupom.this, "Cupom já existe no sistema!", "Erro", JOptionPane.ERROR_MESSAGE);

                } catch (SistemaExcecao excecao) { // Caso capturemos uma exceção (neste caso, uma exceção de cupom inválido) é porque não existe no sistema um cupom com esse código, e portanto, podemos cadastrá-lo

                    if (excecao.getMessage().equals("Código de cupom inválido (não existe)!")) {

                        admin.cadastrarCupom(codigo.getText(), Double.parseDouble(valor.getText()));

                        JOptionPane.showMessageDialog(TelaCadastrarCupom.this, "Cupom cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        TelaCadastrarCupom.this.dispose();
                    }
                }
            }
        });

        contentPane.add(perguntaCodigo);
        contentPane.add(codigo);
        contentPane.add(perguntaValor);
        contentPane.add(valor);
        contentPane.add(botaoConfirmar);

    }

}
