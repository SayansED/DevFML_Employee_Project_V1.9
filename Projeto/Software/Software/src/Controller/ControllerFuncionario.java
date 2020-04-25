package Controller;

import DAO.DAO;
import sistema.entidades.Funcionario;

public class ControllerFuncionario {
	
	DAO dao = new DAO();
	
	public boolean inserirUsuarioController(Funcionario pFuncionario){
		return this.dao.inserirFuncionario(pFuncionario);
	}
	
	public boolean editarFuncionarioController(Funcionario pFuncionario, String pSearchFun) {
		return this.dao.editarFuncionario(pFuncionario, pSearchFun);
	}
}
