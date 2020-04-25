package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sqlite.Conexao;;

public class FuncionariosEditar extends JPanel {

	String searchFun;
	JLabel lblTitulo, lblNome, lblSobrenome, lblDataNascimento, lblEmail, lblCargo, lblSalario, labelId;
	JTextField txtNome, txtSobrenome, txtEmail, txtCargo, txtId;
	JFormattedTextField ftxtDataNascimento, ftxtSalario;
	JButton btnGravar;
	ImageIcon imgSobrescrever = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\overwrite01.png");
	Funcionario funcionarioEditado = new Funcionario();

	public FuncionariosEditar(String pSearchFun) {
		this.searchFun = pSearchFun;
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	private void criarComponentes() {
		setLayout(null);

		String textoLabel = "Editar Funcionario ";
		lblTitulo = new JLabel(textoLabel, JLabel.CENTER);
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.PLAIN, 20));
		lblNome = new JLabel("Nome:", JLabel.LEFT);
		txtNome = new JTextField();
		lblSobrenome = new JLabel("Sobrenome:", JLabel.LEFT);
		txtSobrenome = new JTextField();
		lblDataNascimento = new JLabel("Data de Nascimento:", JLabel.LEFT);
		ftxtDataNascimento = new JFormattedTextField();
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.install(ftxtDataNascimento);
		} catch (ParseException ex) {
			Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
		}
		lblEmail = new JLabel("E-mail:", JLabel.LEFT);
		txtEmail = new JTextField();
		lblCargo = new JLabel("Cargo:", JLabel.LEFT);
		txtCargo = new JTextField();
		labelId = new JLabel("Id:", JLabel.LEFT);
		txtId = new JTextField();
		lblSalario = new JLabel("Salário:", JLabel.LEFT);
		DecimalFormat formatter = new DecimalFormat("0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		ftxtSalario = new JFormattedTextField(formatter);
		// campoSalario.setValue(0);
		btnGravar = new JButton("Salvar", imgSobrescrever);
		txtId.setEnabled(false);
		txtId.setText("ID gerado automaticamente pelo banco de dados.");
		txtCargo.setEnabled(false);
		txtCargo.setText("Busca de cargo na próxima tela");

		lblTitulo.setBounds(20, 20, 660, 40);
		labelId.setBounds(150, 80, 400, 20);
		txtId.setBounds(150, 100, 400, 40);
		lblNome.setBounds(150, 140, 400, 20);
		txtNome.setBounds(150, 160, 400, 40);
		lblSobrenome.setBounds(150, 200, 400, 20);
		txtSobrenome.setBounds(150, 220, 400, 40);
		lblDataNascimento.setBounds(150, 260, 400, 20);
		ftxtDataNascimento.setBounds(150, 280, 400, 40);
		lblEmail.setBounds(150, 320, 400, 20);
		txtEmail.setBounds(150, 340, 400, 40);
		lblCargo.setBounds(150, 380, 400, 20);
		txtCargo.setBounds(150, 400, 400, 40);
		lblSalario.setBounds(150, 440, 400, 20);
		ftxtSalario.setBounds(150, 460, 400, 40);
		btnGravar.setBounds(560, 460, 150, 40);

		add(lblTitulo);
		add(labelId);
		add(txtId);
		add(lblNome);
		add(txtNome);
		add(lblSobrenome);
		add(txtSobrenome);
		add(lblDataNascimento);
		add(ftxtDataNascimento);
		add(lblEmail);
		add(txtEmail);
		add(lblCargo);
		add(txtCargo);
		add(lblSalario);
		add(ftxtSalario);
		add(btnGravar);

		setVisible(true);
	}

	private void criarEventos() {
		btnGravar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Passando valores
				funcionarioEditado.setFuncionarioNome(txtNome.getText());
				funcionarioEditado.setFuncionarioSobrenome(txtSobrenome.getText());
				funcionarioEditado.setFuncionarioDataNascimento(ftxtDataNascimento.getText());
				funcionarioEditado.setFuncionarioEmail(txtEmail.getText());
				funcionarioEditado.setFuncionarioSalario(ftxtSalario.getText());
				selecionarCargoComboBox(funcionarioEditado, searchFun);
			}
		});
	}

	private void selecionarCargoComboBox(Funcionario pFuncionario, String pSearchFun) {

		String nomeFuncionario = txtNome.getText();
		pFuncionario.setFuncionarioNome(nomeFuncionario);
		String sobrenomeFuncionario = txtSobrenome.getText();
		pFuncionario.setFuncionarioSobrenome(sobrenomeFuncionario);
		String dataNascimentoFuncionario = ftxtDataNascimento.getText();
		pFuncionario.setFuncionarioDataNascimento(dataNascimentoFuncionario);
		String emailFuncionario = txtEmail.getText();
		pFuncionario.setFuncionarioEmail(emailFuncionario);
		String cargoFuncionario = txtCargo.getText();
		pFuncionario.setFuncionarioCargo(emailFuncionario);
		String salarioFuncionario = ftxtSalario.getText();
		pFuncionario.setFuncionarioSalario(salarioFuncionario);

		if (verificaCampos() == true) {
			Navegador.InserirCargoFuncionarioAtualizar(pFuncionario, pSearchFun);
		} else {
			return;
		}
	}

	public boolean verificaCampos() {
		if (txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtCargo.getText().isEmpty() || ftxtDataNascimento.getText().isEmpty()
				|| ftxtSalario.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campo", "Validação", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// validando nome
		else if (txtNome.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente.");
			return false;
		}

		// validando sobrenome
		else if (txtSobrenome.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha o sobrenome corretamente.");
			return false;
		}

		// Validando Salario
		else if (ftxtSalario.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha o salário corretamente.");
			return false;
		}
		return true;
	}
}
