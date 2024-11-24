package Pedidos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AccionListaCompras implements MouseListener {
	private Compras compras;
	
	public AccionListaCompras(Compras compras) {
		this.compras=compras;

	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();

		if(source == compras.getListaProducto()) {
			String producto=(String) compras.getProductos().getSelectedValue();
			compras.cantidadMet(producto);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {


	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
