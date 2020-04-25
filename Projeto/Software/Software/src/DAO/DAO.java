package DAO;

import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sistema.telas.CargosInserir;
import sistema.telas.FuncionariosInserir;
import sistema.telas.FuncionariosInserirCargo;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;

public class DAO {

	Cargo cargo = new Cargo();
	Funcionario funcionario = new Funcionario();
	// conexão
	Conexao conexao = new Conexao();
	CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);
	// instrucao SQL
	PreparedStatement preparedStatement = null;
	// resultados
	ResultSet resultado = null;

	/**
	 * Salvar um novo Funcionário
	 * 
	 * @param pFuncionario
	 * @return
	 */
	public boolean inserirFuncionario(Funcionario pFuncionario) {
		funcionario = new Funcionario();
		conexao.conectar();
		String sqlInsertFuncionario = "INSERT INTO T_FUNCIONARIOS " + "(nome, " + "sobrenome, " + "dataNascimento, "
				+ "email, " + "cargo, " + "salario) " + "VALUES(?,?,?,?,?,?);";
		try {
			preparedStatement = conexao.criarPreparedStatement(sqlInsertFuncionario, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, pFuncionario.getFuncionarioNome());
			preparedStatement.setString(2, pFuncionario.getFuncionarioSobrenome());
			preparedStatement.setString(3, pFuncionario.getFuncionarioDataNascimento());
			preparedStatement.setString(4, pFuncionario.getFuncionarioEmail());
			preparedStatement.setString(5, pFuncionario.getFuncionarioCargo());
			preparedStatement.setString(6, pFuncionario.getFuncionarioSalario());
			int resultados = preparedStatement.executeUpdate();
			if (resultados == 1) {
				String message = String.format("Funcionário: %s\nCadastrado com sucesso",
						pFuncionario.getFuncionarioNome());
				JOptionPane.showMessageDialog(null, message, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Funcionário não inserido");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar.");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		Navegador.funcionariosCadastrar();;
		conexao.desconectar();
		return true;
	}
	
	public boolean editarFuncionario(Funcionario pFuncionario, String pSearchFun) {

		// conexão
		Conexao conexao = new Conexao();
		// instrucao SQL
		PreparedStatement preparedStatement = null;
		// resultados
		int resultado;

		try {
			// conectando ao banco de dados
			// String busca = JOptionPane.showInputDialog("Digite o nome para a busca");
			String sqlUpdate = "UPDATE T_FUNCIONARIOS SET nome=?,sobrenome=?,dataNascimento=?,email=?,cargo=?,salario=? WHERE nome=?;";
			conexao.conectar();
			preparedStatement = conexao.criarPreparedStatement(sqlUpdate);
			
			preparedStatement.setString(1, pFuncionario.getFuncionarioNome());
			preparedStatement.setString(2, pFuncionario.getFuncionarioSobrenome());
			preparedStatement.setString(3, pFuncionario.getFuncionarioDataNascimento());
			preparedStatement.setString(4, pFuncionario.getFuncionarioEmail());
			preparedStatement.setString(5, pFuncionario.getFuncionarioCargo());
			preparedStatement.setString(6, pFuncionario.getFuncionarioSalario().replace(",", "."));
			
			preparedStatement.setString(7, pSearchFun);
			resultado = preparedStatement.executeUpdate();
			if (resultado == 0) {
				JOptionPane.showMessageDialog(null, "Funcionário não encontrado: " + pSearchFun, "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			JOptionPane.showMessageDialog(null, "Funcionario atualizado com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar o Funcionario.");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		Navegador.funcionariosConsultar();;
		conexao.desconectar();
		return true;
	}

	/**
	 * Salvar um novo Cargo
	 * 
	 * @param pCargo
	 * @return
	 */
	public boolean inserirCargo(Cargo pCargo) {
		cargo = new Cargo();
		conexao.conectar();
		String sqlInsert = "INSERT INTO T_CARGOS " + "(nome) " + "VALUES(?);";
		try {
			preparedStatement = conexao.criarPreparedStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, pCargo.getCargoNome());
			int resultados = preparedStatement.executeUpdate();
			if (resultados == 1) {
				String message = String.format("Cargo: %s\nCadastrado com sucesso", pCargo.getCargoNome());
				JOptionPane.showMessageDialog(null, message, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Cargo não inserido");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar", "ERRO", JOptionPane.ERROR_MESSAGE);
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		Navegador.cargosCadastrar();;
		conexao.desconectar();
		return true;
	}
}
