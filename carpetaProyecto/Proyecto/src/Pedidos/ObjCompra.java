package Pedidos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexiones.BaseDatos;
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
		String sql2 = "UPDATE producto SET stock= stock -'" + cantidadSele + "' WHERE id='" + idProductoSele + "'"; //Resto stock
		String sql3 = "UPDATE producto SET stock= stock +'" + cantidad + "' WHERE id='" + idProducto + "'";//Sumo stock
		
		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			bs.conexionBD();
			bs.ejecutarSQL2(sql);
			bs.ejecutarSQL2(sql2);//Actualizo stock
			bs.ejecutarSQL2(sql3);//Actualizo stock
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al actualiza la compra en BD");
			e.printStackTrace();
		}
	}
	
	public void eliminarCompra(int idPedido) {
	    String sql = "DELETE FROM compra WHERE idpedido = ? AND idproducto = ?";

		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			bs.conexionBD();
			PreparedStatement pstmt = bs.getC().prepareStatement(sql);
		    pstmt.setInt(1, idPedido);   // Establecer el valor para el primer parámetro (idpedido)
		    pstmt.setInt(2, idProducto);
	        int filasAfectadas = pstmt.executeUpdate();
	        //System.out.println("Filas afectadas: " + filasAfectadas);
	        pstmt.close();
	        bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al eliminar la compra en BD");
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
