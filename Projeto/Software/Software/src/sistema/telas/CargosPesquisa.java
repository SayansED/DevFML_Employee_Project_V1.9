package sistema.telas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sqlite.Conexao;

public class CargosPesquisa extends JFrame {

	DefaultListModel<Cargo> listCargosModel = new DefaultListModel();
	JList<Cargo> listCargos;
	String txtCampoCargo;
	JLabel lblTitle;
	JButton btnVoltar, btnEditar, btnExcluir, btnConsultarDados;
	ImageIcon imgIconDeletar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgIconEditar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png");
	ImageIcon imgIconBack = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\back02.png");
	ImageIcon imgPesquisar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\search03.png");
	Container container;

	public CargosPesquisa(String ptxtCampoCargo) {
		this.txtCampoCargo = ptxtCampoCargo;
		CriarComponentes();
		CriarEventos();
	}

	private void CriarComponentes() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		container = getContentPane();

		lblTitle = new JLabel("Consulta de Cargos", JLabel.CENTER);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 20));
		btnVoltar = new JButton("Voltar", imgIconBack);
		btnEditar = new JButton("Editar", imgIconEditar);
		btnExcluir = new JButton("Excluir", imgIconDeletar);
		btnConsultarDados = new JButton("Consultar", imgPesquisar);

		add(lblTitle);

		listCargosModel = new DefaultListModel();
		listCargos = new JList();
		listCargos.setModel(listCargosModel);
		listPesquisarCargos();

		add(btnVoltar);
		add(btnEditar);
		add(btnExcluir);
		add(btnConsultarDados);
	}

	private void CriarEventos() {

		// Editar
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cargoSelecionado;
				// Recebo um objeto
				Cargo listCargoSelecionado = (Cargo) listCargos.getSelectedValue();
				if (listCargoSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um campo");
				} else {
					// Seleciono o nome do cargo pelo objeto recebido
					cargoSelecionado = listCargoSelecionado.getCargoNome();
					// Depois de selecionar, passa o cargo para o metodo
					sqlAtualizarCargo(cargoSelecionado);
				}
				voltaFrame();
			}
		});

		// Excluir
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cargoSelecionado;
				// Recebo um objeto
				Cargo listCargoSelecionado = (Cargo) listCargos.getSelectedValue();
				if (listCargoSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um campo");
				} else {
					// Seleciono o nome do cargo pelo objeto recebido
					cargoSelecionado = listCargoSelecionado.getCargoNome();
					// Depois de selecionar, passa o cargo para o metodo
					sqlDeletarCargo(cargoSelecionado);
				}
				voltaFrame();
			}
		});

		// Voltar
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltaFrame();
			}
		});

		btnConsultarDados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cargoSelecionado;
				// Recebo um objeto
				Cargo listCargoSelecionado = (Cargo) listCargos.getSelectedValue();
				if (listCargoSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um campo");
				} else {
					// Seleciono o nome do cargo pelo objeto recebido
					cargoSelecionado = listCargoSelecionado.getCargoNome();
					// Depois de selecionar, passa o cargo para o metodo
					sqlConsultarDadosCargos(cargoSelecionado);
				}
			}
		});
	}

	private void voltaFrame() {
		Navegador.cargosConsultar();
		Navegador.cargosList.setVisible(false);
	}

	private void listPesquisarCargos() {
		// Conexao BD
		Conexao conexao = new Conexao();
		// Statement
		Statement statement = null;
		// resultados
		ResultSet resultSet = null;

		try {
			conexao.conectar();
			String sqlSelect = "SELECT * FROM T_CARGOS WHERE nome LIKE '%" + txtCampoCargo + "%';";
			statement = conexao.criarStatement();
			resultSet = statement.executeQuery(sqlSelect);
			listCargosModel.clear();
			while (resultSet.next()) {
				Cargo cargo = new Cargo();
				cargo.setCargoId(resultSet.getInt("id"));
				cargo.setCargoNome(resultSet.getString("nome"));
				listCargosModel.addElement(cargo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar cargos");
			Logger.getLogger(CargosPesquisa.class.getName()).log(Level.SEVERE, null, ex);
		}
		listCargos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		listCargos.setVisibleRowCount(10);
		add(new JScrollPane(listCargos));
	}

	private void sqlDeletarCargo(String pCargoSelecionado) {
		int confirmacao = JOptionPane.showConfirmDialog(null,
				"Deseja realmente " + "excluir o Cargo " + pCargoSelecionado + "?", "Excluir",
				JOptionPane.YES_NO_OPTION);
		if (confirmacao == JOptionPane.YES_OPTION) {
			// conexão
			Conexao conexao = new Conexao();
			// instrucao SQL
			Statement statement = null;
			// resultados
			ResultSet resultados = null;
			try {
				// Conectando - Driver
				conexao.conectar();
				// Instrução SQL
				statement = conexao.criarStatement();
				String sqlDelete = "DELETE FROM T_CARGOS WHERE nome=?;";
				PreparedStatement preparedStatement = conexao.criarPreparedStatement(sqlDelete);
				preparedStatement.setString(1, pCargoSelecionado);
				int resultado = preparedStatement.executeUpdate();
				if (resultado == 1) {
					String message = String.format("Cargo: %s\nDeletado com sucesso", pCargoSelecionado);
					JOptionPane.showMessageDialog(null, message, "Excluir", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Cargo não encontrado");
					return;
				}
				conexao.desconectar();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Cargo.");
				Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
			}
			return;
		}
	}

	private void sqlAtualizarCargo(String pCargoSelecionado) {
		// conexão
		Conexao conexao = new Conexao();
		// instrucao SQL
		Statement statement;
		PreparedStatement preparedStatement;
		// resultados
		int resultado;

		try {
			// conectando ao banco de dados
			conexao.conectar();
			// criando a instrução SQL
			String alterando = JOptionPane.showInputDialog("Digite o novo nome para ser inserido");
			String sqlUpdate = "UPDATE T_CARGOS SET nome = ? WHERE nome = ?;"; // Codigo de atualização
			preparedStatement = conexao.criarPreparedStatement(sqlUpdate);
			preparedStatement.setString(1, alterando);
			preparedStatement.setString(2, pCargoSelecionado);
			resultado = preparedStatement.executeUpdate(); // Executando o UPDATE
			if (resultado == 0) {
				JOptionPane.showMessageDialog(null, "Cargo não encontrado: " + pCargoSelecionado, "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null, "Cargo alterado com sucesso", "Mensagem",
					JOptionPane.INFORMATION_MESSAGE);
			conexao.desconectar();

		} catch (SQLException ew) {
			ew.printStackTrace();
		} finally {
			conexao.desconectar();
		}
	}

	public void sqlConsultarDadosCargos(String pNameConsult) {
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		PreparedStatement preparedStatement;
		// Resultados
		ResultSet resultado = null;

		try {
			// Criando instrução SQL
			String sqlSelect = "SELECT id, nome FROM T_CARGOS WHERE nome = ?;";
			// Conexão - Driver
			conexao.conectar();
			preparedStatement = conexao.criarPreparedStatement(sqlSelect);
			preparedStatement.setString(1, pNameConsult);
			resultado = preparedStatement.executeQuery();

			if (resultado.next()) {
				// Next == true "Encontoru os dados"
				JOptionPane.showMessageDialog(null, "Consulta realizado com sucesso", "Consulta",
						JOptionPane.INFORMATION_MESSAGE);
				do {
					JOptionPane.showMessageDialog(null,
							"Cargo" + "\n" + "Id: " + resultado.getString("id") + "\n" + "Nome: "
									+ resultado.getString("nome") + "\n",
							"Consulta de dados", JOptionPane.INFORMATION_MESSAGE);
				} while (resultado.next());
			} else {
				// Next == false
				JOptionPane.showMessageDialog(null, "Cargo não encontrado", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				Navegador.inicio();
			}
			resultado.close();
			conexao.desconectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar cargos");
			Logger.getLogger(CargosPesquisa.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				resultado.close();
				conexao.desconectar();
			} catch (SQLException ez) {
				ez.printStackTrace();
			}
		}
	}
}
