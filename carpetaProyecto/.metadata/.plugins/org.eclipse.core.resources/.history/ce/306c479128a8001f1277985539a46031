package ParteMario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.SQLException;
import javax.swing.*;
public class latderch extends JPanel {
	private JPanel p1, p2;
	private JLabel nombreMant;
	private JTextField nomMantTx;
	private latizq izq;
	private JButton insertar, borrar;
	public latderch(latizq latizq) throws ClassNotFoundException, SQLException{
		 this.setLayout(new GridLayout(2, 1));
		 this.izq = latizq;

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

	     //-----------------------------------------------------------------------------------------------------------------------------
	        
	     //otro panel nuevo(botones)
	     p2 = new JPanel(new GridLayout(5,2));
	     
	     
	     JLabel l1 = new JLabel();//espacio en gridLayout
	     JLabel l2 = new JLabel();//espacio en gridLayout
	     p2.add(l1);
	     p2.add(l2);
	     
	     p2.setBorder(BorderFactory.createTitledBorder("ALTA MANTENEDOR DEL EQUIPAMIENTO"));
	     p2.setBackground(new Color(240, 240, 230)); 
	     nombreMant = new JLabel("Nombre del Mantenedor:", SwingConstants.CENTER);
	     nomMantTx = new JTextField();
	     p2.add(nombreMant);
	     p2.add(nomMantTx);
	     
	     JLabel l3 = new JLabel();//espacio en gridLayout
	     JLabel l4 = new JLabel();//espacio en gridLayout
	     p2.add(l3);
	     p2.add(l4);
	     
	     JPanel pabajo = new JPanel(new GridBagLayout());
	     pabajo.setBackground(new Color(240, 240, 230)); 
	     insertar = new JButton("Insertar Mantenedor");
	     insertar.setActionCommand("botonInsertar");
	     insertar.setPreferredSize(new Dimension(150, 40));
	     insertar.addActionListener(new accionMantenimiento(this));
	     pabajo.add(insertar);
	     p2.add(pabajo);
	     
	     
	     
	     JPanel pabajo2 = new JPanel(new GridBagLayout());
	     pabajo2.setBackground(new Color(240, 240, 230)); 
	     borrar = new JButton("Borrar Todo");
	     borrar.setActionCommand("botonBorrar");
	     borrar.setPreferredSize(new Dimension(120, 40));
	     borrar.addActionListener(new accionMantenimiento(this));
	     pabajo2.add(borrar);
	     p2.add(pabajo2);
	     
	     JLabel l5 = new JLabel();//espacio en gridLayout
	     JLabel l6 = new JLabel();//espacio en gridLayout
	     p2.add(l5);
	     p2.add(l6);

	     this.add(p1);
	     this.add(p2);
	    
	}
	public latizq getIzq() {
		return izq;
	}
	public void setIzq(latizq izq) {
		this.izq = izq;
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
	public JLabel getNombreMant() {
		return nombreMant;
	}
	public void setNombreMant(JLabel nombreMant) {
		this.nombreMant = nombreMant;
	}
	public JTextField getNomMantTx() {
		return nomMantTx;
	}
	public void setNomMantTx(JTextField nomMantTx) {
		this.nomMantTx = nomMantTx;
	}
}