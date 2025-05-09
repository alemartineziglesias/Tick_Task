package tick_task;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Dialogo extends JDialog 
{
	private static final long serialVersionUID = 1L;

	public Dialogo(String mensaje) 
	{
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 99, 414, 40);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setText(mensaje);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		btnNewButton.setBounds(172, 199, 89, 40);
		btnNewButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		getContentPane().add(btnNewButton);
	}
}
