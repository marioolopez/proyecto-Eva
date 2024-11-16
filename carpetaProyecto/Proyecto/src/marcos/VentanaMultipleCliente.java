package marcos;

import javax.swing.JTabbedPane;

public class VentanaMultipleCliente extends JTabbedPane {
    private VentanaAltaCliente ventanaAltaCliente;
    private VentanaBajaCliente ventanaBajaCliente;

    public VentanaMultipleCliente(ventanaPrincipal ventanaPrincipal) {
        this.ventanaAltaCliente = new VentanaAltaCliente(ventanaPrincipal);
        this.ventanaBajaCliente = new VentanaBajaCliente(ventanaPrincipal);
        this.addTab("ALTA CLIENTE", this.ventanaAltaCliente);
        this.addTab("BAJA CLIENTE", this.ventanaBajaCliente);
        this.addChangeListener(new cambiarVentanaCliente(ventanaPrincipal));
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
}

