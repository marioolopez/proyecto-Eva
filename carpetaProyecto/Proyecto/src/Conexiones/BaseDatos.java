<<<<<<<< HEAD:carpetaProyecto/Proyecto/src/paqueteprincipal/BaseDatos.java
package paqueteprincipal;
========
package Conexiones;
>>>>>>>> main:carpetaProyecto/Proyecto/src/Conexiones/BaseDatos.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class BaseDatos {
	private Connection c;
<<<<<<<< HEAD:carpetaProyecto/Proyecto/src/paqueteprincipal/BaseDatos.java
	public BaseDatos() throws SQLException, ClassNotFoundException{
========
	public BaseDatos() throws SQLException, ClassNotFoundException {
	}
	
	public void conexionBD() throws ClassNotFoundException, SQLException {
>>>>>>>> main:carpetaProyecto/Proyecto/src/Conexiones/BaseDatos.java
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost/gimnasio";
		c = DriverManager.getConnection(url, "root","");
		System.out.println("Conexion realidada!");
	}
	
	public Connection getC() {
		return c;
	}
	
	public void setC(Connection c) {
		this.c = c;
	}
	
	public void cerrarConex() throws SQLException {
		c.close();
	}
	
	public Statement crearStm() throws SQLException{
		Statement stm;
		stm = c.createStatement();
		return stm;
	}
	
	//sacar info
	public ResultSet ejecutarSQL1(String cad) throws SQLException{
		Statement stm = crearStm();
		ResultSet rs = stm.executeQuery(cad);
		return rs;
	}
	
	//modificar, eliminar e insertar info
	public void ejecutarSQL2(String cad) throws SQLException{
		Statement stm = crearStm();
		stm.executeUpdate(cad);
	}

}