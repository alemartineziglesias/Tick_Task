package tick_task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu_Proyectos extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnNuevoProyecto;
    @SuppressWarnings("unused")
	private Usuarios usuario;
    Datos datos = new Datos();

    public Menu_Proyectos(Usuarios usuario) 
    {
    	this.usuario = usuario;
        setTitle("Proyectos");
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
        contentPane.setBounds(x, y, panelWidth, panelHeight);
        contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
        contentPane.setBackground(new Color(0, 0, 0));
        add(contentPane);

        btnNuevoProyecto = new JButton("Crear proyecto");
        btnNuevoProyecto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Crear_Proyecto proyecto = new Crear_Proyecto(usuario);
				proyecto.setVisible(true);			
			}
		});
        btnNuevoProyecto.setBounds(((getWidth() - 160) / 2) - 8, y + panelHeight + 20, 160, 30); // Centrado debajo
        add(btnNuevoProyecto);

        List<Proyectos> proyectos = datos.obtenerProyectos();
        mostrarProyectos(proyectos);
    }


    public void mostrarProyectos(List<Proyectos> proyectos) 
    {
        contentPane.removeAll();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        for (Proyectos proyecto : proyectos) 
        {
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

            JLabel lblNombre = new JLabel(proyecto.getNombreProyecto());
            lblNombre.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
            lblNombre.setForeground(new Color(40, 40, 40));
            itemPanel.add(lblNombre);

            JLabel lblUsuario = new JLabel("Creado por: " + proyecto.getNombreUsuario());
            lblUsuario.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
            lblUsuario.setForeground(new Color(100, 100, 100));
            itemPanel.add(lblUsuario);

            itemPanel.addMouseListener(new MouseAdapter() 
            {
                public void mouseEntered(MouseEvent e) 
                {
                    itemPanel.setBackground(new Color(240, 240, 240));
                }

                public void mouseExited(MouseEvent e) 
                {
                    itemPanel.setBackground(new Color(255, 255, 255));
                }

                public void mouseClicked(MouseEvent e) 
                {
                    JOptionPane.showMessageDialog(null,
                            "Proyecto: " + proyecto.getNombreProyecto() +
                                    "\nUsuario: " + proyecto.getNombreUsuario(),
                            "Detalle del Proyecto",
                            JOptionPane.INFORMATION_MESSAGE);
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