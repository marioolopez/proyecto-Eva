package marcos;

import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.*;

import paqueteprincipal.ventanaPrincipal;

public class VentanaModificacionCliente extends JPanel{
	private BordeFormulario bordeFormularioModificacionCliente;

    public VentanaModificacionCliente(ventanaPrincipal ventanaPrincipal) throws SQLException, ClassNotFoundException {
        this.setLayout(new FlowLayout());
        bordeFormularioModificacionCliente = new BordeFormulario(ventanaPrincipal);
        this.add(bordeFormularioModificacionCliente);
    }

	public BordeFormulario getBordeFormularioModificacionCliente() {
		return bordeFormularioModificacionCliente;
	}

	public void setBordeFormularioModificacionCliente(BordeFormulario bordeFormularioModificacionCliente) {
		this.bordeFormularioModificacionCliente = bordeFormularioModificacionCliente;
	}
    
}
