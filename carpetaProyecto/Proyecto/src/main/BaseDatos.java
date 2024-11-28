package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	public class BaseDatos {
	
	private Connection c;
	public BaseDatos(){
		
	}
	public void conexionBD() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost/gimnasio?serverTimezone=Europe/Madrid";
		c= DriverManager.getConnection(url,"root","");		
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
	public ResultSet ejecutarSQL1(String cad) throws SQLException{
		Statement stm = crearStm();
		ResultSet rs = stm.executeQuery(cad);
		return rs;
	}
	public void ejecutarSQL2(String cad) throws SQLException{
		Statement stm = crearStm();
		 int filasAfectadas = stm.executeUpdate(cad);
		/**
		System.out.println("----INFO----");
		System.out.println("Consulta SQL generada: " + cad);
		System.out.println("Filas afectadas: " + filasAfectadas);
		**/
	}
	
	}
