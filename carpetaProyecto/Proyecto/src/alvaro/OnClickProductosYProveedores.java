package alvaro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Conexiones.BaseDatos;

public class OnClickProductosYProveedores implements ActionListener{

	private FrameProductosYProveedores ventanaMostrarMensaje;
	private FrameAñadir añadir;
	private BaseDatos objetoConexion;
	
	public OnClickProductosYProveedores(FrameProductosYProveedores ventana) {
		ventanaMostrarMensaje=ventana;
	}
	public OnClickProductosYProveedores(FrameAñadir añadir) {
		this.añadir=añadir;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		try {
			objetoConexion=new BaseDatos();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			objetoConexion.conexionBD();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (evento.getActionCommand()){
		case "MOSTRAR PRODUCTOS","MOSTRAR AGOTADOS","BUSCAR POR NOMBRE": 
			ventanaMostrarMensaje.rellenarTabla(evento.getActionCommand(), objetoConexion.getC());
			break;
		case "ACTUALIZAR STOCK":
			try {
				ventanaMostrarMensaje.actualizarStock(objetoConexion.getC());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "AÑADIR PRODUCTO":
			try {
				ventanaMostrarMensaje.completarInternalFrameProductos(objetoConexion.getC());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "AÑADIR PROVEEDOR":
			ventanaMostrarMensaje.completarInternalFrameProveedores();
			break;
		case "AÑADIR PRODUCTO ":
			if(añadir!=null) {
				try {
					añadir.insertarProducto(objetoConexion.getC());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;			
		case "ELIMINAR PRODUCTOS":
			try {
				ventanaMostrarMensaje.eliminarProducto(objetoConexion.getC());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "AÑADIR PROVEEDOR ":
			if(añadir!=null) {
				try {
					añadir.insertarProveedor(objetoConexion.getC());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
	}
}

