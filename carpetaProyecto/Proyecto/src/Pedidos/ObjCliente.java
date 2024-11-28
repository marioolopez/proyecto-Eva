package Pedidos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import Conexiones.BaseDatos;


public class ObjCliente {
	private String nombre;
	private int id;
	private DefaultListModel<String> listaClientes, listaPedidosCliente; //Muestra nombres
	private ArrayList<ObjCliente> listaClientesTotal; //Almacena todos los clientes
	private ArrayList<ObjPedido> listaPedidosClienteTotal;
	
	

	public ObjCliente() {
		super();
		this.listaClientes = new DefaultListModel<String>();
		this.listaClientesTotal = new  ArrayList<ObjCliente>();
		cargarClientesBD();
	}
	public ObjCliente(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.listaPedidosClienteTotal=new ArrayList<ObjPedido>();
		listaPedidosCliente=new DefaultListModel<String>();
	}
	 
	public void cargarClientesBD() { //Guarda los clientes de la BD
		String sql="SELECT nombre, id FROM cliente";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			bs.conexionBD();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				ObjCliente cliente=new ObjCliente(result.getString("nombre"), result.getInt("id"));
				listaClientesTotal.add(cliente);
				listaClientes.addElement(cliente.getNombre());
				//System.out.println(cliente.toString());
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al descargar los nombres de los clientes");
			e.printStackTrace();
		}
	}
	
	//Busca los pedidos del cliente seleccionado
	public void buscarPedidosCliente(int idCliente) {
		String sql="SELECT id, fechaEntrega, fechaRealizada FROM pedido WHERE idCliente= '" + idCliente + "'" ;
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			bs.conexionBD();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				int id=result.getInt("id");
				Date fechaEntrega=result.getDate("fechaEntrega");
				Date fechaRealizada=result.getDate("fechaRealizada");
				
				ObjPedido pedido=new ObjPedido(id,fechaEntrega,fechaRealizada);
				listaPedidosClienteTotal.add(pedido);
				listaPedidosCliente.addElement("{Id: " + id + " } " + fechaRealizada + "/" + fechaEntrega);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al buscarPedidosCliente ");
			e.printStackTrace();
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DefaultListModel<String> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(DefaultListModel<String> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public ArrayList<ObjCliente> getListaClientesTotal() {
		return listaClientesTotal;
	}
	public void setListaClientesTotal(ArrayList<ObjCliente> listaClientesTotal) {
		this.listaClientesTotal = listaClientesTotal;
	}
	
	public DefaultListModel<String> getListaPedidosCliente() {
		return listaPedidosCliente;
	}
	public void setListaPedidosCliente(DefaultListModel<String> listaPedidosCliente) {
		this.listaPedidosCliente = listaPedidosCliente;
	}
	public ArrayList<ObjPedido> getListaPedidosClienteTotal() {
		return listaPedidosClienteTotal;
	}
	public void setListaPedidosClienteTotal(ArrayList<ObjPedido> listaPedidosClienteTotal) {
		this.listaPedidosClienteTotal = listaPedidosClienteTotal;
	}
	@Override
	public String toString() {
		return "ObjCliente [nombre=" + nombre + ", id=" + id + ", listaClientes=" + listaClientes
				+ ", listaClientesTotal=" + listaClientesTotal + ", listaPedidosCliente=" + listaPedidosCliente + "]";
	}
}
