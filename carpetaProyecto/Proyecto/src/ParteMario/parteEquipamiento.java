package ParteMario;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
public class parteEquipamiento extends JInternalFrame {
	private JPanel p, p1, p2;
	public parteEquipamiento() { //hay que hacer publico  el constructor cuando quieres usar otro PACKAGE
		 p = new JPanel(new BorderLayout());
		 
		 latizq izquierda = new latizq();
	     latderch derecha = new latderch();
		 
	     p.add(izquierda, BorderLayout.WEST); //panel izquierdo a la izquierda en otro panel
	     p.add(derecha, BorderLayout.CENTER); //panel derecho a la derecha en otro panel
	     this.add(p); 
	     this.setVisible(true); 
	}
	
	public JPanel getP() {
		return p;
	}
	public void setP(JPanel p) {
		this.p = p;
	}
	public JPanel getP1() {
		return p1;
	}
	public void setP1(JPanel p1) {
		this.p1 = p1;
	}
	public JPanel getP2() {
		return p2;
	}
	public void setP2(JPanel p2) {
		this.p2 = p2;
	}
}







































/* p = new JPanel(new GridLayout(2,2));
	
		 p1 = new JPanel(); //hacer esta parte mañana
		
		 
		 
		 
		 p2 = new JPanel(new GridLayout(2,2));
		 ImageIcon img1 = new ImageIcon(new ImageIcon("img/pesa.jpg").getImage().getScaledInstance(190, 130, Image.SCALE_SMOOTH));
		 JLabel im1 = new JLabel(img1);
		 ImageIcon img2 = new ImageIcon(new ImageIcon("img/pesa2.jpg").getImage().getScaledInstance(190, 130, Image.SCALE_SMOOTH));
		 JLabel im2 = new JLabel(img2);
		 ImageIcon img3 = new ImageIcon(new ImageIcon("img/cuerda.jpg").getImage().getScaledInstance(190, 130, Image.SCALE_SMOOTH));
		 JLabel im3 = new JLabel(img3);
		 ImageIcon img4 = new ImageIcon(new ImageIcon("img/bola.jpg").getImage().getScaledInstance(190, 130, Image.SCALE_SMOOTH));
		 JLabel im4 = new JLabel(img4);
		 p2.add(im1);
		 p2.add(im2);
		 p2.add(im3);
		 p2.add(im4);
		 
		 
		 
		 p3 = new JPanel(); //hacer esta parte mañana
	
		 
		 
		 
		 
		 
		 p4 = new JPanel(); //meter un JAR MediaPlayer
		 
		 
		 
		 
		 
		 
		 p.add(p1);
		 p.add(p2);
		 p.add(p3);
		 p.add(p4);

	     this.add(p); 
	     this.setVisible(true); */


