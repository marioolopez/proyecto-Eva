package Yoel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelParaContenidoAltas extends JPanel{
	private JLabel []lb;
	private JTextArea ta;
	private JTextField []tx;
PanelParaContenidoAltas()
{
	this.setLayout(new FlowLayout(FlowLayout.CENTER));
		lb=new JLabel[3];
		ta=new JTextArea();
		tx=new JTextField[2];
		String []titulos= {"Nombre:","Descrpcion:","Precio:"};
		for (int i = 0; i < lb.length; i++) {
			lb[i]=new JLabel(titulos[i]);
		}
		for (int i = 0; i < tx.length; i++) {
			tx[i]=new JTextField(20);
		}
		ta.setPreferredSize(new Dimension(400,200));
		this.add(lb[0]);
		this.add(tx[0]);
		this.add(lb[1]);
		this.add(ta);
		this.add(lb[2]);
		this.add(tx[1]);
}
}
