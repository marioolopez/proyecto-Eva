package Pedidos;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.BaseDatos;

public class ObjCompra {
	private String nombre;
	private int idProducto, cantidad;
	
	
	public ObjCompra(String nombre, int idProducto, int cantidad) {
		super();
		this.nombre = nombre;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	
	public void actualizaCompra(int idProductoSele, int cantidadSele, int idPedido) {
		String sql = "UPDATE compra SET idproducto='" + idProductoSele + "', cantidad='" + cantidadSele + "' WHERE idpedido='" + idPedido + "'";

		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			bs.conexionBD();
			bs.ejecutarSQL2(sql);
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al actualiza la compra en BD");
			e.printStackTrace();
		}
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public String toString() {
		return "ObjCompra [nombre=" + nombre + ", idProducto=" + idProducto + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
	
	
}
