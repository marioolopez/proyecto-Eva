package marcos;

import java.sql.SQLException;

import javax.swing.JTabbedPane;

import paqueteprincipal.cambiarVentanaCliente;
import paqueteprincipal.ventanaPrincipal;

public class VentanaMultipleCliente extends JTabbedPane {
    private VentanaAltaCliente ventanaAltaCliente;
    private VentanaBajaCliente ventanaBajaCliente;
    private VentanaModificacionCliente ventanaModificacionCliente;
    public VentanaMultipleCliente(ventanaPrincipal ventanaPrincipal) throws SQLException, ClassNotFoundException {
    	ventanaAltaCliente = new VentanaAltaCliente(ventanaPrincipal);
        ventanaBajaCliente = new VentanaBajaCliente(ventanaPrincipal);
        ventanaModificacionCliente = new VentanaModificacionCliente(ventanaPrincipal);
        this.addTab("ALTA CLIENTE", ventanaAltaCliente);
        this.addTab("BAJA CLIENTE", ventanaBajaCliente);
        this.addTab("MODIFICACIÓN CLIENTE", ventanaModificacionCliente);
        this.addChangeListener(new cambiarVentanaCliente(ventanaPrincipal));//hacer escuchador a la ventana, para que cuando cambie de ventana se cambien ciertas propiedades.
    }

    public VentanaAltaCliente getVentanaAltaCliente() {
        return this.ventanaAltaCliente;
    }

    public void setVentanaAltaCliente(VentanaAltaCliente ventanaAltaCliente) {
        this.ventanaAltaCliente = ventanaAltaCliente;
    }

    public VentanaBajaCliente getVentanaBajaCliente() {
        return this.ventanaBajaCliente;
    }

    public void setVentanaBajaCliente(VentanaBajaCliente ventanaBajaCliente) {
        this.ventanaBajaCliente = ventanaBajaCliente;
    }

	public VentanaModificacionCliente getVentanaModificacionCliente() {
		return ventanaModificacionCliente;
	}

	public void setVentanaModificacionCliente(VentanaModificacionCliente ventanaModificacionCliente) {
		this.ventanaModificacionCliente = ventanaModificacionCliente;
	}
    
    
}

