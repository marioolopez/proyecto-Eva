package ParteMario;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
public class latderch extends JPanel{
	private JPanel p, p1, p2;
	latderch(){
		
		p1 = new JPanel(new GridLayout(2,2));
        ImageIcon im1 = new ImageIcon(new ImageIcon("img/pesa.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel img1 = new JLabel(im1);
        ImageIcon im2 = new ImageIcon(new ImageIcon("img/pesa2.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel img2 = new JLabel(im2);
        ImageIcon im3 = new ImageIcon(new ImageIcon("img/bola.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel img3 = new JLabel(im3);
        ImageIcon im4 = new ImageIcon(new ImageIcon("img/cuerda.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel img4 = new JLabel(im4);
        p1.add(img1);
        p1.add(img2);
        p1.add(img3);
        p1.add(img4);

		p2 = new JPanel(); //intentar meter el video
		
		
		p.add(p1);
		p.add(p2);
		this.add(p);
	}
	
}
