package paqueteprincipal;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import Conexiones.BaseDatos;

public class onClick implements ActionListener {
    private ventanaPrincipal ventanaPrincipal;
    
    public onClick(ventanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    public void insertarCliente() throws ClassNotFoundException {//en este metodo doy de alta al cliente.
    	BaseDatos bd = null;
		try {
			bd = new BaseDatos();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bd.conexionBD();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			bd.crearStm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int id = 0;
		try {
			id = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().sacarUltimoId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String nombre = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().getCajaNombreCliente().getText();
    	String telefono = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().getCajaTelefonoCliente().getText();
    	String dni = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().getCajaDniCliente().getText();
    	int edad = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().getBarraEdadCliente().getValue();
    	int idtarifa = Integer.parseInt(ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().getCajaIdTarifaCliente().getText());
    	int sexo;
    	if(ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().getBtnHombre().isSelected()) {
    		sexo = 1;
    	}else {
    		sexo = 0;
    	}
    	try {
			bd.ejecutarSQL2("INSERT INTO cliente VALUES('"+id+"','"+nombre+"','"+telefono+"','"+dni.toUpperCase()+"','"+edad+"','"+idtarifa+"','"+sexo+"')");
			JOptionPane.showMessageDialog(null, "se ha dado de alta correctamente","correcto",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "no se ha podido dar de alta a este cliente","error",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
    	
    	try {
			bd.cerrarConex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public int buscarPorIdBaja() {
    	BaseDatos bd = null;
		try {
			bd = new BaseDatos();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bd.conexionBD();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			bd.crearStm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int id = 0;
    	String idTexto;
    	   
    	idTexto = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getCajaIdCliente().getText();
    	if (!idTexto.isEmpty()) {
    	   try {
    	       	id = Integer.parseInt(idTexto);
    	   } catch (NumberFormatException e) {
    	        JOptionPane.showMessageDialog(null, "El texto en la caja de ID no es un número válido", "error", JOptionPane.ERROR_MESSAGE);
    	   }
    	} else {
    	    JOptionPane.showMessageDialog(null, "La caja de ID está vacía en la pestaña Baja Cliente", "error", JOptionPane.ERROR_MESSAGE);
    	}
    	

		try {
			ResultSet rs = bd.ejecutarSQL1("SELECT id from cliente where id = '"+id+"'");
			if(rs.next()) {
				id = rs.getInt(1);
			}else {
				id = 0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			bd.cerrarConex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return id;
    }
    
    public int buscarPorIdModificacion() {
    	BaseDatos bd = null;
		try {
			bd = new BaseDatos();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bd.conexionBD();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			bd.crearStm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int id = 0;
    	String idTexto;
    	   
    	idTexto = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getCajaIdCliente().getText();
    	    
    	if (!idTexto.isEmpty()) {
    		try {
    			id = Integer.parseInt(idTexto);
    		} catch (NumberFormatException e) {
    	        JOptionPane.showMessageDialog(null, "El texto en la caja de ID no es un número válido", "error", JOptionPane.ERROR_MESSAGE);
    		}
    	} else {
    		JOptionPane.showMessageDialog(null, "La caja de ID está vacía en la pestaña modificacion Cliente", "error", JOptionPane.ERROR_MESSAGE);
    	}
    	

		try {
			ResultSet rs = bd.ejecutarSQL1("SELECT id from cliente where id = '"+id+"'");
			if(rs.next()) {
				id = rs.getInt(1);
			}else {
				id = 0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			bd.cerrarConex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return id;
    }
    
    
    public void darDeBajaCliente() {
    	BaseDatos bd = null;
		try {
			bd = new BaseDatos();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bd.conexionBD();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			bd.crearStm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		try {
			bd.ejecutarSQL2("DELETE FROM cliente where id = '"+buscarPorIdBaja()+"'");
			JOptionPane.showMessageDialog(null, "se ha dado de baja correctamente","correcto",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "no se ha podido dar de baja a este cliente","error",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
    	
    	try {
			bd.cerrarConex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void modificarCliente() {
    	BaseDatos bd = null;
		try {
			bd = new BaseDatos();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bd.conexionBD();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			bd.crearStm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String nombre = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getCajaNombreCliente().getText();
    	String telefono = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getCajaTelefonoCliente().getText();
    	String dni = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getCajaDniCliente().getText();
    	int edad = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBarraEdadCliente().getValue();
    	int idtarifa = Integer.parseInt(ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getCajaIdTarifaCliente().getText());
    	int sexo = 100;
    	if(ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBtnHombre().isSelected()) {
			sexo = 1;
		}else if(ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBtnMujer().isSelected()){
			sexo = 0;
		}
    	try {
			bd.ejecutarSQL2("UPDATE cliente SET nombre = '"+nombre+"',telefono = '"+telefono+"',dni = '"+dni+"',edad = '"+edad+"', idtarifa = '"+idtarifa+"', sexo = '"+sexo+"' WHERE id = '"+buscarPorIdModificacion()+"'");
			JOptionPane.showMessageDialog(null, "se ha modificado correctamente","correcto",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "no se ha podido modificar a este cliente","error",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
    	
    	try {
			bd.cerrarConex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "botonAltaCliente":
            	//si todas las validaciones son correctas se inserta
            	if(ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().validarFormulario()) {
            		try {
        				insertarCliente();
        				ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().resetearVentanaAlta();//una vez ya se haya dado de alta
        			} catch (ClassNotFoundException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
            	}
			break;
            case "botonBuscarCliente":
            	int pestañaActiva = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getSelectedIndex();
            	if(pestañaActiva == 1) {
            		if(buscarPorIdBaja() != 0) {//si el id no es 0(es decir, si el id existe en la base de datos). EN BAJA Y MODFIFICACION
                		//BAJA
            			ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getCajaIdCliente().setForeground(Color.BLACK);
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getCajaIdCliente().setEditable(false);//DESACTIVAR LA CAJA DEL ID
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getBotonBuscarCliente().setEnabled(false);//DESACTIVAR BOTON DE BUSCAR
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getBotonBajaCliente().setEnabled(true);//ACTIVAR BOTON DE BAJA
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().cambiarColoresBaja();//cambiar los colores a verde
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().rellenarTextoVentanaBaja();//autocompleto las cajas de texto con lo de la base de datos de ese id
                	}else {
                		JOptionPane.showMessageDialog(null, "no existe ese id en la base de datos en la ventana baja","error",JOptionPane.INFORMATION_MESSAGE);
                	}
            	}else if(pestañaActiva == 2) {
            		if(buscarPorIdModificacion() != 0) {//si el id no es 0(es decir, si el id existe en la base de datos). EN MODFIFICACION
                		//MODIFICACION.
            			ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getCajaIdCliente().setForeground(Color.BLACK);
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getCajaIdCliente().setEditable(false);//DESACTIVAR LA CAJA DEL ID
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBotonBuscarCliente().setEnabled(false);//DESACTIVAR BOTON DE BUSCAR
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBotonModificacionCliente().setEnabled(true);//ACTIVAR BOTON DE MODIFICACION
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getBotonModificacionCliente().setBackground(Color.RED);//CAMBIAR COLOR A ROJO OTRA VEZ
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().cambiarColoresModificacion();//cambiar los colores a verde
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().rellenarTextoVentanaModificacion();//autocompleto las cajas de texto con lo de la base de datos de ese id
                		  
                		//ACTIVAR RADIO BUTTONS
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBtnHombre().setEnabled(true);
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBtnMujer().setEnabled(true);
                		//ACTIVAR CAJAS DE TEXTO PARA MODIFICAR MENOS LA DEL ID.
                		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().activarComponentesModificacion();
            		}else {
                		JOptionPane.showMessageDialog(null, "no existe ese id en la base de datos en la ventana modificacion","error",JOptionPane.INFORMATION_MESSAGE);
                	}
            	}
            break;
            case "botonBajaCliente":
            	darDeBajaCliente();
            	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().resetearVentanaBaja();//una vez ya se haya dado de baja
            	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getBotonBuscarCliente().setEnabled(true);//ACTIVAR BOTON DE BUSCAR
        		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getBotonBajaCliente().setEnabled(false);//DESACTIVAR BOTON DE BAJA
        		//LIMPIAR LOS RADIO BUTTONS
        		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().getGrupoBtns().clearSelection();
        		break;
            case "botonModificacionCliente":
            	//if todo esta validado que se modifique.
            	if(ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().validarFormulario()) {
            		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBotonBuscarCliente().setEnabled(true);//ACTIVAR BOTON DE BUSCAR
            		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBotonModificacionCliente().setEnabled(false);//DESACTIVAR BOTON DE BAJA
            		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getBotonModificacionCliente().setBackground(Color.RED);//CAMBIAR COLOR A ROJO OTRA VEZ

            		//MODIFIFACION:
            		modificarCliente();
            		//LIMPIAR LOS RADIO BUTTONS
            		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().getGrupoBtns().clearSelection();
            		//RESETEAR VENTANA
            		ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().resetearVentanaModificacion();
            	}
        		break;
        }
    }
}
