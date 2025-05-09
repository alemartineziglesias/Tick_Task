package tick_task;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Crear_usuario extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldClave;
	private JButton btnEntrar;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public Crear_usuario()
	{
		setTitle("Crear usuario");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblUsuario.setBounds(134, 56, 60, 14);
		contentPane.add(lblUsuario);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(204, 55, 165, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblContrasea.setBounds(108, 113, 86, 14);
		contentPane.add(lblContrasea);

		textFieldClave = new JTextField();
		textFieldClave.setColumns(10);
		textFieldClave.setBounds(204, 112, 165, 20);
		contentPane.add(textFieldClave);

		btnEntrar = new JButton("Crear");
		btnEntrar.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				String usuario = textFieldUsuario.getText();
				String clave = textFieldClave.getText();
				String claveHasheada = org.apache.commons.codec.digest.DigestUtils.sha256Hex(clave);
				Usuarios nuevoUsuario = new Usuarios();
				nuevoUsuario.setNombreUsuario(usuario);
				nuevoUsuario.setClaveUsuario(claveHasheada);
				try (var session = HibernateUtil.getSessionFactory().openSession()) 
				{
					session.beginTransaction();
					session.save(nuevoUsuario);
					session.getTransaction().commit();
					setVisible(false);
					Dialogo dialogo = new Dialogo("¡Usuario creado!");
					dialogo.setVisible(true);
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
		});
		btnEntrar.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnEntrar.setBounds(247, 167, 89, 34);
		contentPane.add(btnEntrar);
		
		btnCancelar = new JButton("Cancelar");
		btnEntrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnCancelar.setBounds(103, 168, 91, 32);
		contentPane.add(btnCancelar);
	}
}