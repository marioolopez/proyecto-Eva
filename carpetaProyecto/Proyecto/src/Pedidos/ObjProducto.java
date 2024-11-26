package Pedidos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Main.BaseDatos;

public class ObjProducto {
	private ArrayList<ObjCompra> listaProductosTotal; //Almacena el nombre, su id y stock de toda bd
	private DefaultListModel<String> listaProductos;//Solo el nombre del producto de listaProductosTotal

	public ObjProducto () {
		this.listaProductosTotal = new ArrayList<ObjCompra>();
		this.listaProductos = new DefaultListModel<String>();
		cargarProductosBD();
	}

	//Busca todos los productos y los añade a la lista listaProductosTotal
	public void cargarProductosBD() { 
		String sql="SELECT nombre,id,stock FROM producto WHERE stock>0";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			
			while(result.next()) {
				ObjCompra productos=new ObjCompra(result.getString("nombre"), result.getInt("id"), result.getInt("stock"));
				listaProductosTotal.add(productos);
				listaProductos.addElement(productos.getNombre());
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error en listaProductosMet");
			e.printStackTrace();
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
	
	
	
}



