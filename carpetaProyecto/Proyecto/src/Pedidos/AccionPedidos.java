package Pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionPedidos implements ActionListener {
	private Pedidos pedidos;
	
	public AccionPedidos(Pedidos pedidos) {
		this.pedidos=pedidos;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		
		if(comando.equals("Añadir")) {
			pedidos.crearPedido();
		}else if(comando.equals("eliminar")) {
			pedidos.eliminarCompra();
		}

	}

}
