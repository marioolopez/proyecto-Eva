package Pedidos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AccionListaPedidosModificar implements MouseListener {
	private PedidosModificar ped;
	
	public AccionListaPedidosModificar(PedidosModificar ped) {
		this.ped=ped;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if(source == ped.getListaCliente()){
		    ped.pedidosCliente();
		}else if(source == ped.getListaPedido()){
			ped.buscarCompras();
		}else if(source == ped.getListaCompra()) {
			ped.getProductos().setEditable(true);
			ped.mostrarCompra();
		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
