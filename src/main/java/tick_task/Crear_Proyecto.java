package tick_task;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Crear_Proyecto extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProyecto;

	/**
	 * Create the frame.
	 */
	public Crear_Proyecto()
	{
		setTitle("Crear proyecto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblNewLabel.setBounds(90, 68, 67, 14);
		contentPane.add(lblNewLabel);
		
		textFieldProyecto = new JTextField();
		textFieldProyecto.setBounds(167, 67, 186, 20);
		contentPane.add(textFieldProyecto);
		textFieldProyecto.setColumns(10);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnNewButton.setBounds(262, 146, 81, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnNewButton_1.setBounds(90, 146, 91, 34);
		contentPane.add(btnNewButton_1);
	}
}
