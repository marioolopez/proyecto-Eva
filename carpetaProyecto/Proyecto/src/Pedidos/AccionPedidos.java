package Pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionPedidos implements ActionListener {

	private PedidosAnadir pedidos;
	
	public AccionPedidos(PedidosAnadir pedidos) {
		this.pedidos=pedidos;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		
		if(comando.equals("Añadir")) {
			pedidos.anadirMetPedido();
		}else if(comando.equals("eliminar")) {
			pedidos.eliminarCompra();
		}

	}

}
