package tick_task;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Crear_Tarea extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JDateChooser dateChooser;
	@SuppressWarnings("unused")
	private int id;
	@SuppressWarnings("unused")
	private Menu_Tareas menuTareas;

	/**
	 * Create the frame.
	 * @param id 
	 */
	public Crear_Tarea(int id, Menu_Tareas menuTareas)
	{
		this.id = id;
		this.menuTareas = menuTareas;
		setTitle("Crear tarea");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblNombre.setBounds(130, 41, 58, 14);
		contentPane.add(lblNombre);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setBounds(198, 40, 146, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblDescripcion.setBounds(181, 88, 82, 14);
		contentPane.add(lblDescripcion);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(59, 113, 320, 135);
		contentPane.add(textArea);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblFecha.setBounds(130, 270, 58, 20);
		contentPane.add(lblFecha);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(198, 270, 146, 20);
		contentPane.add(dateChooser);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() 
		{
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        String nombre = textField.getText();
		        String descripcion = textArea.getText();
		        Date fechaSeleccionada = dateChooser.getDate();
		        SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
		        String fecha = formatoSQL.format(fechaSeleccionada);

		        int estado = 0; // estado por defecto
		        int idProyecto = id;

		        Datos datos = new Datos();
		        boolean insertado = datos.insertarTarea(nombre, descripcion, fecha, estado, idProyecto);

		        if (insertado) 
		        {
		            JOptionPane.showMessageDialog(null, "Tarea creada con éxito");
		            menuTareas.mostrarTareas(datos.obtenerTareas(id));
		            dispose();
		        } 
		        else 
		        {
		            JOptionPane.showMessageDialog(null, "Error al crear la tarea");
		        }
		    }
		});
		btnCrear.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnCrear.setBounds(249, 326, 113, 33);
		contentPane.add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnCancelar.setBounds(75, 326, 113, 33);
		contentPane.add(btnCancelar);
	}
}