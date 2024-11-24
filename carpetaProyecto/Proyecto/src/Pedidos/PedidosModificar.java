package Pedidos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import Main.BaseDatos;

public class PedidosModificar extends JInternalFrame{
	private JPanel panelCliente, panelCompra, panelArriba, panelAbajo, panelCompras, panelEditar, panelBotones, panelEditarNorte, panelEditarCentro;
	private JLabel txt1,txt2,txt3,txt4,txt5;
	private DefaultListModel<String> listasCompras;


	private JList listaCliente, listaPedido, listaCompra;
	private JScrollPane barra1,barra2,barra3;
	private JSpinner cantidad;
	private JComboBox productos;
	private JButton guardar, eliminar;
	private ObjCliente gestionClientes;
	private ObjPedido gestionPedidos;
	
	public PedidosModificar() {
		this.setLayout(new GridLayout(2,1, 15 ,10));
		gestionClientes=new ObjCliente();
		gestionPedidos=new ObjPedido();
		
		//DIBUJA
		dibujoMe1();
		dibujoMe2();

	}
	
	
	public void dibujoMe1() {
		panelArriba=new JPanel();
		panelArriba.setLayout(new GridLayout(1,2));
		panelCliente=new JPanel();
			panelCliente.setBorder(new EmptyBorder(10,90,10,5));
			panelCliente.setLayout(new BorderLayout());
			txt1=new JLabel("Lista clientes");
			panelCliente.add(txt1, BorderLayout.NORTH);
			listaCliente=new JList(gestionClientes.getListaClientes());
			listaCliente.addMouseListener(new AccionListaPedidosModificar(this));
			barra1=new JScrollPane(listaCliente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelCliente.add(barra1, BorderLayout.CENTER);
		panelArriba.add(panelCliente);
			
		panelCompra=new JPanel();
			panelCompra.setBorder(new EmptyBorder(10,5,10,90));
			panelCompra.setLayout(new BorderLayout());
			txt2=new JLabel("Lista compras del cliente");
			panelCompra.add(txt2, BorderLayout.NORTH);
			listaPedido=new JList<String>(gestionPedidos.getListaPedidos());
			listaPedido.addMouseListener(new AccionListaPedidosModificar(this));
			barra2=new JScrollPane(listaPedido, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelCompra.add(barra2, BorderLayout.CENTER);
		panelArriba.add(panelCompra);
		this.add(panelArriba);
	}
	
	public void dibujoMe2() {
		panelAbajo=new JPanel();
		panelAbajo.setLayout(new GridLayout(1,2));
		panelCompras=new JPanel();
			panelCompras.setBorder(new EmptyBorder(10,90,40,5));
			panelCompras.setLayout(new BorderLayout());
			txt3=new JLabel("Compras");
			panelCompras.add(txt3, BorderLayout.NORTH);
			listaCompra=new JList();
			barra3=new JScrollPane(listaCompra,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelCompras.add(barra3, BorderLayout.CENTER);
		panelAbajo.add(panelCompras);
		
		panelEditar=new JPanel();
		panelEditar.setLayout(new GridLayout(3,1));
		panelEditar.setBorder(new EmptyBorder(60,5,40,90));
		panelEditarNorte=new JPanel();
			panelEditarNorte.setLayout(new FlowLayout());
			txt4=new JLabel("Producto");
			panelEditarNorte.add(txt4);
			productos=new JComboBox();
			productos.setPreferredSize(new Dimension(190,25));
			panelEditarNorte.add(productos);
		panelEditar.add(panelEditarNorte, BorderLayout.NORTH);
		panelEditarCentro=new JPanel();
			panelEditarCentro.setLayout(new FlowLayout());
			txt5=new JLabel("Cantidad de productos");
			panelEditarCentro.add(txt5);
			cantidad=new JSpinner();
			cantidad.setPreferredSize(new Dimension(50,25));
			panelEditarCentro.add(cantidad);
		panelEditar.add(panelEditarCentro, BorderLayout.CENTER);
		panelBotones=new JPanel();
			panelBotones.setLayout(new FlowLayout());
			guardar=new JButton("Guardar");
			panelBotones.add(guardar);
			eliminar=new JButton("Eliminar");
			panelBotones.add(eliminar);
			panelEditar.add(panelBotones, BorderLayout.SOUTH);
		panelAbajo.add(panelEditar);	
		this.add(panelAbajo);
		
	}	
	
	public void pedidosCliente() {		//Saca el id del cliente que se ha seleccionado
		int index=(int) listaCliente.getSelectedIndex();
		ObjCliente cliente=gestionClientes.getListaClientesTotal().get(index);
		int idCliente=cliente.getId();
		
		gestionPedidos.buscarPedidosCliente(idCliente);
	}
	



	

	
	public void buscarCompras() {
		String pedido[]= listaPedido.getSelectedValue().toString().split(" ");
		int id= Integer.parseInt(pedido[1]);
		//System.out.println(id);
		
		String sql="SELECT compra.cantidad, producto.nombre, pedido.fechaEntrega FROM compra JOIN producto ON compra.idProducto = producto.id "
				+ "JOIN pedido ON pedido.id = compra.idpedido WHERE compra.idpedido = '" + id + "'";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				int cantidad=result.getInt("cantidad");
				String nombre=result.getString("nombre");
				Date fechaEntrega=result.getDate("fechaEntrega");
				ObjCompra objCompra=new ObjCompra(nombre, cantidad, fechaEntrega);

			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con buscarCompras");
			e.printStackTrace();
		}
	}
	
	


	public JPanel getPanelCompra() {
		return panelCompra;
	}


	public void setPanelCompra(JPanel panelCompra) {
		this.panelCompra = panelCompra;
	}


	public JList getListaCliente() {
		return listaCliente;
	}


	public void setListaCliente(JList listaCliente) {
		this.listaCliente = listaCliente;
	}


	public JList getListaCompra() {
		return listaPedido;
	}


	public void setListaCompra(JList listaCompra) {
		this.listaPedido = listaCompra;
	}


	public JPanel getPanelArriba() {
		return panelArriba;
	}


	public void setPanelArriba(JPanel panelArriba) {
		this.panelArriba = panelArriba;
	}


	public JList getListaPedido() {
		return listaPedido;
	}


	public void setListaPedido(JList listaPedido) {
		this.listaPedido = listaPedido;
	}
	
	
	
}
