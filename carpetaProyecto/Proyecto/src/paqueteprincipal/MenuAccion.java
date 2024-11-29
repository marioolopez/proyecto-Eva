package paqueteprincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Pedidos.Pedidos;
import Pedidos.PedidosModificar;
import alvaro.FrameProductosYProveedores;





public class MenuAccion implements ActionListener {
	private ventanaPrincipal ven;
	
	public MenuAccion(ventanaPrincipal ven) {
		this.ven=ven;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		
		if(comando.equals("pedidoA")) {
			Pedidos pedidoA=new Pedidos();
			
			ven.getContentPane().removeAll();
			ven.getContentPane().add(pedidoA);
			pedidoA.setVisible(true);
		}else if(comando.equals("pedidoM")) {
			PedidosModificar pedidoM=new PedidosModificar();
			
			ven.getContentPane().removeAll();
			ven.getContentPane().add(pedidoM);
			pedidoM.setVisible(true);
		}
		else if(comando.equals("Productos")) {
			FrameProductosYProveedores ventanaInterna=new FrameProductosYProveedores();			
			ven.getContentPane().removeAll();
			ventanaInterna.completarFrame();
			ven.getContentPane().add(ventanaInterna);
					
		}

	}

}
