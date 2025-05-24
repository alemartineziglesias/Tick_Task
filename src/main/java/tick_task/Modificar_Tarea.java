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

	/**
	 * Create the frame.
	 * @param tarea 
	 */
	public Modificar_Tarea(Tareas tarea)
	{
		this.tarea = tarea;
		setTitle("Modificar Tarea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		contentPane.add(dateChooser);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() 
		{
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        
		    }
		});
		btnCrear.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnCrear.setBounds(250, 412, 113, 33);
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
		btnCancelar.setBounds(75, 412, 113, 33);
		contentPane.add(btnCancelar);
		
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
	}
}