package tick_task;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class Modificar_Tarea extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JDateChooser dateChooser;
	@SuppressWarnings("unused")
	private Tareas tarea;
	@SuppressWarnings("unused")
	private Menu_Tareas menuTareas;
	@SuppressWarnings("unused")
	private int id;

	/**
	 * Create the frame.
	 * @param tarea 
	 * @param id 
	 * @param menu_Tareas 
	 */
	public Modificar_Tarea(Tareas tarea, Menu_Tareas menuTareas, int id)
	{
		this.tarea = tarea;
		this.menuTareas = menuTareas;
		this.id = id;
		setTitle("Modificar Tarea");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 495);
        getContentPane().setLayout(null);
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
		
		textField = new JTextField(tarea.getNombreTarea());
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setBounds(198, 40, 146, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblDescripcion.setBounds(181, 88, 82, 14);
		contentPane.add(lblDescripcion);
		
		JTextArea textArea = new JTextArea(tarea.getDescripcionTarea());
		textArea.setBounds(59, 113, 320, 135);
		contentPane.add(textArea);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblFecha.setBounds(130, 270, 58, 20);
		contentPane.add(lblFecha);
		
		dateChooser = new JDateChooser();
		dateChooser.setDate(Date.valueOf(tarea.getFechaTarea()));
		dateChooser.setBounds(198, 270, 146, 20);
		((JTextField) dateChooser.getDateEditor().getUiComponent()).setEditable(false);
		contentPane.add(dateChooser);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		lblEstado.setBounds(198, 318, 48, 14);
		contentPane.add(lblEstado);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		comboBox.setBounds(154, 343, 137, 20);
	    comboBox.addItem("Pendiente");
	    comboBox.addItem("En Proceso");
	    comboBox.addItem("Completada");
	    switch (tarea.getEstadoTarea()) 
	    {
        	case 0: comboBox.setSelectedItem("Pendiente"); break;
        	case 1: comboBox.setSelectedItem("En Proceso"); break;
        	case 2: comboBox.setSelectedItem("Completada"); break;
	    }
		contentPane.add(comboBox);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() 
		{
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(textField.getText().isBlank() || textArea.getText().isBlank())
		    	{
		    		Dialogo dialogo = new Dialogo("Error: rellena todos los campos");
					dialogo.setVisible(true);
		    	}
		    	else
		    	{
		    		String nuevoNombre = textField.getText();
			        String nuevaDescripcion = textArea.getText();
			        String nuevaFecha = new Date(dateChooser.getDate().getTime()).toString();
			        int nuevoEstado;

			        String estadoSeleccionado = (String) comboBox.getSelectedItem();
			        switch (estadoSeleccionado) 
			        {
			            case "Pendiente":
			                nuevoEstado = 0;
			                break;
			            case "En Proceso":
			                nuevoEstado = 1;
			                break;
			            case "Completada":
			                nuevoEstado = 2;
			                break;
			            default:
			                nuevoEstado = 0;
			        }

			        Datos datos = new Datos();
			        boolean actualizado = datos.actualizarTarea(tarea.getIdTarea(), nuevoNombre, nuevaDescripcion, nuevaFecha, nuevoEstado);

			        if (actualizado) 
			        {
			        	Dialogo dialogo = new Dialogo("¡Tarea modificada!");
						dialogo.setVisible(true);
			            menuTareas.mostrarTareas(datos.obtenerTareas(id));
			            dispose();
			        } 
			        else 
			        {
			        	Dialogo dialogo = new Dialogo("Error al modificar la tarea");
						dialogo.setVisible(true);
			        }
		    	}
		    }
		});
		btnEditar.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnEditar.setBounds(250, 412, 113, 33);
		contentPane.add(btnEditar);
		
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
		btnCancelar.setBounds(75, 412, 113, 33);
		contentPane.add(btnCancelar);
	}
}