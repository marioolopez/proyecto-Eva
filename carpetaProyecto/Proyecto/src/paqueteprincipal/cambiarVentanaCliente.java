package paqueteprincipal;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class cambiarVentanaCliente implements ChangeListener {
    private ventanaPrincipal ventanaPrincipal;
    
    public cambiarVentanaCliente(ventanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void stateChanged(ChangeEvent e) {
        int indiceSeleccionado = ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getSelectedIndex();
        if (indiceSeleccionado == 0) {//ALTA
        	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().cambiarVentanaAlta();
        	//RESETEAR EL RESTO DE VENTANAS
        	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().resetearVentanaBaja();
        	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().resetearVentanaModificacion();
        	//CAMBIAR BORDE DEL FORMULARIO
        	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().añadirBordeAlta();
        } else if (indiceSeleccionado == 1) {//BAJA
            ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().cambiarVentanaBaja();
          //RESETEAR EL RESTO DE VENTANAS
            ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().resetearVentanaAlta();
            ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().resetearVentanaModificacion();
          //CAMBIAR BORDE DEL FORMULARIO
            ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().añadirBordeBaja();
        }else if (indiceSeleccionado == 2){//MODIFICACION
        	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().cambiarVentanaModificacion();
        	//RESETEAR EL RESTO DE VENTANAS
        	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().resetearVentanaAlta();
        	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().resetearVentanaBaja();
        	//CAMBIAR BORDE DEL FORMULARIO
      	  	ventanaPrincipal.getVentanaCliente().getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().añadirBordeModificacion();
        }
    }
}