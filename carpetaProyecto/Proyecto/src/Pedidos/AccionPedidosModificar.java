package Pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionPedidosModificar implements ActionListener {
	private PedidosModificar pedi;
	
	public AccionPedidosModificar(PedidosModificar pedi) {
		this.pedi=pedi;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comandos=e.getActionCommand();
		
		if(comandos.equals("Guardar")) {
			pedi.modificarCompra();
		}else if(comandos.equals("Eliminar pedido")) {
			pedi.eliminarPedido();
		}else if(comandos.equals("Cambiar entrega")) {
			pedi.cambiarEntrega();
		}else if(comandos.equals("Eliminar compra")) {
			pedi.eliminarCompra();
		}
		

	}

}
