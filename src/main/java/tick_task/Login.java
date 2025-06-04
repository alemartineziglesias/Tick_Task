package tick_task;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField textFieldClave;
	private JButton btnEntrar;
	private JLabel lblNuevoUsuario;
	private JButton btnNuevoUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Login frame = new Login();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login()
	{
		setTitle("Inicio de sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblUsuario.setBounds(96, 56, 60, 14);
		contentPane.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(166, 55, 165, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblContrasea.setBounds(70, 113, 86, 14);
		contentPane.add(lblContrasea);
		
		textFieldClave = new JPasswordField();
		textFieldClave.setColumns(10);
		textFieldClave.setBounds(166, 112, 165, 20);
		contentPane.add(textFieldClave);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        Datos datos = new Datos();
		        String nombre = textFieldUsuario.getText();
		        String clave = new String(textFieldClave.getPassword());

		        Usuarios usuario = datos.obtenerUsuarioPorCredenciales(nombre, clave);
		        if (usuario != null) 
		        {
		            Menu_Proyectos menu = new Menu_Proyectos(usuario);
		            menu.setVisible(true);
		            setVisible(false);
		        }
		        else 
		        {
		            Dialogo dialogo = new Dialogo("Error: las credenciales no son correctas");
		            dialogo.setVisible(true);
		        }
		    }
		});
		btnEntrar.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnEntrar.setBounds(156, 167, 89, 34);
		contentPane.add(btnEntrar);
		
		lblNuevoUsuario = new JLabel("¿No tienes un usuario todavía? Regístrate");
		lblNuevoUsuario.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		lblNuevoUsuario.setBounds(70, 229, 252, 14);
		contentPane.add(lblNuevoUsuario);
		
		btnNuevoUsuario = new JButton("Nuevo usuario");
		btnNuevoUsuario.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Crear_Usuario usuarios = new Crear_Usuario();
				usuarios.setVisible(true);
			}
		});
		btnNuevoUsuario.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnNuevoUsuario.setBounds(125, 254, 150, 34);
		contentPane.add(btnNuevoUsuario);
	}
}
