package ParteMario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
public class latderch extends JPanel{
	private JPanel p1, p2;
	private JButton insertar, borrar;
	latderch(){
		 this.setLayout(new GridLayout(2, 1));
	     p1 = new JPanel(new GridLayout(2, 2));
	     p1.setBackground(new Color(240, 240, 230)); 
	     
	     ImageIcon im1 = new ImageIcon(new ImageIcon("img/pesa.jpg").getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
	     JLabel img1 = new JLabel(im1);
	     ImageIcon im2 = new ImageIcon(new ImageIcon("img/pesa2.jpg").getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
	     JLabel img2 = new JLabel(im2);
	     ImageIcon im3 = new ImageIcon(new ImageIcon("img/bola.jpg").getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
	     JLabel img3 = new JLabel(im3);
	     ImageIcon im4 = new ImageIcon(new ImageIcon("img/cuerda.jpg").getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
	     JLabel img4 = new JLabel(im4);

	     p1.add(img1);
	     p1.add(img2);
	     p1.add(img3);
	     p1.add(img4);

	     //------------------------------------------------------------------------------------------------------------------------
	        
	     p2 = new JPanel(new GridLayout(3, 2));  //panel para los botones y etiquetas
	     p2.setBackground(new Color(240, 240, 230)); 
	     
	     ImageIcon imgC = new ImageIcon(new ImageIcon("img/cuerda2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH)); //imagen 1
	     JLabel l1 = new JLabel(imgC);
	     
	        
	     ImageIcon imgA = new ImageIcon(new ImageIcon("img/cuerda2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH)); //imagen 2
	     JLabel l2 = new JLabel(imgA);
	       
	     
	     JPanel insertarPanel = new JPanel(new GridBagLayout()); //boton insertar
	     insertarPanel.setBackground(new Color(240, 240, 230)); 
	     insertar = new JButton("Insertar");
	     insertar.setPreferredSize(new Dimension(120, 30));
	     insertarPanel.add(insertar);
	     
	    
	     JLabel l = new JLabel("cx"); //vacio
	     
	     
	     JPanel borrarPanel = new JPanel(new GridBagLayout()); //boton borrar
	     borrarPanel.setBackground(new Color(240, 240, 230)); 
	     borrar = new JButton("Borrar");
	     borrar.setPreferredSize(new Dimension(120, 30));
	     borrarPanel.add(borrar);
	     
	     
	     JLabel ls = new JLabel("fd"); //vacio
	     
	        
	     p2.add(l1); //img
	     p2.add(l2); //img
	     p2.add(insertarPanel); //boton
	     p2.add(l);
	     p2.add(borrarPanel);
	     p2.add(ls);
  
	     this.add(p1);
	     this.add(p2);
	}	
}