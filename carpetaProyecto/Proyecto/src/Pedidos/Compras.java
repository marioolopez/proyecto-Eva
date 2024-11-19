package Pedidos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Compras extends JPanel{
	private JLabel txt1,txt2,txt3;
	private JTextField id,cantidad;
	private JScrollPane barra;
	private JList productos;
	private JPanel datos, izq, centro, dere, botones;
	
	private JButton boton[];
	private String[] botonesNom= {"Añadir", "modificar", "eliminar"};
	
	public Compras() {
		datosMe();
		botonesMe();
	}
	public void datosMe() {
		this.setLayout(new BorderLayout());
		datos=new JPanel();
		datos.setLayout(new GridLayout(1,3));
			izq=new JPanel();
			izq.setLayout(new BorderLayout());
			txt1=new JLabel("Id compra");
			izq.add(txt1, BorderLayout.NORTH);
			id=new JTextField();
			izq.add(id, BorderLayout.CENTER);
		datos.add(izq);
		
			centro=new JPanel();
			centro.setLayout(new BorderLayout());
			txt2=new JLabel("Seleccionar productos");
			centro.add(txt2, BorderLayout.NORTH);
			productos=new JList();
			barra=new JScrollPane(productos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			centro.add(barra, BorderLayout.CENTER);
		datos.add(centro);
		
			dere=new JPanel();
			dere.setLayout(new BorderLayout());
			txt3=new JLabel("Cantidad");
			dere.add(txt3, BorderLayout.NORTH);
			cantidad=new JTextField();
			dere.add(cantidad,BorderLayout.CENTER);
		datos.add(dere);
		this.add(datos, BorderLayout.CENTER);
	}
	
	public void botonesMe() {
		botones=new JPanel();
		botones.setLayout(new GridLayout(1,5));
		botones.add(new JLabel());
		boton=new JButton[botonesNom.length];
		for(int i=0; i<botonesNom.length; i++) {
			boton[i]=new JButton(botonesNom[i]);
			//boton[i].addActionListener(new PedidosAccion(this));
			botones.add(boton[i]);
		}
		botones.add(new JLabel());
		boton[1].setEnabled(false);
		boton[2].setEnabled(false);

		this.add(botones, BorderLayout.SOUTH);
	}
}
