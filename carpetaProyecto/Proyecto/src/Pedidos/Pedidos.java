package Pedidos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import Main.BaseDatos;
import Main.ventanaPrincipal;

public class Pedidos extends JInternalFrame{
	private JLabel txt1,txt2,txt3,txt4;
	private JTextField id;
	private JCalendar calendario;
	private JPanel datos,botones, izq, izqArr, izqAba, centro, der;
	private JList listaCliente, listaCompra;
	private JScrollPane barra1,barra2;
	private JButton[] boton;
	private String[] botonesNom= {"Añadir", "eliminar", "modificar", "mostrar"};
	private Compras compra;
	
	
	private ObjCliente gestionClientes; //Para gestion de clientes
	private ObjPedido gestionPedidos;

	

	//ARRIBA
	public Pedidos() {
		super();
		this.setClosable(true);;
		this.setLayout(new FlowLayout());
		
		gestionClientes=new ObjCliente();
		gestionPedidos=new ObjPedido();
		
		datosMe();//Dibuja
		botonesMe();//Dibuja
		
		id.setText(String.valueOf(gestionPedidos.idMax()));
		
		compra=new Compras(this,gestionPedidos);
		compra.setPreferredSize(new Dimension(700,150));
		compra.setVisible(true);
		this.add(compra);
		
	}
	
	public void datosMe() {
		datos=new JPanel();
		
		datos.setLayout(new GridLayout(1,3,10,10));
			izq=new JPanel();
			izq.setLayout(new BorderLayout());
				izqArr=new JPanel();
				izqArr.setLayout(new BorderLayout());
					txt1=new JLabel("IdPedido");
					izqArr.add(txt1, BorderLayout.NORTH);
					id=new JTextField();
					id.setEditable(false);
				izqArr.add(id, BorderLayout.CENTER);
			izq.add(izqArr, BorderLayout.NORTH);
			
			izqAba=new JPanel();
			izqAba.setLayout(new BorderLayout());
				txt2=new JLabel("Cliente");
				izqAba.add(txt2,BorderLayout.NORTH);
					listaCliente=new JList<String>(gestionClientes.getListaClientes());
					barra1=new JScrollPane(listaCliente,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				izqAba.add(barra1, BorderLayout.CENTER);
			izq.add(izqAba, BorderLayout.CENTER);
		datos.add(izq);
			
			centro=new JPanel();
			centro.setLayout(new BorderLayout());
				txt3=new JLabel("Fecha de entrega");
				centro.add(txt3, BorderLayout.NORTH);
				calendario=new JCalendar();
				centro.add(calendario, BorderLayout.CENTER);
		datos.add(centro);
			
			der=new JPanel();
			der.setLayout(new BorderLayout());
				txt4=new JLabel("Id Compras");
				der.add(txt4, BorderLayout.NORTH);
				listaCompra=new JList<String>(gestionPedidos.getListaCompras());
				listaCompra.addMouseListener(new AccionListaPedidos(this));
				barra2=new JScrollPane(listaCompra,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				der.add(barra2, BorderLayout.CENTER);
		datos.add(der);
		datos.setPreferredSize(new Dimension(750,250));
		this.add(datos);

	}
	
	public void botonesMe() {
		botones=new JPanel();
		botones.setLayout(new GridLayout(1,4));
		boton=new JButton[botonesNom.length];
		for(int i=0; i<botonesNom.length; i++) {
			boton[i]=new JButton(botonesNom[i]);
			boton[i].addActionListener(new AccionPedidos( this));
			botones.add(boton[i]);
		}
		boton[0].setEnabled(false);
		boton[1].setEnabled(false);
		boton[1].addActionListener(new AccionPedidos(this));
		boton[2].setEnabled(false);
		boton[3].setEnabled(false);
		this.add(botones);
	}
	


	//FUNCIONALIDAD
	public void listaComprasMet() { //Actualiza y si hay algo activa el botón añadir
		gestionPedidos.getListaCompras().removeAllElements();
		for(ObjPedido a: gestionPedidos.getListaComprasTotal()) {
			gestionPedidos.getListaCompras().addElement(a.getNombre());
		}
		if(gestionPedidos.getListaComprasTotal().isEmpty()) {
			boton[0].setEnabled(false);
		}else {
			boton[0].setEnabled(true);
		}

	}
	
	public boolean validar() {
		if(listaCliente.getSelectedValue()==null) {
			JOptionPane.showMessageDialog(compra, "Selecciona un cliente");
			return false;
		}
		return true;
	}
	
	public void anadePedidoBD() {
		if(validar()) {
			String cliente= (String) listaCliente.getSelectedValue();
			int idCliente=0;
			for(ObjCliente a:gestionClientes.getListaClientesTotal()) {
				if(listaCliente.getSelectedValue().equals(a.getNombre())) {
					idCliente=a.getId();
				}
			}
			//Fecha del calendario
			java.util.Date fecha = calendario.getDate();
			java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
			gestionPedidos.anadirMetPedido(cliente, idCliente, fechaSql, id.getText(), gestionPedidos);
			
			reseteoMet();
		}
	}
		 
	//Reseteo
	public void reseteoMet() { 
		gestionPedidos.getListaCompras().clear();
		gestionPedidos.getListaComprasTotal().clear();
		id.setText(String.valueOf(Integer.parseInt(id.getText())+1));
	}
	
	//Elimina la compra de la lista y añade el stock restado anteriormente en la clase Compras, METODO compraRealizadaMet
	public void eliminarCompra() {
		if(listaCompra.getSelectedValue()!=null) {
			
			int index=listaCompra.getSelectedIndex(); //eL INDEX de listaCompras y listaComprasTotal es el mismo, se añaden en el mismo orden 
			int cantidad= gestionPedidos.getListaComprasTotal().get(index).getCantidad(); //Stock total actual
			int id=gestionPedidos.getListaComprasTotal().get(index).getId(); //Id producto selecicionado en el JLIst
			for(ObjPedido gestionCompras: gestionPedidos.getListaProductosTotal()) {
				if(gestionCompras.getId()==id) {
					System.out.println(gestionCompras.getCantidad());
					gestionCompras.setCantidad(gestionCompras.getCantidad()+cantidad);
					System.out.println(gestionCompras.getCantidad());
				}
			}
			gestionPedidos.getListaComprasTotal().get(index).getCantidad();
			gestionPedidos.getListaComprasTotal().remove(listaCompra.getSelectedIndex());
			listaComprasMet();
			listaCompra.clearSelection();
			boton[1].setEnabled(false);
		}
	}
	//Se activa el boton cuando se selecciona algo en la lista compra
	public void eliminarBoton() {
		boton[1].setEnabled(true);
	}
	

	public JList getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(JList listaCompra) {
		this.listaCompra = listaCompra;
	}
	
	
	
	
	
	


	
	
	

}
