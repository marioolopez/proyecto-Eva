package marcos;

import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JPanel;

import porDefecto.ventanaPrincipal;

public class VentanaBajaCliente extends JPanel {
    private BordeFormulario bordeFormularioBajaCliente;

    public VentanaBajaCliente(ventanaPrincipal ventanaPrincipal) throws ClassNotFoundException, SQLException {
        this.setLayout(new FlowLayout());
        bordeFormularioBajaCliente = new BordeFormulario(ventanaPrincipal);
        this.add(bordeFormularioBajaCliente);
    }

    public BordeFormulario getBordeFormularioBajaCliente() {
        return this.bordeFormularioBajaCliente;
    }

    public void setBordeFormularioBajaCliente(BordeFormulario bordeFormularioBajaCliente) {
        this.bordeFormularioBajaCliente = bordeFormularioBajaCliente;
    }
}