package JComboBox;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ComboBoxMain extends JFrame{

	public static void main(String[] args) {
		
		ComboBoxMain m = new ComboBoxMain();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComboBox<String> imagesJComboBox; // Contém nomes dos ícones
		
		String[] names = {"imagem01", "imagem02", "imagem03",
				"imagem04", "imagem05", "imagem06", "imagem07", "imagem08",
				"imagem09", "imagem10"};
		
		imagesJComboBox = new JComboBox<String>(names); // Configura
		imagesJComboBox.setMaximumRowCount(5); // Exibe 3 linhas
		
		
		
		JPanel container = new JPanel();
		
		imagesJComboBox.setBounds(100, 100, 100, 100);
		
		container.add(imagesJComboBox);
		
		m.getContentPane().add(container);
		m.pack();
		m.setLocationRelativeTo(null);
		m.setSize(350, 150);
		m.setVisible(true);

	}

}
