package alvaro;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Producto {
	
	private int id, stock, idProveedor;
	private String nombre;
	
	public Producto() {
		
	}
	protected void insertar(Connection conexion, String name, String stock, String proveedor) throws SQLException {
		Statement consulta=conexion.createStatement();
		ResultSet resultado=consulta.executeQuery("SELECT id from producto order by id desc limit 1");
		if(resultado.next()) {
			id=resultado.getInt(1)+1;
		}
		else id=1;
		consulta.executeUpdate("INSERT INTO producto VALUES ('"+id+"','"+name+"','"+stock+"','"+proveedor+"')");
		conexion.close();
	}
	protected void actualizarStock(Connection conexion, String codigo, int stock) throws SQLException {
		Statement consulta=conexion.createStatement();
		consulta.executeUpdate("UPDATE producto SET stock='"+stock+"' WHERE id='"+codigo+"'");
	}
	protected void eliminarStock(Connection conexion, String codigo) throws SQLException {
		
		//COMPROBAR QUE ESTE PRODUCTO NO ESTE EN NINGUN PEDIDO
		
		Statement consulta=conexion.createStatement();
		consulta.executeUpdate("DELETE from producto WHERE id='"+codigo+"'");
	}

	protected int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	protected int getStock() {
		return stock;
	}
	protected void setStock(int stock) {
		this.stock = stock;
	}
	protected int getIdProveedor() {
		return idProveedor;
	}
	protected void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	protected String getNombre() {
		return nombre;
	}
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

