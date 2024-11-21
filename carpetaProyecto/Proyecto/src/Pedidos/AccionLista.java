package Pedidos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AccionLista implements MouseListener {
	private Compras compras;
	
	public AccionLista(Compras compras) {
		this.compras=compras;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String producto=(String) compras.getProductos().getSelectedValue();
		compras.cantidadMet(producto);
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
