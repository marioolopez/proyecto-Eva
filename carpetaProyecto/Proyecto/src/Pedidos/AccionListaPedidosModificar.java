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
			ped.buscarPedidos();
		}else if(source == ped.getListaPedido()){
			ped.buscarCompras();
			ped.getEliminarPedido().setEnabled(true);
		}else if(source == ped.getListaCompra()) {
			ped.mostrarCompra();
			ped.getEliminarCompra().setEnabled(true);
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
