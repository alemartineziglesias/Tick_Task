package tick_task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class Menu_Tareas extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JButton btnNuevoProyecto;
    private int id;
    @SuppressWarnings("unused")
	private String proyecto;
    Datos datos = new Datos();

    public Menu_Tareas(int id, String proyecto) 
    {
    	this.id = id;
    	this.proyecto = proyecto;
        setTitle(proyecto + " - Tareas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Tamaño del panel de contenido
        int panelWidth = 475;
        int panelHeight = 220;

        // Calculamos posición centrada
        int x = ((getWidth() - panelWidth) / 2) - 8;
        int y = 40;

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
        contentPane.setBackground(new Color(33, 33, 33));

        scrollPane = new JScrollPane(contentPane);
        scrollPane.setBounds(x, y, panelWidth, panelHeight);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        btnNuevoProyecto = new JButton("Crear tarea");
        btnNuevoProyecto.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            	Crear_Tarea tarea = new Crear_Tarea();
            	tarea.setVisible(true);
            }
        });
        btnNuevoProyecto.setBounds(((getWidth() - 160) / 2) - 8, y + panelHeight + 20, 160, 30); // Centrado debajo
        add(btnNuevoProyecto);

        List<Tareas> tareas = datos.obtenerTareas(id);
        mostrarTareas(tareas);
    }


    public void mostrarTareas(List<Tareas> tareas) 
    {
        contentPane.removeAll();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        for (Tareas tarea : tareas) 
        {
        	LocalDate fechaVencimiento = LocalDate.parse(tarea.getFechaTarea());
            LocalDate fechaActual = LocalDate.now();
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS)); // Usamos BoxLayout en Y para alinear los componentes verticalmente
            itemPanel.setMaximumSize(new Dimension(455, 60)); // Un poco menos que el contentPane
            itemPanel.setAlignmentX(CENTER_ALIGNMENT);        // Centrar horizontalmente
            itemPanel.setBackground(new Color(245, 245, 245));
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            itemPanel.setBackground(new Color(255, 255, 255));
            itemPanel.setOpaque(true);

            JLabel lblNombre = new JLabel(tarea.getNombreTarea());
            lblNombre.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
            lblNombre.setForeground(new Color(40, 40, 40));
            itemPanel.add(lblNombre);

            JLabel lblFecha = new JLabel("Fecha: " + tarea.getFechaTarea());
            lblFecha.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
            lblFecha.setForeground(new Color(100, 100, 100));
            itemPanel.add(lblFecha);

            itemPanel.addMouseListener(new MouseAdapter() 
            {
                public void mouseEntered(MouseEvent e) 
                {
                	if(tarea.getEstadoTarea() == 2)
                	{
                		itemPanel.setBackground(new Color(34, 141, 8));
                	}
                	else if(tarea.getEstadoTarea() == 1)
                	{
                		itemPanel.setBackground(new Color(155, 158, 1));
                	}
                	else if(fechaVencimiento.isAfter(fechaActual))
                	{
                		itemPanel.setBackground(new Color(161, 16, 16));
                	}
                	else
                	{
                		itemPanel.setBackground(new Color(240, 240, 240));
                	} 
                }

                public void mouseExited(MouseEvent e) 
                {
                	if(tarea.getEstadoTarea() == 2)
                	{
                		itemPanel.setBackground(new Color(42, 178, 9));
                	}
                	else if(tarea.getEstadoTarea() == 1)
                	{
                		itemPanel.setBackground(new Color(202, 205, 3));
                	}
                	else if(fechaVencimiento.isAfter(fechaActual))
                	{
                		itemPanel.setBackground(new Color(201, 12, 12));
                	}
                	else
                	{
                		itemPanel.setBackground(new Color(255, 255, 255));
                	}   
                }

                public void mouseClicked(MouseEvent e) 
                {
                    JOptionPane.showMessageDialog(null,
                            "Tarea: " + tarea.getNombreTarea() +
                                    "\nFecha: " + tarea.getFechaTarea(),
                            "Detalle de la Tarea",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.print(id);
                }
            });
            // Añadir un poco de espacio entre los ítems
            contentPane.add(Box.createVerticalStrut(10));
            contentPane.add(itemPanel);
        }
        contentPane.revalidate();
        contentPane.repaint();
    }
}