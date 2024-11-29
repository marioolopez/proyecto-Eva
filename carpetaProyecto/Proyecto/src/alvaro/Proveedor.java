package alvaro;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Proveedor {
	private int id, cif;
	private String nombre;
	
	public Proveedor() {
		
	}
	
	protected DefaultComboBoxModel<String> devolverProveedores(Connection conexion) throws SQLException{
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<String>();
		boolean existe=false;
		Statement consulta=conexion.createStatement();
		ResultSet resultado=consulta.executeQuery("SELECT * from proveedor");
		while(resultado.next()) {
			existe=true;
			modelo.addElement(resultado.getInt(1)+". ID: "+resultado.getString(2));
		}
		if(!existe) {
			modelo.addElement("No existen proveedores en la BD.");
		}
		return modelo;
	}
	
	protected void darDeAltaProveedorEnLaBaseDeDatos(Connection conexion, String name, int codigo) throws SQLException {
		Statement consulta=conexion.createStatement();
		ResultSet resultado=consulta.executeQuery("Select id from proveedor order by id desc limit 1");
		if(resultado.next()) {
			id=resultado.getInt(1)+1;
		}
		else id=1;
		consulta.executeUpdate("INSERT INTO proveedor VALUES ('"+id+"','"+name+"','"+codigo+"')");
		conexion.close();
	}
	
	protected void darDeBajaProveedorDeLaBaseDeDatos(Connection conexion, JFrame ventana, int [] ids) throws SQLException {
		Statement consulta=conexion.createStatement();
		String cadenaConLosIds="";
		for (int id=0;id<ids.length;id++) {
			consulta.executeUpdate("DELETE FROM proveedor WHERE id='"+ids[id]+"'");
			if(id!=0)
			cadenaConLosIds+=","+id;
			else cadenaConLosIds+=id;
		}
		if(ids.length>1)
		JOptionPane.showMessageDialog(ventana, "Proveedores con IDs: "+cadenaConLosIds+". eliminados de la Base de Datos.", "BAJA PROVEEDORES", JOptionPane.INFORMATION_MESSAGE);
		else JOptionPane.showMessageDialog(ventana, "Proveedor con ID: "+ids[0]+" eliminado de la Base de Datos.", "BAJA PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);
	}

	protected int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	protected int getCif() {
		return cif;
	}

	protected void setCif(int cif) {
		this.cif = cif;
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

