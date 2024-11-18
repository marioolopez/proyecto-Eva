package marcos;



import java.sql.SQLException;

import javax.swing.JInternalFrame;

import porDefecto.ventanaPrincipal;
public class VentanaCliente extends JInternalFrame {
    private VentanaMultipleCliente ventanaMultipleCliente;
    
    public VentanaCliente(ventanaPrincipal ventanaPrincipal) throws SQLException, ClassNotFoundException {

        this.ventanaMultipleCliente = new VentanaMultipleCliente(ventanaPrincipal);
        this.getContentPane().add(this.ventanaMultipleCliente);
    }
    
    

    public VentanaMultipleCliente getVentanaMultipleCliente() {
        return this.ventanaMultipleCliente;
    }

    public void setVentanaMultipleCliente(VentanaMultipleCliente ventanaMultipleCliente) {
        this.ventanaMultipleCliente = ventanaMultipleCliente;
    }
}