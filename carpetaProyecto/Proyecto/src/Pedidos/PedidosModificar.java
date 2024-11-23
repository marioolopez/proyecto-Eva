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
	private DefaultListModel<String> listaClientes, listaCompras;
	private ArrayList<Cliente> listaClientesTotal;
	private ArrayList<ObjPedido> listaComprasTotal;
	private JList listaCliente, listaPedido, listaCompra;
	private JScrollPane barra1,barra2,barra3;
	private JSpinner cantidad;
	private JComboBox productos;
	private JButton guardar, eliminar;
	public PedidosModificar() {
		this.setLayout(new GridLayout(2,1, 15 ,10));

		listaClientes=new DefaultListModel<String>();
		listaClientesTotal=new ArrayList<Cliente>();
		listaComprasTotal=new ArrayList<ObjPedido>();
		listaCompras=new DefaultListModel<String>();
		clientesNombre();
		
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
			listaCliente=new JList(listaClientes);
			listaCliente.addMouseListener(new AccionListaPedidosModificar(this));
			barra1=new JScrollPane(listaCliente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelCliente.add(barra1, BorderLayout.CENTER);
		panelArriba.add(panelCliente);
			
		panelCompra=new JPanel();
			panelCompra.setBorder(new EmptyBorder(10,5,10,90));
			panelCompra.setLayout(new BorderLayout());
			txt2=new JLabel("Lista compras del cliente");
			panelCompra.add(txt2, BorderLayout.NORTH);
			listaPedido=new JList<String>(listaCompras);
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
	
	
	


	public void clientesNombre() {
		String sql="SELECT nombre, id FROM cliente";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				Cliente cliente=new Cliente(result.getString("nombre"), result.getInt("id"));
				listaClientesTotal.add(cliente);
				listaClientes.addElement(cliente.getNombre());
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con el idMax");
			e.printStackTrace();
		}
	}
	
	//Busca pedidos del cliente seleccionado
	public void pedidosCliente() { 
		//Saca el id del cliente que se ha seleccionado
		int index=(int) listaCliente.getSelectedIndex();
		Cliente cliente=listaClientesTotal.get(index);
		int idCliente=cliente.getId();
		//Limpia
		listaComprasTotal.clear();
		listaCompras.clear();
		//Busca en db
		String sql="SELECT id, fecha FROM pedido WHERE id= '" + idCliente + "'" ;
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				int idPedido=result.getInt("id");
				Date fecha=result.getDate("fecha");
				ObjPedido objePedido=new ObjPedido(idPedido,fecha);
				listaComprasTotal.add(objePedido);
				listaCompras.addElement(idPedido + " / " + fecha);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con el idMax");
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
	
	
	
}
