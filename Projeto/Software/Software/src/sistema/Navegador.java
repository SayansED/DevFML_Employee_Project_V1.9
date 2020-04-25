package sistema;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sistema.telas.FuncionarioAtualizarInserirCargo;
import sistema.telas.CargosConsultar;
import sistema.telas.CargosInserir;
import sistema.telas.CargosPesquisa;
import sistema.telas.FuncionariosConsultar;
import sistema.telas.FuncionariosEditar;
import sistema.telas.FuncionariosInserir;
import sistema.telas.FuncionariosInserirCargo;
import sistema.telas.FuncionariosPesquisa;
import sistema.telas.Inicio;
import sistema.telas.Login;
import sistema.telas.RelatoriosCargos;
import sistema.telas.RelatoriosSalarios;

public class Navegador {
    
    // Menu
	public static FuncionariosPesquisa funcionariosList;
	public static CargosPesquisa cargosList;
	public static FuncionariosInserirCargo funInserirCargo;
	public static FuncionarioAtualizarInserirCargo AtualizarFunInserirCargo;
    private static boolean menuConstruido;
    private static boolean menuHabilitado;
    private static JMenuBar menuBar;
    private static JMenu menuArquivo, menuFuncionarios, menuCargos, menuRelatorios;
    private static JMenuItem miInicio, miSair, miFuncionariosConsultar, miFuncionariosCadastrar, miCargosConsultar;
    private static JMenuItem miCargosCadastrar, miRelatoriosCargos, miRelatoriosSalarios;
    private static String versãoSoftware = "V1.9";
    
    public static void login(){
        Sistema.JPtela = new Login();
        Sistema.JFframe.setTitle("Company Employees - "+versãoSoftware);
        Navegador.atualizarTela();
    }
    
    public static void inicio(){
        Sistema.JPtela = new Inicio();
        Sistema.JFframe.setTitle("Company Employees - "+versãoSoftware);
        Navegador.atualizarTela();
    }
    
    public static void funcionariosCadastrar(){
        Sistema.JPtela = new FuncionariosInserir();   
        Sistema.JFframe.setTitle("Company Employees - Cadastrar funcionários - V1.8");     
        Navegador.atualizarTela();
    }
    
    public static void funcionariosConsultar(){
        Sistema.JPtela = new FuncionariosConsultar();
        Sistema.JFframe.setTitle("Company Employees - Consultar funcionários - "+versãoSoftware);     
        Navegador.atualizarTela();
    }
    
    public static void funcionariosEditar(String funcionarioAtual){
        Sistema.JPtela = new FuncionariosEditar(funcionarioAtual);  
        Sistema.JFframe.setTitle("Company Employees - Editar funcionários - "+versãoSoftware);           
        Navegador.atualizarTela();
    }
    
    public static void cargosCadastrar(){
        Sistema.JPtela = new CargosInserir();
        Sistema.JFframe.setTitle("Company Employees - Cadastrar cargos - "+versãoSoftware);
        Navegador.atualizarTela();
    }
    
    public static void cargosConsultar(){
        Sistema.JPtela = new CargosConsultar();  
        Sistema.JFframe.setTitle("Company Employees - Consultar cargos - "+versãoSoftware);      
        Navegador.atualizarTela();
    }
    
    public static void CargosPesquisa(String ptxtCargoSelecionado) {
    	cargosList = new CargosPesquisa(ptxtCargoSelecionado);
    	cargosList.setSize(300, 400);
		String strIconWay = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\system03.png";
		Image iconSystem = Toolkit.getDefaultToolkit().getImage(strIconWay);
		cargosList.setIconImage(iconSystem);
		cargosList.setTitle("Company Employees - Consulta Cargo - "+versãoSoftware);
		cargosList.setLocationRelativeTo(null);
		cargosList.setVisible(true);
    }
    
    public static void FuncionariosPesquisa(String ptxtFuncionarioSelecionado) {
    	funcionariosList = new FuncionariosPesquisa(ptxtFuncionarioSelecionado);
    	funcionariosList.setSize(300, 400);
		String strIconWay = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\system03.png";
		Image iconSystem = Toolkit.getDefaultToolkit().getImage(strIconWay);
		funcionariosList.setIconImage(iconSystem);
		funcionariosList.setTitle("Company Employees - Consulta Funcionário - "+versãoSoftware);
		funcionariosList.setLocationRelativeTo(null);
		funcionariosList.setVisible(true);
    }
    
    public static void FuncionariosInserirCargo(Funcionario novoFuncionario) {
    	funInserirCargo = new FuncionariosInserirCargo(novoFuncionario);
    	funInserirCargo.setSize(280, 300);
		String strIconWay = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\system03.png";
		Image iconSystem = Toolkit.getDefaultToolkit().getImage(strIconWay);
		funInserirCargo.setIconImage(iconSystem);
		funInserirCargo.setTitle("Company Employees - Funcionário Inserir Cargo - "+versãoSoftware);
		funInserirCargo.setLocationRelativeTo(null);
		funInserirCargo.setVisible(true);
    }
    
    public static void InserirCargoFuncionarioAtualizar(Funcionario pFuncionario, String pSearchFun) {
    	AtualizarFunInserirCargo = new FuncionarioAtualizarInserirCargo(pFuncionario, pSearchFun);
    	AtualizarFunInserirCargo.setSize(280, 300);
		String strIconWay = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\system03.png";
		Image iconSystem = Toolkit.getDefaultToolkit().getImage(strIconWay);
		AtualizarFunInserirCargo.setIconImage(iconSystem);
		AtualizarFunInserirCargo.setTitle("Company Employees - Funcionário Inserir Cargo - "+versãoSoftware);
		AtualizarFunInserirCargo.setLocationRelativeTo(null);
		AtualizarFunInserirCargo.setVisible(true);
    }
    
    public static void relatoriosCargos(){
    	/*
        Sistema.tela = new RelatoriosCargos();
        Sistema.frame.setTitle("Funcionários Company - Relatórios");    
        Navegador.atualizarTela();
        */
    	JOptionPane.showMessageDialog(null, "EM MANUTENÇÃO", "Descupe", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void relatoriosSalarios(){
    	/*
        Sistema.tela = new RelatoriosSalarios();
        Sistema.frame.setTitle("Funcionários Company - Relatórios");    
        Navegador.atualizarTela();
        */
    	JOptionPane.showMessageDialog(null, "EM MANUTENÇÃO", "Descupe", JOptionPane.ERROR_MESSAGE);
    }
    
    // Método que remove a tela atual e adiciona a próxima tela
    private static void atualizarTela(){
        Sistema.JFframe.getContentPane().removeAll();
        Sistema.JPtela.setVisible(true);
        Sistema.JFframe.add(Sistema.JPtela);
        
        Sistema.JFframe.setVisible(true);
    }
    
    private static void construirMenu(){
        if(!menuConstruido){
            menuConstruido = true;
            
            menuBar = new JMenuBar();
            
            // menu Arquivo
            menuArquivo = new JMenu("Arquivo");
            menuBar.add(menuArquivo);
            miInicio = new JMenuItem("Inicio");
            menuArquivo.add(miInicio);
            miSair = new JMenuItem("Sair");
            menuArquivo.add(miSair);
            
            // menu Funcionários
            menuFuncionarios = new JMenu("Funcionários");
            menuBar.add(menuFuncionarios);
            miFuncionariosCadastrar = new JMenuItem("Cadastrar");
            menuFuncionarios.add(miFuncionariosCadastrar);
            miFuncionariosConsultar = new JMenuItem("Consultar");
            menuFuncionarios.add(miFuncionariosConsultar);
            
            // menu Cargos
            menuCargos = new JMenu("Cargos");
            menuBar.add(menuCargos);
            miCargosCadastrar = new JMenuItem("Cadastrar");
            menuCargos.add(miCargosCadastrar);
            miCargosConsultar = new JMenuItem("Consultar");
            menuCargos.add(miCargosConsultar);
            
            // menu Relatórios
            menuRelatorios = new JMenu("Relatórios");
            menuBar.add(menuRelatorios);
            miRelatoriosCargos = new JMenuItem("Funcionários por cargos");
            menuRelatorios.add(miRelatoriosCargos);
            miRelatoriosSalarios = new JMenuItem("Salários dos funcionários");
            menuRelatorios.add(miRelatoriosSalarios);
            
            criarEventosMenu();
            
        }
    }
    
    public static void habilitaMenu(){
        if(!menuConstruido) construirMenu();
        if(!menuHabilitado){
            menuHabilitado = true;
            Sistema.JFframe.setJMenuBar(menuBar);
        }
    }
    
    public static void desabilitaMenu(){
        if(menuHabilitado){
            menuHabilitado = false;
            Sistema.JFframe.setJMenuBar(null);
        }        
    }

    private static void criarEventosMenu() {
    	// Inicio
    	 miInicio.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Navegador.inicio();
             }
         });
    	
    	// Exit
        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Funcionario
        miFuncionariosCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionariosCadastrar();
            }
        });
        miFuncionariosConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionariosConsultar();
            }
        });
        
        // Cargos
        miCargosCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargosCadastrar();
            }
        });
        miCargosConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargosConsultar();
            }
        });
        
        miRelatoriosCargos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                relatoriosCargos();
            }
        });
        
        miRelatoriosSalarios.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                relatoriosSalarios();
            }
        });
    }
}
