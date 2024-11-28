package marcos;

import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JPanel;
import paqueteprincipal.ventanaPrincipal;

public class VentanaAltaCliente extends JPanel {
    private BordeFormulario bordeFormularioAltaCliente;

    public VentanaAltaCliente(ventanaPrincipal ventanaPrincipal) throws SQLException, ClassNotFoundException {
        this.setLayout(new FlowLayout());
        bordeFormularioAltaCliente = new BordeFormulario(ventanaPrincipal);
        this.add(bordeFormularioAltaCliente);
    }

    public BordeFormulario getBordeFormularioAltaCliente() {
        return this.bordeFormularioAltaCliente;
    }

    public void setBordeFormularioAltaCliente(BordeFormulario bordeFormulario) {
        this.bordeFormularioAltaCliente = bordeFormulario;
    }
}
