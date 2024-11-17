package ParteMario;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.*;
public class latizq extends JPanel{
	private JPanel p, p1, p2;
	latizq(){
		p = new JPanel(new GridLayout(2,1));
		
		p1 = new JPanel();
		JLabel l1 = new JLabel("SHBFH");
		p1.add(l1);
		p2 = new JPanel();
		JLabel l2 = new JLabel("FHWSCJN");
		p2.add(l2);
		p.add(p1);
		p.add(p2);
		this.add(p);
	}
}