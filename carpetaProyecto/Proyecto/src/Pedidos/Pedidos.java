package Pedidos;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class Pedidos extends JInternalFrame{
	private JLabel txt1,txt2,txt3;
	private JTextField id;
	private JCalendar calendario;
	private JPanel arriba;
	private GridBagConstraints gbc;
	
	public Pedidos() {
		super();
		this.setLayout(new GridLayout(2,1));
		
		arriba=new JPanel();
			arriba.setLayout(new GridBagLayout());
		    gbc = new GridBagConstraints();
		    
			txt1=new JLabel("IdPedido");
			gbc.gridx=0;
			gbc.gridy=1;
			arriba.add(txt1,gbc);
		this.add(arriba);
			
		
			
	        

	}
	

}
