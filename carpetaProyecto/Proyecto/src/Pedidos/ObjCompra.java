package Pedidos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Main.BaseDatos;

public class ObjCompra {
	private String nombre;
	private int id,cantidad;
	private Date fechaEntrega;
	
	private ArrayList<ObjCompra> listaProductosTotal; //Almacena el nombre, su id y stock de toda bd
	private DefaultListModel<String> listaProductos;//Solo el nombre del producto de listaProductosTotal
	private ArrayList<ObjCompra> listaComprasTotal; //Almacena los objetos con el id, nombre y cantidad seleccionada por el usuario
	private DefaultListModel<String> listaCompras; //Muestra el nombre por la lista de listaComprasTotal
	
	public ObjCompra() { //GestionCompras
		super();
		this.listaProductosTotal= new ArrayList<ObjCompra>();
		this.listaProductos= new DefaultListModel<String>();
		this.listaCompras = new DefaultListModel<String>();
		this.listaComprasTotal = new ArrayList<ObjCompra>();
		listaProductosMet(); 
	}

	public ObjCompra(String nombre, int id, int cantidad) { //Para crear la compra q realiza el cliente
		super();
		this.nombre = nombre;
		this.id = id;
		this.cantidad = cantidad;
	}
	
	public ObjCompra(String nombre, int cantidad, Date fechaEntrega) { 
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.fechaEntrega=fechaEntrega;
	}
	
	
	public void listaProductosMet() { //Busca todos los productos y los añade a la lista listaProductosTotal
		String sql="SELECT nombre,id,stock FROM producto WHERE stock>0";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			
			while(result.next()) {
				ObjCompra productos=new ObjCompra(result.getString("nombre"), result.getInt("id"), result.getInt("stock"));
				listaProductosTotal.add(productos);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error en listaProductosMet");
			e.printStackTrace();
		}
		//Añade los nombres a listaProductos
		for(ObjCompra nombres:listaProductosTotal ) {
			listaProductos.addElement(nombres.getNombre());
		}
	}
	public ArrayList<ObjCompra> getListaProductosTotal() {
		return listaProductosTotal;
	}

	public void setListaProductosTotal(ArrayList<ObjCompra> listaProductosTotal) {
		this.listaProductosTotal = listaProductosTotal;
	}

	public DefaultListModel<String> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(DefaultListModel<String> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public ArrayList<ObjCompra> getListaComprasTotal() {
		return listaComprasTotal;
	}

	public void setListaComprasTotal(ArrayList<ObjCompra> listaComprasTotal) {
		this.listaComprasTotal = listaComprasTotal;
	}

	public DefaultListModel<String> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(DefaultListModel<String> listaCompras) {
		this.listaCompras = listaCompras;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	@Override
	public String toString() {
		return "ObjeCompra [nombre=" + nombre + ", id=" + id + ", cantidad=" + cantidad + "]";
	}

	
	
}
