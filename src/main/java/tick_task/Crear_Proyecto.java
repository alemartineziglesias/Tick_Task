package tick_task;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Crear_Proyecto extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProyecto;
	@SuppressWarnings("unused")
	private Usuarios usuario;

	/**
	 * Create the frame.
	 */
	public Crear_Proyecto(Usuarios usuario)
	{
		this.usuario = usuario;
		setTitle("Crear proyecto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreProyecto = new JLabel("Nombre:");
		lblNombreProyecto.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblNombreProyecto.setBounds(90, 68, 67, 14);
		contentPane.add(lblNombreProyecto);
		
		textFieldProyecto = new JTextField();
		textFieldProyecto.setBounds(167, 67, 186, 20);
		contentPane.add(textFieldProyecto);
		textFieldProyecto.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        String nombreProyecto = textFieldProyecto.getText().trim();

		        if (!nombreProyecto.isEmpty()) 
		        {
		            Datos datos = new Datos();
		            datos.insertarProyecto(nombreProyecto, usuario);
		            dispose();
		        } 
		        else 
		        {
		        	Dialogo dialogo = new Dialogo("Error: rellena el nombre");
		            dialogo.setVisible(true);
		        }
		    }
		});
		btnCrear.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnCrear.setBounds(262, 146, 81, 34);
		contentPane.add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnCancelar.setBounds(90, 146, 91, 34);
		contentPane.add(btnCancelar);
	}
}
