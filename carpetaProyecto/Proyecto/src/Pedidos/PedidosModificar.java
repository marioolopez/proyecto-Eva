package Pedidos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Main.BaseDatos;

public class PedidosModificar extends JInternalFrame{
	private JPanel panelCliente, panelCompra;
	private JLabel txt1,txt2;
	private DefaultListModel listaClientes;
	private ArrayList<Cliente> listaClientesTotal;
	private JList listaCliente, listaCompra;
	private JScrollPane barra1,barra2;
	public PedidosModificar() {
		this.setLayout(new FlowLayout());
		listaClientes=new DefaultListModel<String>();
		listaClientesTotal=new ArrayList<Cliente>();
		clientesNombre();
		
		//DIBUJA
		datosMe();

	}
	
	
	public void datosMe() {
		panelCliente=new JPanel();
		panelCliente.setLayout(new BorderLayout());
		panelCliente.setPreferredSize(new Dimension(250,150));
			txt1=new JLabel("Lista clientes");
			panelCliente.add(txt1, BorderLayout.NORTH);
			listaCliente=new JList(listaClientes);
			listaCliente.addMouseListener(new AccionListaPedidosModificar(this));
			barra1=new JScrollPane(listaCliente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelCliente.add(barra1, BorderLayout.CENTER);
		this.add(panelCliente);
		
		panelCompra=new JPanel();
		panelCompra.setVisible(false);
		panelCompra.setLayout(new BorderLayout());
		panelCompra.setPreferredSize(new Dimension(250,150));
			txt2=new JLabel("Lista compras del cliente");
			panelCompra.add(txt2, BorderLayout.NORTH);
			listaCompra=new JList<String>();
			//listaCompra.addMouseListener(new AccionListaPedidosModificar(this));
			barra2=new JScrollPane(listaCompra, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelCompra.add(barra2, BorderLayout.CENTER);
		this.add(panelCompra);
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
	
	public void pedidosCliente() {
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
		return listaCompra;
	}


	public void setListaCompra(JList listaCompra) {
		this.listaCompra = listaCompra;
	}
	
	
	
}
