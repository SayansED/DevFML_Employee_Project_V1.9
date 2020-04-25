package sistema.telas;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controller.ControllerFuncionario;
import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sqlite.Conexao;

public class FuncionarioAtualizarInserirCargo extends JFrame {

	Funcionario funcionario;
	String searchFun;

	JLabel lblTitle;
	JComboBox<Cargo> comboBoxInserirCargo;
	DefaultComboBoxModel<Cargo> comboBoxModel;
	JButton btnVoltar, btnEditar, btnExcluir, btnSalvar;
	ImageIcon imgIconDeletar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgIconEditar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png");
	ImageIcon imgIconBack = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\back02.png");
	ImageIcon imgSalvar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\save01.png");
	Cargo cargoSelecionadoComboBox = new Cargo();

	public FuncionarioAtualizarInserirCargo(Funcionario pFuncionario, String pSearchFun) {
		this.funcionario = pFuncionario;
		this.searchFun = pSearchFun;
		criarComponentes();
		criarEventos();
	}

	public void criarComponentes() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));

		lblTitle = new JLabel("Funcionário - Inserir Cargo", JLabel.CENTER);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 20));
		add(lblTitle);

		btnSalvar = new JButton("Salvar Funcionário", imgSalvar);
		btnVoltar = new JButton("Voltar", imgIconBack);
		btnEditar = new JButton("Editar", imgIconEditar);
		btnEditar.setEnabled(false);

		// Gerar combobox
		comboBoxModel = new DefaultComboBoxModel();
		comboBoxInserirCargo = new JComboBox<Cargo>(comboBoxModel);
		comboBoxInserirCargo.setModel(comboBoxModel);
		add(btnVoltar);
		add(btnEditar);

		// Buscar cargos
		sqlBuscaCargos();

		add(btnSalvar);
	}

	public void criarEventos() {

		// Carregar Cargos
		comboBoxInserirCargo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				funcionario.setFuncionarioCargo("");
				String cargoSelecionado;
				Cargo comboBoxCargoSelecionado = (Cargo) comboBoxInserirCargo.getSelectedItem();
				// Determina se o item está selecionado
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cargoSelecionado = comboBoxCargoSelecionado.getCargoNome();
					cargoSelecionadoComboBox = new Cargo();
					cargoSelecionadoComboBox.setCargoNome(cargoSelecionado);
					funcionario.setFuncionarioCargo(cargoSelecionado);
				}
			}
		});

		// Salvar Funcionario
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (funcionario.getFuncionarioCargo().length() == 0) {
					JOptionPane.showMessageDialog(null, "Selecione um cargo");
					return;
				} else {
					// Modelo DAO
					ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
					controllerFuncionario.editarFuncionarioController(funcionario, searchFun);
					voltaFrame();
				}
			}
		});

		// Volta Frame
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltaFrame();
			}
		});

	}

	public void voltaFrame() {
		Navegador.AtualizarFunInserirCargo.setVisible(false);
	}

	public void sqlBuscaCargos() {
		// Conexao BD
		Conexao conexao = new Conexao();
		// Statement
		Statement statement = null;
		// resultados
		ResultSet resultSet = null;

		try {
			conexao.conectar();
			String sqlSelect = "SELECT * FROM T_CARGOS;";
			statement = conexao.criarStatement();
			resultSet = statement.executeQuery(sqlSelect);
			// comboBoxModel.clear();
			while (resultSet.next()) {
				Cargo cargo = new Cargo();
				cargo.setCargoId(resultSet.getInt("id"));
				cargo.setCargoNome(resultSet.getString("nome"));
				comboBoxModel.addElement(cargo);
			}
			comboBoxInserirCargo.setMaximumRowCount(5); // Exibe 5 linhas
			add(comboBoxInserirCargo, FlowLayout.CENTER);

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar cargos");
			Logger.getLogger(FuncionariosInserirCargo.class.getName()).log(Level.SEVERE, null, ex);
		}
		comboBoxInserirCargo.setMaximumRowCount(5); // Exibe 5 linhas
	}

}
