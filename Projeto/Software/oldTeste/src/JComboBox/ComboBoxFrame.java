package JComboBox;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboBoxFrame extends JFrame{
	
	private final JComboBox<String> imagesJComboBox; // Contém nomes dos ícones
	
	private static final String[] names = {"imagem01", "imagem02", "imagem03",
			"imagem04", "imagem05", "imagem06", "imagem07", "imagem08",
			"imagem09", "imagem10"};
	
	// Construtor ComboBoxFrame
	public ComboBoxFrame() {
		super("Testing JComboBox");
		//setLayout(new FlowLayout());
		
		
		imagesJComboBox = new JComboBox<String>(names); // Configura
		imagesJComboBox.setMaximumRowCount(5); // Exibe 3 linhas
		
		imagesJComboBox.addItemListener(new ItemListener() { // Classe interna anônima
			// Trata evento
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				// Determina se o item está selecionado
				
				/*if(event.getStateChange() == ItemEvent.SELECTED) {
				
			}
				*/
			}
		});
		
		imagesJComboBox.setBounds(100, 100, 100, 100);
		
		ComboBoxFrame m = null;
		JPanel container = new JPanel();
		container.add(imagesJComboBox);
		add(container);
		//m.getContentPane().add(container);
		//m.pack();
		//m.setLocationRelativeTo(null);
		//m.setVisible(true);
		
		
		//add(imagesJComboBox);
		
	}
}
