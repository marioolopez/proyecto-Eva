import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import marcos.*;
import marcos.*;
public class cambiarVentanaCliente implements ChangeListener {
    private ventanaPrincipal ventanaPrincipal;
    
    cambiarVentanaCliente(ventanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void stateChanged(ChangeEvent e) {
        int indiceSeleccionado = this.ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getSelectedIndex();
        if (indiceSeleccionado == 0) {
            System.out.println("hola");
        } else if (indiceSeleccionado == 1) {
            this.ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().añadirBotonBuscar();
            this.ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().borrarBotonAlta();
            this.ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().deshabilitarComponentesBaja();
        }

    }
}