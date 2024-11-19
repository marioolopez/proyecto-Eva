package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Pedidos.Pedidos;

public class MenuAccion implements ActionListener {
	private ventanaPrincipal ven;
	
	public MenuAccion(ventanaPrincipal ven) {
		this.ven=ven;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		
		if(comando.equals("pedido")) {
			Pedidos pedido=new Pedidos(ven);
			ven.getContentPane().removeAll();
			ven.getContentPane().add(pedido);
			pedido.setVisible(true);
		}

	}

}