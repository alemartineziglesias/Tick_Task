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
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Menu_Proyectos extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JButton btnNuevoProyecto;
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

        // Calculo de la posición centrada
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

        btnNuevoProyecto = new JButton("Crear proyecto");
        btnNuevoProyecto.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                Crear_Proyecto proyecto = new Crear_Proyecto(usuario, Menu_Proyectos.this);
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
                private Timer holdTimer;
                private final int HOLD_DELAY = 800;

                @Override
                public void mousePressed(MouseEvent e) 
                {
                    holdTimer = new Timer(HOLD_DELAY, new ActionListener() 
                    {
                        @Override
                        public void actionPerformed(ActionEvent evt) 
                        {
                        	if(usuario.getIdUsuario() == proyecto.getIdUsuario())
                        	{
                        		holdTimer.stop();

                                int confirm = JOptionPane.showConfirmDialog(
                                    Menu_Proyectos.this,
                                    "¿Deseas eliminar el proyecto?",
                                    "Confirmar eliminación",
                                    JOptionPane.YES_NO_OPTION
                                );

                                if (confirm == JOptionPane.YES_OPTION) 
                                {
                                    boolean eliminado = datos.eliminarProyecto(proyecto.getIdProyecto());
                                    if (eliminado) 
                                    {
                                        JOptionPane.showMessageDialog(Menu_Proyectos.this, "Proyecto eliminado exitosamente.");
                                        mostrarProyectos(datos.obtenerProyectos()); // refrescar lista
                                    } 
                                    else 
                                    {
                                        JOptionPane.showMessageDialog(Menu_Proyectos.this, "Error al eliminar el proyecto.");
                                    }
                                }
                        	}
                        	else
                        	{
                        		Dialogo dialogo = new Dialogo("Error: el proyecto a eliminar no es tuyo");
            		            dialogo.setVisible(true);
                        	}
                        }
                    });
                    holdTimer.setRepeats(false);
                    holdTimer.start();
                }

                @Override
                public void mouseReleased(MouseEvent e) 
                {
                    if (holdTimer != null && holdTimer.isRunning()) 
                    {
                        holdTimer.stop(); // se interrumpe antes de tiempo → clic normal
                        // Abrir menú de tareas
                        Menu_Tareas tareas = new Menu_Tareas(usuario, proyecto.getIdProyecto(), proyecto.getNombreProyecto());
                        tareas.setVisible(true);
                        setVisible(false);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) 
                {
                    if (holdTimer != null && holdTimer.isRunning()) 
                    {
                        holdTimer.stop(); // cancelar si el mouse sale del área
                    }
                    itemPanel.setBackground(new Color(255, 255, 255));
                }

                @Override
                public void mouseEntered(MouseEvent e) 
                {
                    itemPanel.setBackground(new Color(240, 240, 240));
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