package Pedidos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import Main.BaseDatos;

public class ObjCliente {
	private String nombre;
	private int id;
	private DefaultListModel<String> listaClientes; //Muestra nombres
	private ArrayList<ObjCliente> listaClientesTotal; //Almacena todos los clientes
	
	public ObjCliente() {
		this.listaClientesTotal=new ArrayList<ObjCliente>();
		this.listaClientes=new DefaultListModel<String>();
		clientesNombre(); //Busca los nombres de los clientes registrados
	}
	
	public ObjCliente(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	
	
	public void clientesNombre() {
		String sql="SELECT nombre, id FROM cliente";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				ObjCliente cliente=new ObjCliente(result.getString("nombre"), result.getInt("id"));
				listaClientesTotal.add(cliente);
				listaClientes.addElement(cliente.getNombre());
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con clientesNombre");
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
	
	
	
	
}
