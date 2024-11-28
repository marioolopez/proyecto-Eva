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

import Conexiones.BaseDatos;
import paqueteprincipal.ventanaPrincipal;

public class Pedidos extends JInternalFrame{
	private JLabel txt1,txt2,txt3,txt4;
	private JTextField id;
	private JCalendar calendario;
	private JPanel datos,botones, izq, izqArr, izqAba, centro, der;
	private JList listaCliente, listaCompra;
	private JScrollPane barra1,barra2;
	private JButton[] boton;
	private String[] botonesNom= {"Añadir", "eliminar"};
	private Compras compra;
	
	
	private ObjCliente gestionClientes; //Para gestion de clientes
	private ObjPedido gestionPedidos;
	private ObjProducto gestionProductos;
	

	//ARRIBA
	public Pedidos() {
		super();
		this.setLayout(new FlowLayout());
		
		gestionClientes= new ObjCliente();
		gestionPedidos=new ObjPedido();
		gestionProductos=new ObjProducto(); //Carga los productos

		datosMe();//Dibuja
		botonesMe();//Dibuja
		
		//Saco la idMax de pedido
		id.setText(String.valueOf(gestionPedidos.idMax()));
		
		compra=new Compras(this,gestionPedidos, gestionProductos);
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
					listaCliente=new JList<String>(gestionClientes.getListaClientes());//Lista 1
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
				listaCompra=new JList<String>(gestionPedidos.getListaPedidos());//Lista 2
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
			boton[i].addActionListener(new AccionPedidos(this));
			boton[i].setEnabled(false);
			botones.add(boton[i]);
		}
		this.add(botones);
	}

	public void activarBotones() {
		if(gestionPedidos.getListaPedidos().isEmpty()) {
			boton[0].setEnabled(false);
			boton[1].setEnabled(false);
		}else {
			boton[0].setEnabled(true);
			boton[1].setEnabled(true);
		}
	}
	
	public void eliminarCompra() { //Elimina la compra seleccionada
		int index=listaCompra.getSelectedIndex();
		ObjCompra compraEliminar=gestionPedidos.getListaComprasTotal().get(index); //Selecciona el objeto a eliminar
		//System.out.println("Compra a eliminar " + compraEliminar.toString());
		int cantidadCompra=compraEliminar.getCantidad();
		
		//Añado al stock global la cantidad de la compraEliminada
		for(ObjCompra a:gestionPedidos.getListaComprasTotal()) {
			if(a.getIdProducto()==compraEliminar.getIdProducto()) {
				a.setCantidad(a.getCantidad()+cantidadCompra);
			}
		}
		
		//Elimino la compra
		gestionPedidos.getListaComprasTotal().remove(index);
		gestionPedidos.getListaPedidos().remove(index);
		
		activarBotones(); //Desactivo o no los botones
	}
	
	
	//Crea el pedido
	public void crearPedido() {
		if(listaCliente.getSelectedValue()!=null) {
			int idPedido=Integer.parseInt(id.getText());
			int index=listaCliente.getSelectedIndex();
			ObjCliente cliente=gestionClientes.getListaClientesTotal().get(index);
			//System.out.println(cliente.toString());
			
			 //Fecha del calendario
	        java.util.Date fecha = calendario.getDate();
	        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());

	        //Fecha actual
	        LocalDate fechaLocal = LocalDate.now();
	        java.sql.Date fechaLocalSql = java.sql.Date.valueOf(fechaLocal);
			
			ObjPedido pedidoTerminado=new ObjPedido(idPedido,cliente.getId(), fechaSql, fechaLocalSql, gestionPedidos.getListaComprasTotal());
			//System.out.println(pedidoTerminado.toString());
			//System.out.println("a");
			gestionPedidos.crearPedido(pedidoTerminado);
			
			limpieza();
		}else {
			JOptionPane.showMessageDialog(this, "Indica un cliente");
		}
	}
	
	public void limpieza() {
		//Saco la idMax de pedido
		id.setText(String.valueOf(gestionPedidos.idMax()));
		gestionPedidos.getListaComprasTotal().clear();
		gestionPedidos.getListaPedidos().clear();
		listaCliente.clearSelection();
		listaCompra.clearSelection();
		activarBotones();
	}
	
	
	public JButton[] getBoton() {
		return boton;
	}

	public void setBoton(JButton[] boton) {
		this.boton = boton;
	}

	public JList getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(JList listaCliente) {
		this.listaCliente = listaCliente;
	}

	public JList getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(JList listaCompra) {
		this.listaCompra = listaCompra;
	}
	
	
	


	
	
	
	
	
	

	
	
	


	
	
	

}
