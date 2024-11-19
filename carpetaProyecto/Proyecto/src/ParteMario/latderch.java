package ParteMario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.SQLException;
import javax.swing.*;
public class latderch extends JPanel{
	private JPanel p1, p2;
	private JButton insertar, borrar;
	private latizq latizq;
	public latderch(latizq izq) throws ClassNotFoundException, SQLException{
		 this.latizq = izq; //uso la instancia pasada		 
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
	     
	     ImageIcon imgC = new ImageIcon(new ImageIcon("img/cuerda2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH));
	     JLabel l1 = new JLabel(imgC);
	     
	        
	     ImageIcon imgA = new ImageIcon(new ImageIcon("img/cuerda2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH));
	     JLabel l2 = new JLabel(imgA);
	       
	     
	     JPanel insertarPanel = new JPanel(new GridBagLayout()); //boton insertar
	     insertarPanel.setBackground(new Color(240, 240, 230)); 
	     insertar = new JButton("Insertar");
	     insertar.setActionCommand("botonInsertar");
	     insertar.addActionListener(new accionEquipamiento(this));
	     insertar.setPreferredSize(new Dimension(120, 30));
	     insertarPanel.add(insertar);
	     
	    
	     JLabel l = new JLabel(""); //vacio

	     
	     JPanel borrarPanel = new JPanel(new GridBagLayout()); //boton borrar
	     borrarPanel.setBackground(new Color(240, 240, 230)); 
	     borrar = new JButton("Borrar");
	     borrar.setActionCommand("botonBorrar");
	     borrar.addActionListener(new accionEquipamiento(this));
	     borrar.setPreferredSize(new Dimension(120, 30));
	     borrarPanel.add(borrar);
	     
	     
	     JLabel ls = new JLabel(""); //vacio
	     
	        
	     p2.add(l1);
	     p2.add(l2);
	     p2.add(insertarPanel);
	     p2.add(l);
	     p2.add(borrarPanel);
	     p2.add(ls);
  
	     this.add(p1);
	     this.add(p2);
	}
	
	
	public latizq getLatizq() {
		return latizq;
	}
	public void setLatizq(latizq latizq) {
		this.latizq = latizq;
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
	public JButton getInsertar() {
		return insertar;
	}
	public void setInsertar(JButton insertar) {
		this.insertar = insertar;
	}
	public JButton getBorrar() {
		return borrar;
	}
	public void setBorrar(JButton borrar) {
		this.borrar = borrar;
	}	
}